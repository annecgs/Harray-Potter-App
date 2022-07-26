package com.example.frontend.ui.grifinoriaMembres

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.frontend.R
import com.example.frontend.databinding.FragmentGrifinoriaBinding
import com.example.backend.data.remote.dto.PersonagemApiResult
import com.example.backend.data.remote.dto.PersonagensItem
import com.example.frontend.ui.error.ErrorFragment
import com.example.frontend.ui.home.InfoFragment
import com.example.frontend.ui.viewModel.MainViewModel
import com.example.frontend.utils.Helpers

class GrifinoriaFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels { Helpers.getMainViewModelFactory() }
    private lateinit var adapter: AdapterGrifinoriaMembres
    private var _binding: FragmentGrifinoriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var errorFragment: ErrorFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGrifinoriaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()

        return root
    }

    private fun setupAdapter() {
        adapter = AdapterGrifinoriaMembres()
        binding.rvGrifinoriaMembres.adapter = adapter

        // val layoutManager = GridLayoutManager(activity, 2)
        // layoutManager.orientation = RecyclerView.VERTICAL
        // binding.rvGrifinioriaMembres.layoutManager = layoutManager
        // binding.rvGrifinioriaMembres.adapter = adapter

        viewModel.personagemItem.observe(viewLifecycleOwner) { listPersonagens ->
            getMembresGrifindor(listPersonagens)
        }
    }

    private fun setupSearchView(list: List<PersonagensItem>) {
        var newList: MutableList<PersonagensItem> = ArrayList()
        binding.serchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.serchView.clearFocus()
                newList = Helpers.FilterListQuery(query, list)
                setlistQueryAdapter(newList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newList = Helpers.FilterListQuery(newText, list)
                setlistQueryAdapter(newList)
                return true
            }
        })
    }

    private fun setlistQueryAdapter(newList: MutableList<PersonagensItem>) {
        if (newList.isNotEmpty()) {
            setListAdapter(newList)
            binding.widgetListEmpty.visibility = View.GONE
            binding.rvGrifinoriaMembres.visibility = View.VISIBLE
            binding.includeDivider.root.visibility = View.VISIBLE
        } else {
            binding.rvGrifinoriaMembres.visibility = View.GONE
            binding.includeDivider.root.visibility = View.GONE
            binding.tvNoFavorites.visibility = View.VISIBLE
            binding.widgetListEmpty.visibility = View.VISIBLE
        }
    }

    private fun setListAdapter(list: List<PersonagensItem>) {
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }

    private fun getMembresGrifindor(list: PersonagemApiResult<List<PersonagensItem>>) {
        val tempList: MutableList<PersonagensItem> = ArrayList()
        when (list) {
            is PersonagemApiResult.Success -> {
                list.data.forEach {
                    if (it.house == "Gryffindor") {
                        tempList.add(it)
                    }
                }
                if (tempList.isNotEmpty()) {
                    binding.tvNoFavorites.visibility = View.GONE
                    binding.widgetListEmpty.visibility = View.GONE
                }
                setupSearchView(tempList as List<PersonagensItem>)
            }

            is PersonagemApiResult.Error -> {
                errorFragment = ErrorFragment()
                replaceFragment(ErrorFragment())
                binding.rvGrifinoriaMembres.visibility = View.GONE
                binding.includeHeader.imageView2.visibility = View.GONE
                binding.serchView.visibility = View.GONE
                binding.includeDivider.root.visibility = View.GONE
                binding.tvNoFavorites.visibility = View.GONE
                binding.widgetListEmpty.visibility = View.GONE
            }
        }

        Log.d(
            "ListFilter",
            "getFavorites: $tempList"
        )

        adapter.submitList(tempList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.onClickListener = { personagemId ->
            viewModel.setPersonagens(personagemId)
            replaceFragment(InfoFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
