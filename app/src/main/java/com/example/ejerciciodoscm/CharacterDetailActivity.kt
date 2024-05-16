package com.example.ejerciciodoscm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_character)

        // Aquí puedes obtener el ID del personaje de los extras del intent
        val characterId = intent.getStringExtra("character_id")

        // Luego puedes usar este ID para obtener los detalles del personaje y mostrarlos en la actividad
    }
}
