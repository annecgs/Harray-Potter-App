package com.example.frontend.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.backend.data.remote.dto.PersonagensItem
import com.example.frontend.R
import com.example.frontend.databinding.FragmentInfoBinding
import com.example.frontend.ui.viewModel.MainViewModel
import com.example.frontend.utils.Helpers

class InfoFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupAdapter() {
        getData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getData() {
        viewModel.personagemSelected.observe(viewLifecycleOwner) { personagem ->
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@observe
            if (sharedPref.all.containsKey(personagem.name)) {
                binding.btnAddFavorite.text = "REMOVER"
                verifyFavorite(personagem)
            } else {
                binding.btnAddFavorite.text = "ADICIONAR"
                verifyFavorite(personagem)
            }

            Log.d("PersonagemRecive", "onActivityCreated: $personagem")
            oficio(personagem)
            findHouse(personagem)
            setImage(personagem)
            genero(personagem)
            dataNascimento(personagem)
            setAncestry(personagem)
            setPatrono(personagem)
            //getName(personagem)

            binding.btnAddFavorite.setOnClickListener {
                Log.d("Personagem:", "getData: $personagem")
                when (personagem.isFavorite) {
                    true -> { // Vai remover dos favoritos
                        sharedPref.edit().remove(personagem.name).apply()
                        viewModel.setFavorite(false)
                        binding.btnAddFavorite.text = "ADICIONAR"
                        verifyFavorite(personagem)

                    }
                    false -> { // Vai adicionar aos favoritos
                        sharedPref.edit().putString(personagem.name, personagem.name).apply()
                        viewModel.setFavorite(true)
                        binding.btnAddFavorite.text = "REMOVER"
                        verifyFavorite(personagem)
                    }
                }
            }
        }
    }

    private fun verifyFavorite(x: PersonagensItem){
        if(x.isFavorite){
            binding.ivFavorite.setImageResource(R.drawable.favorito_on)
        } else{
            binding.ivFavorite.setImageResource(R.drawable.favorito_off)
        }
    }

    private fun dataNascimento(x: PersonagensItem) {
        if (x.dateOfBirth == null) {
            binding.tvDataNascimento.text = "Não informado"
        } else {
            binding.tvDataNascimento.text = x.dateOfBirth
        }
    }

    private fun getName(x: PersonagensItem){
        (activity as AppCompatActivity).supportActionBar?.title = x.name
    }

    private fun genero(x: PersonagensItem) {
        if (x.gender == "male") {
            binding.tvGenero.text = x.gender
            Glide.with(binding.root.context)
                .load(R.drawable.iconmasculino)
                .into(binding.iconGenero)
        } else if (x.gender == "female") {
            binding.tvGenero.text = x.gender
            Glide.with(binding.root.context)
                .load(R.drawable.iconfeminino)
                .into(binding.iconGenero)
        } else {
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun findHouse(x: PersonagensItem) {
        if (x.house == "Gryffindor") {
            Glide.with(binding.root.context)
                .load(R.drawable.grifinoria)
                .into(binding.casaIcon)
            binding.backgroundHouse.setBackgroundResource(R.drawable.background_grifinoria)
            binding.imagePeople.borderColor = context?.getColor(R.color.circle_gryffindor)!!
            binding.tvHouse.setText(x.house)
        } else if (x.house == "Hufflepuff") {
            Glide.with(binding.root.context)
                .load(R.drawable.lufalufa)
                .into(binding.casaIcon)
            binding.backgroundHouse.setBackgroundResource(R.drawable.background_lufalufa)
            binding.imagePeople.borderColor = context?.getColor(R.color.circle_hufflepuff)!!
            binding.tvHouse.setText(x.house)
        } else if (x.house == "Ravenclaw") {
            Glide.with(binding.root.context)
                .load(R.drawable.corvinal)
                .into(binding.casaIcon)
            binding.backgroundHouse.setBackgroundResource(R.drawable.background_corvinal)
            binding.imagePeople.borderColor = context?.getColor(R.color.circle_ravenclaw)!!
            binding.tvHouse.setText(x.house)
        } else if (x.house == "Slytherin") {
            Glide.with(binding.root.context)
                .load(R.drawable.sonserina)
                .into(binding.casaIcon)
            binding.backgroundHouse.setBackgroundResource(R.drawable.background_sonserina)
            binding.imagePeople.borderColor = context?.getColor(R.color.circle_slytherin)!!
            binding.tvHouse.setText(x.house)
        } else {
            Glide.with(binding.root.context)
                .load(R.drawable.casanaodefinida)
                .into(binding.casaIcon)
            binding.backgroundHouse.setBackgroundResource(R.drawable.background_marauders_map)
            binding.imagePeople.borderColor = context?.getColor(R.color.circle_maraudes_map)!!
        }
    }

    private fun setImage(x: PersonagensItem) {
        if (x.image != "") {
            Glide.with(binding.root.context)
                .load(x.image)
                .circleCrop()
                .into(binding.imagePeople)
        } else {
            Glide.with(binding.root.context)
                .load(R.drawable.sem_foto)
                .circleCrop()
                .into(binding.imagePeople)
        }
    }

    private fun setPatrono(x: PersonagensItem){
        if(x.patronus != ""){
            binding.tvPatrono.setText(x.patronus)
        } else{
            binding.tvPatrono.text = "Não localizado"
        }
    }

    private fun setAncestry(x: PersonagensItem){
       if(x.ancestry != ""){
           binding.tvBlood.setText(x.ancestry)
       }else{
           binding.tvBlood.text = "Não localizado"
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
