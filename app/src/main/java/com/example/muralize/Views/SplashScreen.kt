package com.example.muralize.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.Utils.SessionManager
import com.example.muralize.ViewModels.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    private lateinit var mLoginViewModel : LoginViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        sessionManager = SessionManager(this)
        Handler().postDelayed({
            // Código a ser executado após o atraso
            checkCurrentUser()
        }, 2500)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            // Código a ser executado após o atraso
            checkCurrentUser()
        }, 2500)
    }

    private fun checkCurrentUser() {
        // Checa se o usuário já está logado
        val currentUser = Firebase.auth.currentUser

        if (currentUser != null) {
            finish()
            val intent =Intent(this, MenuInicial::class.java)
            startActivity(intent)
        } else {
            finish()
            sessionManager.salvarNomeUsuario(null)
            sessionManager.salvarEmailPerfil(null)
            sessionManager.salvarTelefone(null)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}