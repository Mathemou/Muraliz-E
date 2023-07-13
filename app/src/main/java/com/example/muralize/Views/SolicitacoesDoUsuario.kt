package com.example.muralize.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.Adapters.DisciplinasAdapter
import com.example.muralize.Adapters.SolicitacoesAdapter
import com.example.muralize.Adapters.SolicitacoesGrandeAdapter
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.ViewModels.ModificarSolicitacaoViewModel
import com.example.muralize.ViewModels.ObterDadosUsuarioViewModel
import com.example.muralize.ViewModels.ObterSolicitacoesViewModel
import com.example.muralize.databinding.ActivitySolicitacoesDoUsuarioBinding

/**
 * Classe responsável por exibir as solicitações do usuário logado.
 */
class SolicitacoesDoUsuario : AppCompatActivity() {
    private lateinit var binding : ActivitySolicitacoesDoUsuarioBinding
    private lateinit var mObterSolicitacoesViewModel: ObterSolicitacoesViewModel
    private lateinit var mObterDadosUsuarioViewModel: ObterDadosUsuarioViewModel
    private lateinit var mModificarSolicitacaoViewModel: ModificarSolicitacaoViewModel
    private lateinit var solicitacoes : List<Solicitacao>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolicitacoesDoUsuarioBinding.inflate(layoutInflater)
        mObterDadosUsuarioViewModel = ViewModelProvider(this)[ObterDadosUsuarioViewModel::class.java]
        mObterSolicitacoesViewModel = ViewModelProvider(this)[ObterSolicitacoesViewModel::class.java]
        mModificarSolicitacaoViewModel = ViewModelProvider(this)[ModificarSolicitacaoViewModel::class.java]
        observe()
        mObterDadosUsuarioViewModel.obterUsuarioLogado()
        setContentView(binding.root)
    }

    /**
     * Configura os observadores para ações relacionadas às solicitações do usuário.
     */
    private fun observe() {
        mObterDadosUsuarioViewModel.currentUser.observe(this){ usuario ->
            mObterSolicitacoesViewModel.obterSolicitacoesDoUsuario(usuario)
        }
        mObterSolicitacoesViewModel.solicitacoes.observe(this){ listaSolicitacoes ->
            solicitacoes = listaSolicitacoes
            binding.recyclerViewListaSolicitacoes.adapter = SolicitacoesGrandeAdapter(solicitacoes.toMutableList(), mModificarSolicitacaoViewModel)
            binding.recyclerViewListaSolicitacoes.layoutManager = LinearLayoutManager(this)
        }
        mModificarSolicitacaoViewModel.modificarSolicitacao.observe(this){ solicitacaoResolvida ->
            PopUpMethods.toastLong(this, "Solicitacão resolvida")
            val rv = findViewById<RecyclerView>(R.id.recycler_view_lista_solicitacoes)
            val solicitacoesAdapter = rv.adapter as? SolicitacoesGrandeAdapter
            val solicitacaoRemover = solicitacoes.firstOrNull { it == solicitacaoResolvida }

            solicitacaoRemover?.let {
                val position = solicitacoes.indexOf(it)
                (solicitacoes as MutableList<*>).remove(it)
                solicitacoesAdapter?.removerSolicitacao(position)
            }
        }
    }
}