package com.example.muralize.Views

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Universidade
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.Utils.UtilMethods
import com.example.muralize.ViewModels.CadastroAlunosViewModel
import com.example.muralize.ViewModels.ObterCursosViewModel
import com.example.muralize.ViewModels.ObterUniversidadesViewModel
import com.example.muralize.databinding.ActivityCadastroAlunoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.grpc.okhttp.internal.Util

/**
 * Activity responsável pelo cadastro de um aluno.
 */
class CadastroAluno : AppCompatActivity() {
    private lateinit var binding : ActivityCadastroAlunoBinding
    private lateinit var mUniversidadesViewModel : ObterUniversidadesViewModel
    private lateinit var mCursosViewModel : ObterCursosViewModel
    private lateinit var mCadastroAlunosViewModel: CadastroAlunosViewModel
    private lateinit var listaDeUniversidades : List<Universidade>
    private lateinit var listaDeCursos : List<Curso>
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUniversidadesViewModel = ViewModelProvider(this)[ObterUniversidadesViewModel::class.java]
        mCursosViewModel = ViewModelProvider(this)[ObterCursosViewModel::class.java]
        mCadastroAlunosViewModel = ViewModelProvider(this)[CadastroAlunosViewModel::class.java]
        binding.insTelefoneActivityCadastro.addTextChangedListener(UtilMethods.mask("(##)#####-####", binding.insTelefoneActivityCadastro))
        observe()
        mUniversidadesViewModel.obterUniversidades()
        auth = Firebase.auth

        binding.btnBuscarUniversidadeActivityCadastro.setOnClickListener{
            binding.inputCursoActivityCadastro.visibility = View.GONE
            val universidade = binding.insUniversidadeActivityCadastro.text.toString()
            mCursosViewModel.obterCursos(universidade)
        }
        binding.btnCadastrarActivityCadastro.setOnClickListener{
            val nome_completo = binding.insNomeActivityCadastro.text.toString()
            val numero_telefone = binding.insTelefoneActivityCadastro.text.toString()
            val email = binding.insEmailActivityCadastro.text.toString()
            val universidade = binding.insUniversidadeActivityCadastro.text.toString()
            val curso = binding.insCursoActivityCadastro.text.toString()
            val senha = binding.insSenhaActivityCadastro.text.toString()
            if(nome_completo.isEmpty() || numero_telefone.isEmpty() || email.isEmpty() || universidade.isEmpty() || curso.isEmpty() || senha.isEmpty()){
                PopUpMethods.SnackbarLong(it, "Preencha todos os campos!")
            } else if (numero_telefone.length < 11){
                PopUpMethods.SnackbarLong(it, "Número de telefone inválido!")
            }else {
                val dadosAluno = HashMap<String, Any>()
                dadosAluno["nome"] = binding.insNomeActivityCadastro.text.toString()
                dadosAluno["telefone"] = UtilMethods.replaceChars(binding.insTelefoneActivityCadastro.text.toString())
                dadosAluno["curso"] = UtilMethods.obterCursoPorNome(curso, listaDeCursos)!!.documento!!
                dadosAluno["universidade"] = UtilMethods.obterUniversidadePorNome(universidade, listaDeUniversidades)!!.documento!!
                mCadastroAlunosViewModel.cadastrar(email, senha, dadosAluno)
            }
        }
    }

    /**
     * Observa as mudanças nas MutableLiveData e atualiza a UI de acordo.
     */
    private fun observe() {
        mUniversidadesViewModel.universidades.observe(this){
            listaDeUniversidades = it
            val listaNomesUniversidades = mutableListOf<String>()
            for(universidade in it){
                listaNomesUniversidades.add(universidade.nome)
            }
            if(it.isNotEmpty()){
                val adapter = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, listaNomesUniversidades.distinct()
                )
                binding.insUniversidadeActivityCadastro.setAdapter(adapter)
            }
        }
        mUniversidadesViewModel.failureList.observe(this){
            PopUpMethods.toastLong(this, "Falha ao obter universidades")
        }
        mCursosViewModel.cursos.observe(this){
            listaDeCursos = it
            val listaNomesCursos = mutableListOf<String>()
            for(universidade in it){
                listaNomesCursos.add(universidade.nome)
            }
            if(it.isNotEmpty()){
                val adapter = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, listaNomesCursos.distinct()
                )
                binding.insCursoActivityCadastro.setAdapter(adapter)
                binding.inputCursoActivityCadastro.visibility = View.VISIBLE
            } else {
                PopUpMethods.toastLong(this, "Não há cursos para a universidade ${binding.insUniversidadeActivityCadastro.text.toString()}")
            }
        }
        mCursosViewModel.failureList.observe(this){
            PopUpMethods.toastLong(this, "Falha ao obter cursos da universidade ${binding.insUniversidadeActivityCadastro.text.toString()}")
        }
        mCadastroAlunosViewModel.userRegistration.observe(this){
            if(it){
                PopUpMethods.toastLong(this, "Sucesso ao cadastrar!")
                super.onBackPressed()
            }
        }
        mCadastroAlunosViewModel.failureUserRegistration.observe(this){
            PopUpMethods.toastLong(this, "Falha ao cadastrar usuário")
        }
    }
}