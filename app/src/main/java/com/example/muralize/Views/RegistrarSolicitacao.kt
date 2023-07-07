package com.example.muralize.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.muralize.Classes.Disciplina
import com.example.muralize.Classes.Usuario
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.Utils.UtilMethods
import com.example.muralize.ViewModels.ObterDadosUsuarioViewModel
import com.example.muralize.ViewModels.ObterDisciplinasViewModel
import com.example.muralize.ViewModels.RegistrarSolicitacaoViewModel
import com.example.muralize.databinding.ActivityCadastroAlunoBinding
import com.example.muralize.databinding.ActivityRegistrarSolicitacaoBinding

class RegistrarSolicitacao : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrarSolicitacaoBinding
    private lateinit var mObterDadosUsuarioViewModel: ObterDadosUsuarioViewModel
    private lateinit var mObterDisciplinasViewModel : ObterDisciplinasViewModel
    private lateinit var mRegistrarSolicitacaoViewModel: RegistrarSolicitacaoViewModel
    private lateinit var aluno : Usuario
    private lateinit var disciplinas : List<Disciplina>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarSolicitacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mObterDadosUsuarioViewModel = ViewModelProvider(this)[ObterDadosUsuarioViewModel::class.java]
        mObterDisciplinasViewModel = ViewModelProvider(this)[ObterDisciplinasViewModel::class.java]
        mRegistrarSolicitacaoViewModel = ViewModelProvider(this)[RegistrarSolicitacaoViewModel::class.java]
        observe()
        mObterDadosUsuarioViewModel.obterUsuarioLogado()

        binding.btnCadastrarActivityCadastro.setOnClickListener{
            if(binding.insDisciplinaActivityRegistrarSolicitacao.text.toString().isNotEmpty()&&binding.insDescricaoActivityRegistrarSolicitacao.text.toString().isNotEmpty()){
                val disciplina = UtilMethods.obterDisciplinaPorNome(binding.insDisciplinaActivityRegistrarSolicitacao.text.toString(), disciplinas)
                if(disciplina != null) {
                    mRegistrarSolicitacaoViewModel.solicitar(
                        aluno,
                        disciplina.documento!!,
                        binding.insDescricaoActivityRegistrarSolicitacao.text.toString()
                    )
                }
            }
        }
    }

    private fun observe() {
        mObterDadosUsuarioViewModel.currentUser.observe(this){ usuario ->
            aluno = usuario
            mObterDisciplinasViewModel.obterDisciplinasCursadas(usuario)
        }
        mObterDisciplinasViewModel.disciplinas.observe(this){
            disciplinas = it
            val listaNomesDisciplinas = mutableListOf<String>()
            for(disciplina in disciplinas){
                listaNomesDisciplinas.add(disciplina.nome)
            }
            if(disciplinas.isNotEmpty()){
                val adapter = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, listaNomesDisciplinas.distinct()
                )
                binding.insDisciplinaActivityRegistrarSolicitacao.setAdapter(adapter)
            }
        }
        mRegistrarSolicitacaoViewModel.registroSolicitacao.observe(this){
            if(it){
                PopUpMethods.toastLong(this, "Sucesso ao enviar solicitação")
                val intent = Intent(this, MenuInicial::class.java)
                startActivity(intent)
                finish()
            }
        }
        mRegistrarSolicitacaoViewModel.failureregistroSolicitacao.observe(this){
            PopUpMethods.toastLong(this, "Falha ao enviar solicitação")
        }
    }
}