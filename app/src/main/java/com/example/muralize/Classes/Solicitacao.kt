package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference
import java.sql.Timestamp

data class Solicitacao (
    val disciplina : DocumentReference? = null,
    val aluno : DocumentReference? = null,
    val data : Timestamp? = null,
    val descricao : String = ""
)