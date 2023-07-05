package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

data class Curso(
    val nome : String = "",
    var documento : DocumentReference? = null
)
