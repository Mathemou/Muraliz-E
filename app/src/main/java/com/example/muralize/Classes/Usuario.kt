package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference

data class Usuario(
    val nome : String = "",
    val telefone : String = "",
    val universidade : DocumentReference? = null,
    val curso : DocumentReference? = null,
    val disciplinas: List<DocumentReference>? = null
)
