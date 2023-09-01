package com.example.muralize.Adapters

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.muralize.R

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
        }
    }
}