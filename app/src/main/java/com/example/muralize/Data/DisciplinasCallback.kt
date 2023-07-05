package com.example.muralize.Data

import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Disciplina

interface DisciplinasCallback {
    fun onSuccess(disciplinas : List<Disciplina>)
    fun onFailure(message: String)
}