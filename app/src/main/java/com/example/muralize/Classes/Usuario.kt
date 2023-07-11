package com.example.muralize.Classes

import com.google.firebase.firestore.DocumentReference

/**
 * Classe que representa um usuário.
 *
 * @property nome O nome do usuário.
 * @property telefone O telefone do usuário.
 * @property universidade A referência ao documento da universidade do usuário.
 * @property curso A referência ao documento do curso do usuário.
 * @property disciplinas A lista de referências aos documentos das disciplinas do usuário.
 * @property documento A referência ao documento do usuário.
 */
data class Usuario(
    val nome : String = "",
    val telefone : String = "",
    val universidade : DocumentReference? = null,
    val curso : DocumentReference? = null,
    val disciplinas: List<DocumentReference> = emptyList(),
    var documento : DocumentReference? = null
)
