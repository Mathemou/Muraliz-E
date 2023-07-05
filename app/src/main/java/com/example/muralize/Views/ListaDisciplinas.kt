package com.example.muralize.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Adapters.DisciplinasAdapter
import com.example.muralize.Classes.Disciplina
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.ViewModels.AdicionarDisciplinasViewModel
import com.example.muralize.ViewModels.ObterDadosUsuarioViewModel
import com.example.muralize.ViewModels.ObterDisciplinasViewModel
import com.example.muralize.databinding.ActivityListaDisciplinasBinding

class ListaDisciplinas : AppCompatActivity() {
    private lateinit var binding : ActivityListaDisciplinasBinding
    private lateinit var mDisciplinasViewModel : ObterDisciplinasViewModel
    private lateinit var mObterDadosUsuarioViewModel : ObterDadosUsuarioViewModel
    lateinit var mAdicionarDisciplinasViewModel : AdicionarDisciplinasViewModel
    private lateinit var disciplinas : List<Disciplina>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_disciplinas)
        binding = ActivityListaDisciplinasBinding.inflate(layoutInflater)
        mObterDadosUsuarioViewModel = ViewModelProvider(this)[ObterDadosUsuarioViewModel::class.java]
        mDisciplinasViewModel = ViewModelProvider(this)[ObterDisciplinasViewModel::class.java]
        mAdicionarDisciplinasViewModel = ViewModelProvider(this)[AdicionarDisciplinasViewModel::class.java]
        observe()
        mObterDadosUsuarioViewModel.obterUsuarioLogado()
    }



    fun observe(){
        mObterDadosUsuarioViewModel.currentUser.observe(this){ usuario ->
            mDisciplinasViewModel.obterDisciplinasNaoCursadas(usuario)
        }
        mDisciplinasViewModel.disciplinas.observe(this){ disciplinasRetornadas ->
            disciplinas = disciplinasRetornadas
            val rv = findViewById<RecyclerView>(R.id.recycler_view_lista_disciplinas)
            rv.adapter = DisciplinasAdapter(disciplinas.toMutableList(), mAdicionarDisciplinasViewModel)
            rv.layoutManager = LinearLayoutManager(this)
        }
        mAdicionarDisciplinasViewModel.cadastrarDisciplina.observe(this) { disciplinaCadastrada ->
            PopUpMethods.toastLong(this, "Sucesso ao cadastrar disciplina ${disciplinaCadastrada.nome}!")
            val rv = findViewById<RecyclerView>(R.id.recycler_view_lista_disciplinas)
            val disciplinasAdapter = rv.adapter as? DisciplinasAdapter
            val disciplinaRemover = disciplinas.firstOrNull { it == disciplinaCadastrada }

            disciplinaRemover?.let {
                val position = disciplinas.indexOf(it)
                (disciplinas as MutableList<*>).remove(it)
                disciplinasAdapter?.removerDisciplina(position)
            }
        }

        mAdicionarDisciplinasViewModel.failureCadastrarDisciplina.observe(this){
            PopUpMethods.toastLong(this, "Falha ao cadastrar disciplina")
        }
    }
}