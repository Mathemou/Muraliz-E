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

/**
 * Activity de splash screen exibida durante o carregamento inicial do aplicativo.
 * Verifica se o usuário está logado e redireciona para a tela adequada.
 */
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
        // Obtém as flags do sistema para a View do decorView
        val decorView = window.decorView
        val flags = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

// Define as flags do sistema na View do decorView
        decorView.systemUiVisibility = flags

    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            // Código a ser executado após o atraso
            checkCurrentUser()
        }, 2500)
    }

    /**
     * Verifica se o usuário está logado e redireciona para a tela adequada.
     */
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