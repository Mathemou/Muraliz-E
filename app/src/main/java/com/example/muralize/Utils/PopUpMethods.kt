package com.example.muralize.Utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * Classe que contém métodos utilitários para exibir pop-ups, como Toasts e Snackbars.
 */
class PopUpMethods {
    companion object{
        /**
         * Exibe um Toast de longa duração.
         *
         * @param context O contexto da aplicação.
         * @param msg A mensagem a ser exibida no Toast.
         */
        fun toastLong(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        /**
         * Exibe um Toast de curta duração.
         *
         * @param context O contexto da aplicação.
         * @param msg A mensagem a ser exibida no Toast.
         */
        fun toastShort(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        /**
         * Exibe um Snackbar de longa duração.
         *
         * @param view A View em que o Snackbar será exibido.
         * @param text O texto a ser exibido no Snackbar.
         */
        fun SnackbarLong(view : View, text : String){
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        }

        /**
         * Exibe um Snackbar de curta duração.
         *
         * @param view A View em que o Snackbar será exibido.
         * @param text O texto a ser exibido no Snackbar.
         */
        fun SnackbarShort(view : View, text : String){
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        }
    }
}