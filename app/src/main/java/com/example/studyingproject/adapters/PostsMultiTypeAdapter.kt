package com.example.studyingproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.classes.Posts
import com.example.studyingproject.classes.Posts.*
import com.example.studyingproject.databinding.AuthorLayoutBinding
import com.example.studyingproject.databinding.ImageLayoutBinding
import com.example.studyingproject.databinding.TextPostLayoutBinding

/*Задачи
Задача 1: Экран со списоком различных видов постов. Экран представляет из себя список из различных элементов.
Возможные элементы:
Имя Автора и Текст под ним
Картинка и текст под ней
Текст и кнопка под ней
Должна быть возможность обновить список по кнопке
Использовать diffUtil для расчета обновлений
*использовать SwipeRefreshLayout при желании усложнить*/

class PostsMultiTypeAdapter(private var data: List<Posts>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_AUTHOR_POST = 0
        private const val TYPE_IMAGE_POST = 1
        private const val TYPE_TEXT_POST = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is Author -> TYPE_AUTHOR_POST
            is Image -> TYPE_IMAGE_POST
            is Text -> TYPE_TEXT_POST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_AUTHOR_POST -> {
                val binding = AuthorLayoutBinding.inflate(layoutInflater, parent, false)
                AuthorViewHolder(binding)
            }

            TYPE_IMAGE_POST -> {
                val binding = ImageLayoutBinding.inflate(layoutInflater, parent, false)
                ImageViewHolder(binding)
            }

            TYPE_TEXT_POST -> {
                val binding = TextPostLayoutBinding.inflate(layoutInflater, parent, false)
                TextPostViewHolder(binding)
            }

            else -> {
                return throw IllegalStateException("")
            }
        }
    }

    class AuthorViewHolder(private val binding: AuthorLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Author) {
            binding.author.text = item.author
            binding.textView.text = item.text
        }
    }

    class ImageViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            binding.imageView.setImageDrawable(item.image)
            binding.text.text = item.text
        }
    }

    class TextPostViewHolder(private val binding: TextPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Text) {
            binding.text.text = item.text
            binding.postBtn.text = item.btnName
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AuthorViewHolder -> holder.bind(data[position] as Author)
            is ImageViewHolder -> holder.bind(data[position] as Image)
            is TextPostViewHolder -> holder.bind(data[position] as Text)
        }
    }

    fun updateView(list: List<Posts>) {
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilCallback(list, oldData = data))
        data = list
        diffUtilResult.dispatchUpdatesTo(this)
    }
}