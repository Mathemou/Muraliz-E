package com.example.muralize.Views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.muralize.Adapters.SolicitacoesAdapter
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.Utils.SessionManager
import com.example.muralize.ViewModels.ObterDadosUsuarioViewModel
import com.example.muralize.ViewModels.ObterSolicitacoesViewModel
import com.example.muralize.databinding.ActivityMenuInicialBinding
import com.google.firebase.auth.FirebaseAuth

class MenuInicial : AppCompatActivity() {
    // binding para acessar os componentes
    private lateinit var binding : ActivityMenuInicialBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var mObterDadosUsuarioViewModel : ObterDadosUsuarioViewModel
    private lateinit var mObterSolicitacoesViewModel : ObterSolicitacoesViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // infla o binding
        binding = ActivityMenuInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        mObterSolicitacoesViewModel = ViewModelProvider(this)[ObterSolicitacoesViewModel::class.java]
        mObterDadosUsuarioViewModel = ViewModelProvider(this)[ObterDadosUsuarioViewModel::class.java]
        observe()
        mObterDadosUsuarioViewModel.obterUsuarioLogado()

        binding.buttonPostar.setOnClickListener {
            val intent = Intent(this, RegistrarSolicitacao::class.java)
            startActivity(intent)
        }
        binding.buttonAdiconarDisciplinas.setOnClickListener {
            val intent = Intent(this, ListaDisciplinas::class.java)
            startActivity(intent)
        }
        binding.buttonResponder.setOnClickListener {
            PopUpMethods.toastLong(this, "Não implementado!")
        }
        binding.buttonMinhasSolicitacoes.setOnClickListener {
            PopUpMethods.toastLong(this, "Não implementado!")
        }

    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        mObterDadosUsuarioViewModel.currentUser.observe(this){ usuario ->
            val primeiroNome = if (usuario.nome.contains(" ")) usuario.nome.split(" ")[0] else usuario.nome
            sessionManager.salvarNomeUsuario(usuario.nome)
            sessionManager.salvarTelefone(usuario.telefone)
            binding.mensagemDeBoasVindas.text = binding.mensagemDeBoasVindas.text.toString() + " " + primeiroNome
            mObterSolicitacoesViewModel.obterSolicitacoesCompativeis(usuario)
        }
        mObterSolicitacoesViewModel.solicitacoes.observe(this){ solicitacoes ->
            binding.solicitacoesRv.adapter = SolicitacoesAdapter(solicitacoes.toMutableList())
            binding.solicitacoesRv.layoutManager = GridLayoutManager(this, 3)
        }
    }


    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Tem certeza que deseja fazer logout?")
            .setPositiveButton("Sim") { dialog, _ ->
                // Realize o logout aqui
                FirebaseAuth.getInstance().signOut()
                finishAffinity()
            }
            .setNegativeButton("Não", null)
            .create()
        alertDialog.show()
    }

}