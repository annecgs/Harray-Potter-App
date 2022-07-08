package com.example.harraypotterapp.ui.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.harraypotterapp.R
import com.example.harraypotterapp.data.remote.dto.PersonagemApiResult
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.databinding.FragmentFavoritesBinding
import com.example.harraypotterapp.ui.error.ErrorFragment
import com.example.harraypotterapp.ui.home.InfoFragment
import com.example.harraypotterapp.ui.viewModel.MainViewModel
import com.example.harraypotterapp.utils.Helpers

class FavoritesFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels { Helpers.getMainViewModelFactory() }
    private lateinit var adapter: AdapterFavoritos
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var errorFragment: ErrorFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()

       /* adapter.onClickListener = { pokemonId ->
            viewModel.setPokemon(pokemonId)
            replaceFragment(InfoFragment())
        }*/

        return root
    }

    private fun setupAdapter() {
        adapter = AdapterFavoritos()
        binding.recyclerViewFavorites.adapter = adapter

        // val layoutManager = GridLayoutManager(activity, 2)
        // layoutManager.orientation = RecyclerView.VERTICAL
        // binding.rvGrifinioriaMembres.layoutManager = layoutManager
        // binding.rvGrifinioriaMembres.adapter = adapter

        viewModel.personagemItem.observe(viewLifecycleOwner) { listPersonagens ->
            getFavoritos(listPersonagens)
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
           // binding.widgetListEmpty.visibility = View.GONE
            binding.recyclerViewFavorites.visibility = View.VISIBLE
            binding.includeDivider.root.visibility = View.VISIBLE
            // binding.progressBar.visibility = View.GONE
        } else {
            binding.recyclerViewFavorites.visibility = View.GONE
            binding.includeDivider.root.visibility = View.GONE
           // binding.widgetListEmpty.visibility = View.VISIBLE
            // binding.progressBar.visibility = View.GONE
        }
    }

    private fun setListAdapter(list: List<PersonagensItem>) {
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }

    private fun getFavoritos(list: PersonagemApiResult<List<PersonagensItem>>) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        Log.d("ListFavorites", "getFavorites: ${sharedPref.all}")
        if (sharedPref.all.isEmpty()) binding.tvNoFavorites.visibility = View.VISIBLE else binding.tvNoFavorites.visibility = View.GONE
        val tempList: MutableList<PersonagensItem> = ArrayList()
        when (list) {
            is PersonagemApiResult.Success -> {
                list.data.forEach {
                    if (it.isFavorite) {
                        tempList.add(it)
                    }
                }
                setupSearchView(tempList as List<PersonagensItem>)
            }

            is PersonagemApiResult.Error -> {
                errorFragment = ErrorFragment()
                replaceFragment(ErrorFragment())
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
