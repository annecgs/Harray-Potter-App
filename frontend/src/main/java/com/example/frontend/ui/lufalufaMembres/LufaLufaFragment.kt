package com.example.frontend.ui.lufalufaMembres

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backend.data.remote.dto.PersonagemApiResult
import com.example.backend.data.remote.dto.PersonagensItem
import com.example.frontend.R
import com.example.frontend.databinding.FragmentLufaLufaBinding
import com.example.frontend.ui.error.ErrorFragment
import com.example.frontend.ui.home.InfoFragment
import com.example.frontend.ui.onboarding.OnboardingActivity
import com.example.frontend.ui.viewModel.MainViewModel
import com.example.frontend.utils.Helpers

class LufaLufaFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels { Helpers.getMainViewModelFactory() }
    private lateinit var adapter: AdapterLufaLufa
    private var _binding: FragmentLufaLufaBinding? = null
    private val binding get() = _binding!!
    private lateinit var errorFragment: ErrorFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLufaLufaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //(activity as AppCompatActivity)!!.supportActionBar?.title?.get(R.string.menu_hufflepuff)
        setupAdapter()
        OnboardingActivity.screen = 4
        return root
    }

    private fun setupAdapter() {
        adapter = AdapterLufaLufa()
        //binding.rvLufalufaMembres.adapter = adapter

         val layoutManager = GridLayoutManager(activity, 2)
         layoutManager.orientation = RecyclerView.VERTICAL
         binding.rvLufalufaMembres.layoutManager = layoutManager
         binding.rvLufalufaMembres.adapter = adapter

        binding.rvLufalufaMembres.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findFirstVisibleItemPosition() == 0) {
                    binding.fabLufalufa.visibility = View.GONE
                } else binding.fabLufalufa.visibility = View.VISIBLE
            }
        })
        binding.fabLufalufa.setOnClickListener {
            binding.rvLufalufaMembres.scrollToPosition(0)
        }

        viewModel.personagemItem.observe(viewLifecycleOwner) { listPersonagens ->
            getMembresGrifindor(listPersonagens)
        }
    }

    private fun setupSearchView(list: List<PersonagensItem>) {
        var newList: MutableList<PersonagensItem> = ArrayList()
        // binding.serchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
        binding.serchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener,
                SearchView.OnQueryTextListener {
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
            //binding.widgetListEmpty.visibility = View.GONE
            binding.rvLufalufaMembres.visibility = View.VISIBLE
            binding.includeDivider.root.visibility = View.VISIBLE
        } else {
            binding.rvLufalufaMembres.visibility = View.GONE
            binding.includeDivider.root.visibility = View.GONE
            //binding.widgetListEmpty.visibility = View.VISIBLE
            //binding.tvNoFavorites.visibility = View.VISIBLE
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
                    if (it.house == "Hufflepuff") {
                        tempList.add(it)
                    }
                }
                if (tempList.isNotEmpty()) {
                    //binding.tvNoFavorites.visibility = View.GONE
                    //binding.widgetListEmpty.visibility = View.GONE
                }
                setupSearchView(tempList as List<PersonagensItem>)
            }

            is PersonagemApiResult.Error -> {
                errorFragment = ErrorFragment()
                replaceFragment(ErrorFragment())
                binding.rvLufalufaMembres.visibility = View.GONE
                binding.includeHeader.imageHouseLufaLufa.visibility = View.GONE
                binding.serchView.visibility = View.GONE
                binding.includeDivider.root.visibility = View.GONE
                //binding.tvNoFavorites.visibility = View.GONE
                //binding.widgetListEmpty.visibility = View.GONE
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
