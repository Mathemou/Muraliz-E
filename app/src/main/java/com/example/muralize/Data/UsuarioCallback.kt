package com.example.muralize.Data

import com.example.muralize.Classes.Usuario

interface UserObjectCallback {
    fun onSuccess(user: Usuario)
    fun onFailure(message: String)
}
