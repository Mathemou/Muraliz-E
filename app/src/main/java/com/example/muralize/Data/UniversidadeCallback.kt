package com.example.muralize.Data

import com.example.muralize.Classes.Universidade
import com.example.muralize.Classes.Usuario
import com.google.firebase.firestore.QuerySnapshot

/**
 * Interface que define métodos de retorno para a obtenção de universidades.
 */
interface UniversidadeCallback {
    /**
     * Função chamada quando a obtenção de universidades é concluída com sucesso.
     *
     * @param universidades Lista de objetos Universidade representando as universidades obtidas.
     */
    fun onSuccess(universidades : List<Universidade>)

    /**
     * Função chamada quando ocorre uma falha na obtenção de universidades.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}