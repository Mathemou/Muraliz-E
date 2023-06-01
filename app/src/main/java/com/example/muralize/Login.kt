package com.example.muralize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
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
                PopUpMethods.toastLong(this, "Nenhum campo pode estar vazio")
            }
        }
        binding.btnCadastroActivityLogin.setOnClickListener{
            abreTelaCadastro()
        }
    }

    private fun checaUsuario(nomeDeUsuario: String, senhaDoUsuario: String) {
        PopUpMethods.toastLong(this, "${nomeDeUsuario} e ${senhaDoUsuario} possíveis de entrar...")
        // Aqui deve ocorrer a verificação
        val intent = Intent(this, MenuInicial::class.java)
        startActivity(intent)
    }

    private fun abreTelaCadastro(){
        val intent = Intent(this, CadastroAluno::class.java)
        startActivity(intent)
    }
}