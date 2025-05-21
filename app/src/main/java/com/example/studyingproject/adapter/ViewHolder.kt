package com.example.studyingproject.adapter

import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.databinding.ViewHolderTemplateBinding
import com.example.studyingproject.data.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ViewHolder(private val binding: ViewHolderTemplateBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(pokemon: Pokemon) {
        loadImageFromUrl(pokemon.url)
    }
    private fun loadImageFromUrl(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val input = URL(url).openStream()
                val bitmap = BitmapFactory.decodeStream(input)
                launch(Dispatchers.Main) {
                    binding.imageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
