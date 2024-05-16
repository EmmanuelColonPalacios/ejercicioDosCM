package com.example.ejerciciodoscm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejerciciodoscm.databinding.ActivityMainBinding
import com.example.ejerciciodoscm.databinding.ItemCharacterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ejerciciodoscm.CharacterAdapter
import com.example.ejerciciodoscm.AvatarApiService
import com.example.ejerciciodoscm.AvatarApiClient
import com.example.ejerciciodoscm.Character
import com.example.ejerciciodoscm.R
import com.example.ejerciciodoscm.CharacterDetailActivity

class MainActivity : ComponentActivity(), CharacterAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var apiService: AvatarApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar splash screen
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Splash screen
            setupViews()
            getCharacters()
        }
    }

    private fun setupViews() {
        // Inflar layout principal
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Retrofit y la API service
        apiService = AvatarApiClient.service

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        characterAdapter = CharacterAdapter(this)
        binding.recyclerView.adapter = characterAdapter
    }

    private fun getCharacters() {
        apiService.getCharacters().enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                if (response.isSuccessful) {
                    val characters = response.body()
                    characters?.let {
                        characterAdapter.submitList(it)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Error al obtener los personajes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(character: Character) {
        val intent = Intent(this, Character::class.java).apply {
            putExtra("character_id", character.id)
        }
        startActivity(intent)
    }
}
