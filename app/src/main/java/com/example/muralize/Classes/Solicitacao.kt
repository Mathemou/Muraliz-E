package com.example.muralize.Classes

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

/**
 * Classe que representa uma solicitação.
 *
 * @property disciplina A referência ao documento da disciplina relacionada à solicitação.
 * @property aluno A referência ao documento do aluno que fez a solicitação.
 * @property data A data da solicitação.
 * @property descricao A descrição da solicitação.
 * @property documento A referência ao documento da solicitação.
 * @property resolvida Indica se a solicitação foi resolvida ou não.
 */
data class Solicitacao (
    val disciplina : DocumentReference? = null,
    val aluno : DocumentReference? = null,
    val data : Timestamp? = null,
    val descricao : String = "",
    var documento : DocumentReference? = null,
    var resolvida : Boolean = false
)