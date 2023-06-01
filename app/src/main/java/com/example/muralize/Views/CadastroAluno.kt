package com.example.muralize.Views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.databinding.ActivityCadastroAlunoBinding
import com.google.android.material.snackbar.Snackbar

class CadastroAluno : AppCompatActivity() {
    private lateinit var binding : ActivityCadastroAlunoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroAlunoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBuscarUniversidadeActivityCadastro.setOnClickListener{
            
        }
        binding.btnCadastrarActivityCadastro.setOnClickListener{
            val nome_completo = binding.insNomeActivityCadastro.text.toString()
            val numero_telefone = binding.insTelefoneActivityCadastro.text.toString()
            val email = binding.insEmailActivityCadastro.text.toString()
            val universidade = binding.insUniversidadeActivityCadastro.text.toString()
            val curso = binding.insCursoActivityCadastro.text.toString()
            val numero_matricula = binding.insNumeroDeMatriculaActivityCadastro.text.toString()
            val nickname = binding.insNicknameActivityCadastro.text.toString()
            val senha = binding.insSenhaActivityCadastro.text.toString()
            if(nome_completo.isEmpty() || numero_telefone.isEmpty() || email.isEmpty() || universidade.isEmpty() || curso.isEmpty() || numero_matricula.isEmpty() || nickname.isEmpty() || senha.isEmpty()){
                PopUpMethods.SnackbarLong(it, "Preencha todos os campos!")
            } else {
                // Cadastra
            }
        }
    }
}