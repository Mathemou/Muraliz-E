package com.example.muralize.Data

/**
 * Interface que define métodos de retorno para o cadastro de um aluno.
 */
interface CadastroAlunoCallback {
    /**
     * Função chamada quando o cadastro é concluído com sucesso.
     *
     * @param result Booleano indicando se o cadastro foi bem-sucedido ou não.
     */
    fun onSuccess(result: Boolean)
    /**
     * Função chamada quando ocorre uma falha no cadastro.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}