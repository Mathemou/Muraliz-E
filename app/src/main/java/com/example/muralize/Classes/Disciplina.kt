package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference

data class Disciplina(
    val nome : String = "",
    val codigo : String = "",
    var documento : DocumentReference? = null
)
