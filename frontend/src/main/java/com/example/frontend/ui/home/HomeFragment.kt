package com.example.harraypotterapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harraypotterapp.R
import com.example.harraypotterapp.data.remote.dto.PersonagemApiResult
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.databinding.FragmentHomeBinding
import com.example.harraypotterapp.ui.error.ErrorFragment
import com.example.harraypotterapp.ui.viewModel.MainViewModel
import com.example.harraypotterapp.utils.Helpers

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterHome: AdapterHome
    private lateinit var errorFragment: ErrorFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupUi()
        return root
    }

    private fun setupUi() {
        configAdapter()
    }

    private fun configAdapter() {
        adapterHome = AdapterHome()
        binding.rvHome.adapter = adapterHome

        getData()
        goToFirstItemInRecyclerView()
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
            binding.rvHome.visibility = View.VISIBLE
            binding.includeDivider.root.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        } else {
            binding.rvHome.visibility = View.GONE
            binding.includeDivider.root.visibility = View.GONE
            binding.widgetListEmpty.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.tvNoFavorites.visibility = View.VISIBLE
        }
    }

    private fun getData() {
        viewModel.getPersonagensFromRetrofit()
        viewModel.personagemItem.observe(viewLifecycleOwner) { listPersonagem ->
            when (listPersonagem) {
                is PersonagemApiResult.Loading<*> -> {
                    Log.d("INFO", "Loading")
                }
                is PersonagemApiResult.Success<*> -> {
                    Log.d("INFO", "Success: ${listPersonagem.data}")
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.visibility = View.VISIBLE
                    binding.includeDivider.root.visibility = View.VISIBLE

                    val sharedPref =
                        activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe

                    (listPersonagem.data as List<PersonagensItem>).forEach { personagem ->
                        if (sharedPref.all.containsKey(personagem.name)) personagem.isFavorite = true
                    }
                    setListAdapter(listPersonagem.data as List<PersonagensItem>)
                    setupSearchView(listPersonagem.data as List<PersonagensItem>)
                }
                is PersonagemApiResult.Error<*> -> {
                    binding.progressBar.visibility = View.GONE
                    errorFragment = ErrorFragment()
                    replaceFragment(ErrorFragment())
                    binding.includeHeader.imageView2.visibility = View.GONE
                    binding.serchView.visibility = View.GONE
                    binding.includeDivider.root.visibility = View.GONE
                    viewModel.mensagem = listPersonagem.throwable.message.toString()
                    Log.d("INFO", "Error.cause: ${listPersonagem.throwable.cause}")
                    Log.d("INFO", "Error: $listPersonagem")
                    Log.d("INFO", "Error.message: ${listPersonagem.throwable.message}")
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapterHome.onClickListener = { personagemId ->
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

    private fun goToFirstItemInRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvHome.layoutManager = linearLayoutManager
        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    binding.fab.visibility = View.GONE
                } else binding.fab.visibility = View.VISIBLE
            }
        })
        binding.fab.setOnClickListener {
            binding.rvHome.scrollToPosition(0)
        }
    }

    private fun setListAdapter(list: List<PersonagensItem>) {
        adapterHome.submitList(list)
        adapterHome.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
