package com.example.muralize.Data

/**
 * Interface que define métodos de retorno para a remoção de uma disciplina.
 */
interface RemoverDisciplinaCallback {
    /**
     * Função chamada quando a remoção da disciplina é concluída com sucesso.
     *
     * @param result Booleano indicando se a remoção foi bem-sucedida ou não.
     */
    fun onSuccess(result: Boolean)

    /**
     * Função chamada quando ocorre uma falha na remoção da disciplina.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}