package com.example.muralize.Views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.databinding.ActivityRegistrarSolicitacaoBinding
import com.example.muralize.databinding.ActivitySolicitacaoExpandidaBinding
import com.example.muralize.databinding.SolicitacaoItemBinding

class SolicitacaoExpandida : AppCompatActivity() {
    private lateinit var binding: ActivitySolicitacaoExpandidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolicitacaoExpandidaBinding.inflate(layoutInflater)
        val DescricaoSolicitacao: String? = intent.getStringExtra("DescricaoSolicitacao")
        val NomeDisciplina: String? = intent.getStringExtra("NomeDisciplina")
        val NomeAluno: String? = intent.getStringExtra("NomeAluno")
        val TelefoneAluno : String? = intent.getStringExtra("TelefoneAluno")

        binding.nomeAlunoSolicitacaoExpandida.text = NomeAluno
        binding.descricaoSolicitacaoExpandida.text = DescricaoSolicitacao
        binding.telefoneSolicitacaoExpandida.text = TelefoneAluno

        binding.buttonTelefoneSolicitacaoExpandida.setOnClickListener{
            abrirChatWhatsApp("+55" + binding.telefoneSolicitacaoExpandida.text.toString())
        }
        setContentView(binding.root)

    }

    fun abrirChatWhatsApp(numeroTelefone: String) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numeroTelefone")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}