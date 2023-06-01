package com.example.muralize.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muralize.Utils.CheckMethods
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
// binding para acessar os componentes
private  lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // infla o binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLoginActivityLogin.setOnClickListener {
            val nomeDeUsuario : String = binding.insNicknameActivityLogin.text.toString()
            val senhaDoUsuario : String = binding.insSenhaActivityLogin.text.toString()
            if(CheckMethods.naoVazio(nomeDeUsuario)&&CheckMethods.naoVazio(senhaDoUsuario)) {
                checaUsuario(nomeDeUsuario, senhaDoUsuario)
            } else {
                PopUpMethods.SnackbarLong(it, "Preencha todos os campos!")
            }
        }
        binding.btnCadastroActivityLogin.setOnClickListener{
            abreTelaCadastro()
        }
    }

    private fun checaUsuario(nomeDeUsuario: String, senhaDoUsuario: String) {
        // Aqui deve ocorrer a verificação
        val intent = Intent(this, MenuInicial::class.java)
        startActivity(intent)
    }

    private fun abreTelaCadastro(){
        val intent = Intent(this, CadastroAluno::class.java)
        startActivity(intent)
    }
}