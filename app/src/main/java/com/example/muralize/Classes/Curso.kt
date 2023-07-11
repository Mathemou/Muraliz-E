package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

/**
 * Classe que representa um curso.
 *
 * @property nome O nome do curso.
 * @property documento A referência ao documento do curso.
 */
data class Curso(
    val nome : String = "",
    var documento : DocumentReference? = null
)
