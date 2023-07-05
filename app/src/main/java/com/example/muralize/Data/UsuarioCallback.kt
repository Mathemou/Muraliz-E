package com.example.muralize.Data

import com.example.muralize.Classes.Usuario

interface UsuarioCallback {
    fun onSuccess(usuario: Usuario)
    fun onFailure(message: String)
}
