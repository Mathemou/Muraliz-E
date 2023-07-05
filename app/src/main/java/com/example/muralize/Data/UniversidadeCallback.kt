package com.example.muralize.Data

import com.example.muralize.Classes.Universidade
import com.example.muralize.Classes.Usuario
import com.google.firebase.firestore.QuerySnapshot

interface UniversidadeCallback {
    fun onSuccess(universidades : List<Universidade>)
    fun onFailure(message: String)
}