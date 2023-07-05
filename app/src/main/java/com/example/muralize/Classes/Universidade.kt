package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference

data class Universidade(
    val nome : String = "",
    val endereco : String = "",
    var documento : DocumentReference? = null
)
