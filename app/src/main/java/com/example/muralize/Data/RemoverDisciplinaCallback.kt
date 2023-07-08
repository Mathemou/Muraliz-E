package com.example.muralize.Data

interface RemoverDisciplinaCallback {
    fun onSuccess(result: Boolean)
    fun onFailure(message: String)
}