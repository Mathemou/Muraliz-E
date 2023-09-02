package com.example.muralize.Views

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.muralize.R

class ImagemExpandida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_expandida)

        val image = intent.getStringExtra("image")
        val renderType = intent.getStringExtra("renderType")
        val imageViewFullScreen = findViewById<ImageView>(R.id.imageview_full_screen)

        if (image != null && renderType == "Uri") {
            imageViewFullScreen.setImageURI(Uri.parse(image))
        }

        if (image != null && renderType == "Link") {
            Glide.with(this) // Use 'this' se estiver dentro de uma atividade, ou 'context' se estiver em um contexto diferente.
                .load(image) // Carrega a imagem da URL especificada
                .into(imageViewFullScreen) // Exibe a imagem no ImageView 'imageView'
        }
    }
}