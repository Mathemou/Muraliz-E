package com.example.muralize.Data

import com.example.muralize.Classes.Curso
import com.google.firebase.firestore.QuerySnapshot

/**
 * Interface que define métodos de retorno para a obtenção de cursos.
 */
interface CursosCallback {
    /**
     * Função chamada quando a obtenção de cursos é concluída com sucesso.
     *
     * @param cursos Lista de objetos Curso representando os cursos obtidos.
     */
    fun onSuccess(cursos : List<Curso>)

    /**
     * Função chamada quando ocorre uma falha na obtenção de cursos.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}