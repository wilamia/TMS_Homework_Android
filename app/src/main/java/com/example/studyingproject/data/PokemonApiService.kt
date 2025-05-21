package com.example.studyingproject.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class PokemonApiService(private val apiKey: String) {

    suspend fun fetchPokemonList(pageSize: Int): List<Pokemon> = withContext(Dispatchers.IO) {
        val pokemonList = mutableListOf<Pokemon>()
        val url = URL("https://api.pokemontcg.io/v2/cards?pageSize=$pageSize&set.name=generations&subtypes=mega")

        val connection = url.openConnection() as HttpsURLConnection

        try {
            connection.requestMethod = "GET"
            connection.setRequestProperty("X-Api-Key", apiKey)

            val responseCode = connection.responseCode
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.use { it.readText() }
                val jsonResponse = JSONObject(response)
                val dataArray = jsonResponse.getJSONArray("data")

                for (i in 0 until dataArray.length()) {
                    val card = dataArray.getJSONObject(i)
                    val images = card.getJSONObject("images")
                    val name = card.getString("name")
                    val imageUrl = images.getString("large")
                    pokemonList.add(Pokemon(name, imageUrl))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }

        return@withContext pokemonList
    }
}
