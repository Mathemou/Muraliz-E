package com.example.muralize.Data

import com.example.muralize.Classes.Curso
import com.google.firebase.firestore.QuerySnapshot

interface CursosCallback {
    fun onSuccess(cursos : List<Curso>)
    fun onFailure(message: String)
}