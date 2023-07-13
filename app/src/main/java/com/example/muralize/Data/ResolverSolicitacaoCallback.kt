package com.example.muralize.Data

import com.example.muralize.Classes.Solicitacao

/**
 * Interface que define métodos de retorno para a resolução de uma solicitação.
 */
interface ResolverSolicitacaoCallback {
    /**
     * Função chamada quando a resolução da solicitação é concluída com sucesso.
     *
     * @param solicitacao Objeto Solicitacao representando a solicitação resolvida.
     */
    fun onSuccess(solicitacao: Solicitacao)

    /**
     * Função chamada quando ocorre uma falha na resolução da solicitação.
     *
     * @param message Mensagem de erro descrevendo o motivo da falha.
     */
    fun onFailure(message: String)
}