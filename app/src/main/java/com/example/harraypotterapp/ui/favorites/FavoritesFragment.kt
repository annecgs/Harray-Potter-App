package com.example.harraypotterapp.ui.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.harraypotterapp.R
import com.example.harraypotterapp.data.remote.dto.PersonagemApiResult
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.databinding.FragmentFavoritesBinding
import com.example.harraypotterapp.ui.viewModel.MainViewModel
import com.example.harraypotterapp.utils.Helpers

class FavoritesFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels { Helpers.getMainViewModelFactory() }
    private lateinit var adapter: AdapterFavoritos
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

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

        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.orientation = RecyclerView.HORIZONTAL

        binding.rvFavoritos.layoutManager = layoutManager

        binding.rvFavoritos.adapter = adapter

        viewModel.personagemItem.observe(viewLifecycleOwner) { listPokemons ->
            getFavorites(listPokemons)
        }
    }

    private fun getFavorites(list: PersonagemApiResult<List<PersonagensItem>>) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        Log.d("ListFavorites", "getFavorites: ${sharedPref.all}")
        if (sharedPref.all.isEmpty()) binding.tvNoFavorites.visibility = View.VISIBLE else binding.tvNoFavorites.visibility = View.GONE
        val tempList: MutableList<PersonagensItem> = ArrayList()
        when (list) {
            is PersonagemApiResult.Success -> {
                list.data.forEach {
                    if (sharedPref.all.contains(it.name)) {
                        tempList.add(it)
                    }
                }
            }

            is PersonagemApiResult.Error -> {
                // errorFragment = ErrorFragment()
                // replaceFragment(ErrorFragment())
            }
        }

        Log.d(
            "ListFilter",
            "getFavorites: $tempList"
        )

        adapter.submitList(tempList)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_fragment, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
