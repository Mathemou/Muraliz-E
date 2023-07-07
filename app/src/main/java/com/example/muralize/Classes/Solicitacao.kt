package com.example.muralize.Classes

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

data class Solicitacao (
    val disciplina : DocumentReference? = null,
    val aluno : DocumentReference? = null,
    val data : Timestamp? = null,
    val descricao : String = "",
    var documento : DocumentReference? = null,
    var resolvida : Boolean = false
)