package com.example.muralize.Data

interface CadastroDisciplinaCallback {
    fun onSuccess(result: Boolean)
    fun onFailure(message: String)
}