package com.example.frontend.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.frontend.R
import com.example.frontend.databinding.FragmentErrorBinding
import com.example.frontend.ui.home.HomeFragment
import com.example.frontend.ui.viewModel.MainViewModel
import com.example.frontend.utils.Helpers

class ErrorFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() { Helpers.getMainViewModelFactory() }
    private var _binding: FragmentErrorBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        mensagemErro()
        binding.btnTryAgain.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        val view = binding.root
        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_fragment, fragment)
        fragmentTransaction?.commit()
    }

    fun mensagemErro() {
        val mensagem = viewModel.mensagem
        if (mensagem.contains("400")) {
            binding.codError.text = "400"
            binding.txtCauseError.text = "Instabilidade no seu computador ou na sua conexão de Internet"

        } else if (mensagem.contains("401")) {
            binding.codError.text = "401"
            binding.txtCauseError.text = "O site que você está tentando acessar se encontra protegido e requer autorização ou autenticação"

        } else if (mensagem.contains("403")) {
            binding.codError.text = "403"
            binding.txtCauseError.text = "Negação por parte do proprietário, que não permite que a página receba visitas"

        } else if (mensagem.contains("404")) {
            binding.codError.text = "404"
            binding.txtCauseError.text = "URL não localizada"

        } else if (mensagem.contains("410")) {
            binding.codError.text = "410"
            binding.txtCauseError.text = "URL excluída permanentemente"

        } else if (mensagem.contains("429")) {
            binding.codError.text = "429"
            binding.txtCauseError.text = "Você excedeu o limite de requisições"

        } else if (mensagem.contains("500")) {
            binding.codError.text = "500"
            binding.txtCauseError.text = "O servidor não pode atender sua solicitação neste momento"

        } else if (mensagem.contains("502")) {
            binding.codError.text = "502"
            binding.txtCauseError.text = "Falha de comunicação entre os servidores"

        } else if (mensagem.contains("503")) {
            binding.codError.text = "503"
            binding.txtCauseError.text = "Serviço temporariamente indisponível"

        } else if (mensagem.contains("504")) {
            binding.codError.text = "504"
            binding.txtCauseError.text = "Esperando por muito tempo para receber a resposta do servidor"

        } else if (mensagem.contains("505")) {
            binding.codError.text = "505"
            binding.txtCauseError.text = "Versão HTTP não suportada"
        } else{
            binding.codError.visibility = View.GONE
            binding.txtCauseError.text = "Serviço temporariamente indisponível"
        }
    }
}

private fun Button.setOnClickListener(replaceFragment: Unit) {
}
