package com.example.muralize.Data

import com.example.muralize.Classes.Curso

interface LoginCallback {
    fun onSuccess(resultado : Boolean)
    fun onFailure(message: String)
}