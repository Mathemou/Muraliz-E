package com.example.muralize.Data

interface CadastroAlunoCallback {
    fun onSuccess(result: Boolean)
    fun onFailure(message: String)
}