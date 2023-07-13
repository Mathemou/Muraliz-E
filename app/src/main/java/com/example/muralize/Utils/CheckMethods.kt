package com.example.muralize.Utils

/**
 * Classe que contém métodos utilitários para verificar se uma string não está vazia.
 */
class CheckMethods {
    companion object {
        /**
         * Verifica se uma string não está vazia.
         *
         * @param s A string a ser verificada.
         * @return Retorna true se a string não estiver vazia, caso contrário, retorna false.
         */
        fun naoVazio(s: String): Boolean {
            return s != ""
        }
    }
}