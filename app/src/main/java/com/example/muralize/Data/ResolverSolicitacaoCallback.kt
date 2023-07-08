package com.example.muralize.Data

import com.example.muralize.Classes.Solicitacao

interface ResolverSolicitacaoCallback {
    fun onSuccess(solicitacao: Solicitacao)
    fun onFailure(message: String)
}