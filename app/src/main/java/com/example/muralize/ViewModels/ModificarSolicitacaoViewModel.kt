package com.example.muralize.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.Data.ResolverSolicitacaoCallback
import com.example.muralize.Data.UpdateData
import com.google.firebase.firestore.FirebaseFirestore

/**
 * ViewModel responsável pela funcionalidade de modificação de solicitações.
 *
 * @param application A instância da aplicação.
 */
class ModificarSolicitacaoViewModel(application : Application) : AndroidViewModel(application)  {

    private var _modificarSolicitacao = MutableLiveData<Solicitacao>()
    val modificarSolicitacao: MutableLiveData<Solicitacao>
        get() = _modificarSolicitacao

    private var _failureModificarSolicitacao = MutableLiveData<String>()
    val failureModificarSolicitacao: MutableLiveData<String>
        get() = _failureModificarSolicitacao

    /**
     * Marca uma solicitação como resolvida.
     *
     * @param solicitacao A solicitação a ser marcada como resolvida.
     */
    fun marcarComoResolvida(solicitacao : Solicitacao){
        UpdateData.resolverSolicitacao(solicitacao, object : ResolverSolicitacaoCallback{
            override fun onSuccess(solicitacao: Solicitacao) {
                _modificarSolicitacao.value = solicitacao
            }
            override fun onFailure(message: String) {
                _failureModificarSolicitacao.value = message
            }
        })
    }
}