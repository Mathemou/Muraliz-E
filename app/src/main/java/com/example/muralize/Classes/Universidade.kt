package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference

/**
 * Classe que representa uma universidade.
 *
 * @property nome O nome da universidade.
 * @property endereco O endereço da universidade.
 * @property documento A referência ao documento da universidade.
 */
data class Universidade(
    val nome : String = "",
    val endereco : String = "",
    var documento : DocumentReference? = null
)
