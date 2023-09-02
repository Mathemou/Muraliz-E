package com.example.muralize.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.R
import com.example.muralize.Views.ImagemExpandida

class ImagensAdapter(val context : Context, val listaImagens: MutableList<Uri>) : RecyclerView.Adapter<ImagensAdapter.ImagensViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagensAdapter.ImagensViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.imagem_item, parent, false)
        return ImagensViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ImagensAdapter.ImagensViewHolder,
        position: Int
    ) {
        val image = listaImagens[position]
        holder.imageView.setImageURI(image)
    }

    override fun getItemCount(): Int {
        return listaImagens.size
    }

    inner class ImagensViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById<CardView>(R.id.card_view_image_solicitacao)
        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageview_solicitacoes)
        val removeImage : ImageView = itemView.findViewById<ImageView>(R.id.imageview_remove_image_solicitacoes)
        init {
            removeImage.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Remova a imagem da lista de imagens na posição correspondente
                    listaImagens.removeAt(position)
                    // Notifique o adaptador sobre a remoção do item
                    notifyItemRemoved(position)
                }
            }

            imageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Recupere a imagem na posição clicada
                    val image = listaImagens[position]
                    Log.d("ImagemExpandidaLogAdapter", image.toString())
                    // Abra a atividade ou fragmento de tela cheia para exibir a imagem
                    val intent = Intent(itemView.context, ImagemExpandida::class.java)
                    intent.putExtra("image", image.toString()) // Envie a URI da imagem para a tela cheia
                    intent.putExtra("renderType", "Uri") // Envia o tipo de renderização que a imagem deve ser executado
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}