package com.example.muralize.Views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.muralize.Adapters.ImagensAdapterLink
import com.example.muralize.Classes.Solicitacao
import com.example.muralize.R
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.databinding.ActivityRegistrarSolicitacaoBinding
import com.example.muralize.databinding.ActivitySolicitacaoExpandidaBinding
import com.example.muralize.databinding.SolicitacaoItemBinding

/**
 * Classe responsável por exibir a tela de visualização expandida de uma solicitação.
 */
class SolicitacaoExpandida : AppCompatActivity() {
    private lateinit var binding: ActivitySolicitacaoExpandidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySolicitacaoExpandidaBinding.inflate(layoutInflater)
        val DescricaoSolicitacao: String? = intent.getStringExtra("DescricaoSolicitacao")
        val NomeDisciplina: String? = intent.getStringExtra("NomeDisciplina")
        val NomeAluno: String? = intent.getStringExtra("NomeAluno")
        val TelefoneAluno : String? = intent.getStringExtra("TelefoneAluno")
        val ListaImagens : ArrayList<String> = intent.getStringArrayListExtra("ListaImagens") as ArrayList<String>
        binding.nomeMateriaSolicitacaoExpandida.text = NomeDisciplina
        binding.nomeAlunoSolicitacaoExpandida.text = NomeAluno
        binding.descricaoSolicitacaoExpandida.text = DescricaoSolicitacao
        binding.telefoneSolicitacaoExpandida.text = TelefoneAluno
        binding.imagensSolicitacaoGrandeRv.adapter = ImagensAdapterLink(this, ListaImagens)
        binding.imagensSolicitacaoGrandeRv.layoutManager = GridLayoutManager(this, 3)

        binding.buttonTelefoneSolicitacaoExpandida.setOnClickListener{
            abrirChatWhatsApp("+55" + binding.telefoneSolicitacaoExpandida.text.toString())
        }
        setContentView(binding.root)

    }

    /**
     * Abre o aplicativo do WhatsApp para iniciar um chat com o número de telefone fornecido.
     *
     * @param numeroTelefone O número de telefone para o qual o chat será iniciado.
     */
    fun abrirChatWhatsApp(numeroTelefone: String) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numeroTelefone")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}