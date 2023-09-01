package com.example.muralize.Adapters

import android.content.Context
import android.net.Uri
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.muralize.R

class ImagensAdapterLink(val context : Context, val listaImagens: ArrayList<String>) : RecyclerView.Adapter<ImagensAdapterLink.ImagensLinkViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagensAdapterLink.ImagensLinkViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.imagem_item, parent, false)
        return ImagensLinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImagensLinkViewHolder, position: Int) {
        val imagem = listaImagens[position]
        // Inicializa com glider
        Glide.with(context) // Use 'this' se estiver dentro de uma atividade, ou 'context' se estiver em um contexto diferente.
            .load(imagem) // Carrega a imagem da URL especificada
            .into(holder.imageView) // Exibe a imagem no ImageView 'imageView'
        holder.removeImage.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return listaImagens.size
    }

    inner class ImagensLinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById<CardView>(R.id.card_view_image_solicitacao)
        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageview_solicitacoes)
        val removeImage : ImageView = itemView.findViewById<ImageView>(R.id.imageview_remove_image_solicitacoes)
    }


}