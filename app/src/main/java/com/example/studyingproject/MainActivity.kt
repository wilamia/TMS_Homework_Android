package com.example.studyingproject

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.studyingproject.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        getImage()
    }
/*{
    "message": "Powered by random-d.uk",
    "url": "https://random-d.uk/api/385.jpg"
}*/
    fun getImage() {
        binding.button.setOnClickListener {
//            lifecycleScope.launch(Dispatchers.IO) {
//                val url = URL("https://random-d.uk/api/random")
//                val connection = url.openConnection() as HttpsURLConnection
//                try {
//                    connection.requestMethod = "GET"
//                    val responseCode = connection.responseCode
//                    if(responseCode == HttpsURLConnection.HTTP_OK) {
//                        val reader = BufferedReader(InputStreamReader(connection.inputStream))
//                        val response = reader.use { it.readText() }
//                        val jsonResponse = JSONObject(response)
//                        val urlImage = jsonResponse.getString("url")
//                        Log.d("MYTAG", urlImage)
//                        val input = URL(urlImage).openStream()
//                        val bitmap = BitmapFactory.decodeStream(input)
//                        withContext(Dispatchers.Main) {
//                            binding.imageView.setImageBitmap(bitmap)
//                        }
//                    } else if (responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED) {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(parent, "Вы не авторизованы!", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                } finally {
//                    connection.disconnect()
//                }
//            }
            val id = Random.nextInt(1..1000)
            lifecycleScope.launch(Dispatchers.IO) {
                val url = URL("https://kinopoiskapiunofficial.tech/api/v2.2/films/$id")
                val connection = url.openConnection() as HttpsURLConnection
                try {
                    connection.requestMethod = "GET"
                    connection.setRequestProperty("X-API-KEY", "a859dd6e-d0ce-472a-8adb-9ab958f0014b")
                    val responseCode = connection.responseCode
                    if(responseCode == HttpsURLConnection.HTTP_OK) {
                        val reader = BufferedReader(InputStreamReader(connection.inputStream))
                        val response = reader.use { it.readText() }
                        val jsonResponse = JSONObject(response)
                        val countries: MutableList<Country> = mutableListOf()
                        val getCountries = jsonResponse.getJSONArray("countries")
                        countries.add(Country(getCountries.getString(1)))
                        Log.d("MYTAG", countries.toString())
                        val urlImage = jsonResponse.getString("posterUrl")
                        Log.d("MYTAG", urlImage)
                        val input = URL(urlImage).openStream()
                        val bitmap = BitmapFactory.decodeStream(input)
                        withContext(Dispatchers.Main) {
                            binding.imageView.setImageBitmap(bitmap)
                        }
                    } else if (responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@MainActivity, "Вы не авторизованы!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    connection.disconnect()
                }
            }

        }
    }
}