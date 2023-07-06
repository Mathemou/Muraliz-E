package com.example.muralize.Data

import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Solicitacao

interface SolicitacaoCallback {
    fun onSuccess(cursos : List<Solicitacao>)
    fun onFailure(message: String)
}