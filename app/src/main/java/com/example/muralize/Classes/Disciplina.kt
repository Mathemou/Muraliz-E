package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference

/**
 * Classe que representa uma disciplina.
 *
 * @property nome O nome da disciplina.
 * @property codigo O código da disciplina.
 * @property documento A referência ao documento da disciplina.
 */
data class Disciplina(
    val nome : String = "",
    val codigo : String = "",
    var documento : DocumentReference? = null
)
