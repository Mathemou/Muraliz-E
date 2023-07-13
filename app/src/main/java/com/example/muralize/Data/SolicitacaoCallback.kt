package com.example.muralize.Data

import com.example.muralize.Classes.Curso
import com.example.muralize.Classes.Solicitacao

/**
 * Interface que define métodos de retorno para a obtenção de solicitações.
 */
interface SolicitacaoCallback {
    /**
     * Função chamada quando a obtenção de solicitações é concluída com sucesso.
     *
     * @param solicitacoes Lista de objetos Solicitacao representando as solicitações obtidas.
     */
    fun onSuccess(solicitacoes : List<Solicitacao>)

    /**
     * Função chamada quando ocorre uma falha na obtenção de solicitações.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}