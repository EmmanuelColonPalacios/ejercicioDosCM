import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejerciciodoscm.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CharacterAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var apiService: AvatarApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar splash screen
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener {
            // Splash screen
            setContentView(R.layout.activity_main) // Inflar layout después del splash
            setupViews()
            getCharacters()
        }
    }

    private fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Retrofit y la API service
        apiService = AvatarApiClient.create()

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        characterAdapter = CharacterAdapter(emptyList(), this)
        binding.recyclerView.adapter = characterAdapter
    }

    private fun getCharacters() {
        apiService.getCharacters().enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                if (response.isSuccessful) {
                    val characters = response.body()
                    characters?.let {
                        characterAdapter = CharacterAdapter(it, this@MainActivity)
                        binding.recyclerView.adapter = characterAdapter
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
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra("character_id", character.id)
        }
        startActivity(intent)
    }
}
