package com.example.muralize.Data

interface RegistrarSolicitacaoCallback {
    fun onSuccess(result: Boolean)
    fun onFailure(message: String)
}