package com.example.harraypotterapp.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.harraypotterapp.R
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.databinding.FragmentInfoBinding
import com.example.harraypotterapp.ui.viewModel.MainViewModel
import com.example.harraypotterapp.utils.Helpers

class InfoFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        setupAdapter()

        binding.btnReturn.setOnClickListener {
            getActivity()?.onBackPressed()
        }

        return view
    }

    private fun setupAdapter() {

        getData()
    }

    private fun getData() {
        viewModel.personagemSelected.observe(viewLifecycleOwner) { personagem ->
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe
            if (sharedPref.all.containsKey(personagem.name)) {
                binding.ivFavorite.visibility = View.VISIBLE
                binding.btnAddFavorite.text = "REMOVER"
            } else {
                binding.ivFavorite.visibility = View.GONE
                binding.btnAddFavorite.text = "ADICIONAR"
            }

            Log.d("PersonagemRecive", "onActivityCreated: $personagem")
            oficio(personagem)
            findHouse(personagem)
            setImage(personagem)
            genero(personagem)
            dataNascimento(personagem)


            binding.btnAddFavorite.setOnClickListener {
                Log.d("Personagem:", "getData: $personagem")
                when (personagem.isFavorite) {
                    true -> { // Vai remover dos favoritos
                        sharedPref.edit().remove(personagem.name).apply()
                        viewModel.setFavorite(false)
                        binding.btnAddFavorite.text = "ADICIONAR"
                        binding.ivFavorite.visibility = View.GONE
                    }
                    false -> { // Vai adicionar aos favoritos
                        sharedPref.edit().putString(personagem.name, personagem.name).apply()
                        viewModel.setFavorite(true)
                        binding.btnAddFavorite.text = "REMOVER"
                        binding.ivFavorite.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun dataNascimento(x: PersonagensItem){
        if(x.dateOfBirth!= ""){
            binding.tvDataNascimento.text = x.dateOfBirth
        }else{
            binding.tvDataNascimento.text = "Não informado"
        }
    }

    private fun genero(x: PersonagensItem){
        if(x.gender == "male"){
            binding.tvGenero.text = x.gender
            Glide.with(binding.root.context)
                .load(R.drawable.iconmasculino)
                .into(binding.iconGenero)
        }else if (x.gender == "female"){
            binding.tvGenero.text = x.gender
            Glide.with(binding.root.context)
                .load(R.drawable.iconfeminino)
                .into(binding.iconGenero)
        }else{
            binding.tvGenero.text = "Não informado"
            Glide.with(binding.root.context)
                .load(R.drawable.notfoundicon)
                .into(binding.iconGenero)
        }
    }

    private fun oficio(x: PersonagensItem) {
        if (x.hogwartsStudent == true) {
            binding.tvOficio.text = "Estudante em Hogwarts"
            Glide.with(binding.root.context)
                .load(R.drawable.studanticon)
                .into(binding.iconOficio)
        } else if (x.hogwartsStaff == true) {
            binding.tvOficio.text = "Funcionário em Hogwarts"
            Glide.with(binding.root.context)
                .load(R.drawable.iconwork)
                .into(binding.iconOficio)
        } else {
            binding.tvOficio.text = "Ofício Não informado"
            Glide.with(binding.root.context)
                .load(R.drawable.notfoundicon)
                .into(binding.iconOficio)
        }
    }

    private fun findHouse(x: PersonagensItem) {
        if (x.house == "Gryffindor") {
            Glide.with(binding.root.context)
                .load(R.drawable.grifinoria)
                .into(binding.casaIcon)
        } else if (x.house == "Hufflepuff") {
            Glide.with(binding.root.context)
                .load(R.drawable.lufalufa)
                .into(binding.casaIcon)
        } else if (x.house == "Ravenclaw") {
            Glide.with(binding.root.context)
                .load(R.drawable.corvinal)
                .into(binding.casaIcon)
        } else if (x.house == "Slytherin") {
            Glide.with(binding.root.context)
                .load(R.drawable.sonserina)
                .into(binding.casaIcon)
        } else {
            Glide.with(binding.root.context)
                .load(R.drawable.casanaodefinida)
                .into(binding.casaIcon)
        }
    }

    private fun setImage(x: PersonagensItem) {
        if (x.image != "") {
            Glide.with(binding.root.context)
                .load(x.image)
                .into(binding.imagePeople)
        } else {
            Glide.with(binding.root.context)
                .load(R.drawable.bruxonaoidentificado)
                .into(binding.imagePeople)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_fragment, fragment)
        fragmentTransaction?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
