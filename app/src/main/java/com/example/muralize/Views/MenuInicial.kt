package com.example.muralize.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muralize.Utils.PopUpMethods
import com.example.muralize.databinding.ActivityLoginBinding
import com.example.muralize.databinding.ActivityMenuInicialBinding

class MenuInicial : AppCompatActivity() {
    // binding para acessar os componentes
    private lateinit var binding : ActivityMenuInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // infla o binding
        binding = ActivityMenuInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAcaoTest.setOnClickListener {
            PopUpMethods.toastLong(this, "Não implementado!")
        }
    }
}