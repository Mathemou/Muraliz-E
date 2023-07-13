package com.example.muralize.Data

import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Disciplina

/**
 * Interface que define métodos de retorno para a obtenção de disciplinas.
 */
interface DisciplinasCallback {
    /**
     * Função chamada quando a obtenção de disciplinas é concluída com sucesso.
     *
     * @param disciplinas Lista de objetos Disciplina representando as disciplinas obtidas.
     */
    fun onSuccess(disciplinas : List<Disciplina>)

    /**
     * Função chamada quando ocorre uma falha na obtenção de disciplinas.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}