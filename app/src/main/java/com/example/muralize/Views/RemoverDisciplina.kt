package com.example.muralize.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Adapters.DisciplinasAdapter
import com.example.muralize.Adapters.RemoverDisciplinasAdapter
import com.example.muralize.Classes.Disciplina
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.ViewModels.AdicionarDisciplinasViewModel
import com.example.muralize.ViewModels.ObterDadosUsuarioViewModel
import com.example.muralize.ViewModels.ObterDisciplinasViewModel
import com.example.muralize.ViewModels.RemoverDisciplinasViewModel
import com.example.muralize.databinding.ActivityListaDisciplinasBinding
import com.example.muralize.databinding.ActivityRemoverDisciplinaBinding

class RemoverDisciplina : AppCompatActivity() {
    private lateinit var binding : ActivityRemoverDisciplinaBinding
    private lateinit var mDisciplinasViewModel : ObterDisciplinasViewModel
    private lateinit var mObterDadosUsuarioViewModel : ObterDadosUsuarioViewModel
    lateinit var mRemoverDisciplinasViewModel : RemoverDisciplinasViewModel
    private lateinit var disciplinas : List<Disciplina>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemoverDisciplinaBinding.inflate(layoutInflater)
        mObterDadosUsuarioViewModel = ViewModelProvider(this)[ObterDadosUsuarioViewModel::class.java]
        mDisciplinasViewModel = ViewModelProvider(this)[ObterDisciplinasViewModel::class.java]
        mRemoverDisciplinasViewModel = ViewModelProvider(this)[RemoverDisciplinasViewModel::class.java]
        observe()
        mObterDadosUsuarioViewModel.obterUsuarioLogado()
        setContentView(binding.root)
    }



    fun observe(){
        mObterDadosUsuarioViewModel.currentUser.observe(this){ usuario ->
            mDisciplinasViewModel.obterDisciplinasCursadas(usuario)
        }
        mDisciplinasViewModel.disciplinas.observe(this){ disciplinasRetornadas ->
            disciplinas = disciplinasRetornadas
            val rv = findViewById<RecyclerView>(R.id.recycler_view_remover_disciplinas)
            rv.adapter = RemoverDisciplinasAdapter(disciplinas.toMutableList(), mRemoverDisciplinasViewModel)
            rv.layoutManager = LinearLayoutManager(this)
        }
        mRemoverDisciplinasViewModel.removerDisciplina.observe(this) { disciplinaRemovida ->
            PopUpMethods.toastLong(this, "Sucesso ao remover disciplina ${disciplinaRemovida.nome}!")
            val rv = findViewById<RecyclerView>(R.id.recycler_view_remover_disciplinas)
            val disciplinasAdapter = rv.adapter as? RemoverDisciplinasAdapter
            val disciplinaRemover = disciplinas.firstOrNull { it == disciplinaRemovida }

            disciplinaRemover?.let {
                val position = disciplinas.indexOf(it)
                (disciplinas as MutableList<*>).remove(it)
                disciplinasAdapter?.removerDisciplina(position)
            }
        }

        mRemoverDisciplinasViewModel.failureRemoverDisciplina.observe(this){
            PopUpMethods.toastLong(this, "Falha ao remover disciplina")
        }
    }
}