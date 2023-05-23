package com.example.muralize.Utils

import android.content.Context
import android.widget.Toast

class PopUpMethods {
    companion object{
        fun toastLong(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun toastShort(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}