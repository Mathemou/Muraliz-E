package com.example.muralize.Utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class PopUpMethods {
    companion object{
        fun toastLong(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun toastShort(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun SnackbarLong(view : View, text : String){
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        }

        fun SnackbarShort(view : View, text : String){
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        }
    }
}