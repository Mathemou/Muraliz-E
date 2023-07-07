package com.example.muralize.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.muralize.R
import com.example.muralize.Utils.CheckMethods
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.ViewModels.LoginViewModel
import com.example.muralize.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
// binding para acessar os componentes
private  lateinit var binding: ActivityLoginBinding
private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // infla o binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        observer()

        binding.btnLoginActivityLogin.setOnClickListener {
            val emailDoUsuario : String = binding.insNicknameActivityLogin.text.toString()
            val senhaDoUsuario : String = binding.insSenhaActivityLogin.text.toString()
            if(CheckMethods.naoVazio(emailDoUsuario)&&CheckMethods.naoVazio(senhaDoUsuario)) {
                checaUsuario(emailDoUsuario, senhaDoUsuario)
            } else {
                PopUpMethods.SnackbarLong(it, "Preencha todos os campos!")
            }
        }
        binding.btnCadastroActivityLogin.setOnClickListener{
            abreTelaCadastro()
        }
    }

    /**
     * Realiza a verificação do usuário usando o email e a senha fornecidos.
     *
     * @param emailDoUsuario O email do usuário.
     * @param senhaDoUsuario A senha do usuário.
     */
    private fun checaUsuario(emailDoUsuario: String, senhaDoUsuario: String) {
        mLoginViewModel.loginUser(emailDoUsuario, senhaDoUsuario)
    }


    /**
     * Configura os observadores para ações relacionadas ao login do usuário.
     */
    private fun observer() {
        mLoginViewModel.loginUser.observe(this) {
            if (it) {
                abreTelaMenu()
            } else {
                PopUpMethods.toastLong(this, getString(R.string.failure_login))
            }
        }
        mLoginViewModel.failureLogin.observe(this) {
            if (it.isNotEmpty()) {
                PopUpMethods.toastShort(this, it)
            }
        }
    }


    /**
     * Abre a tela de cadastro de aluno.
     */
    private fun abreTelaCadastro() {
        val intent = Intent(this, CadastroAluno::class.java)
        startActivity(intent)
    }


    /**
     * Abre a tela do menu inicial.
     */
    private fun abreTelaMenu() {
        val intent = Intent(this, MenuInicial::class.java)
        startActivity(intent)
    }

}