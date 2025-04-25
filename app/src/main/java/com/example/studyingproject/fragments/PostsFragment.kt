package com.example.studyingproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyingproject.R
import com.example.studyingproject.adapters.PostsMultiTypeAdapter
import com.example.studyingproject.classes.Posts
import com.example.studyingproject.classes.Posts.*
import com.example.studyingproject.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {
    private var viewBinding: FragmentPostsBinding? = null
    private var adapter: PostsMultiTypeAdapter? = null
    private lateinit var list: List<Posts>
    private var checkData = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPostsBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecycler()
    }

    private fun initView() {
        initList()
        adapter = PostsMultiTypeAdapter(list)
        viewBinding?.swipeLayout?.setOnRefreshListener {
            if (!checkData) {
                updateRecycler(list)
                viewBinding?.swipeLayout?.isRefreshing = false
                checkData = true
            } else {
                adapter?.updateView(list)
                viewBinding?.swipeLayout?.isRefreshing = false
                checkData = false
            }
        }

    }

    private fun initRecycler() {
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        viewBinding?.recyclerView?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: android.graphics.Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildAdapterPosition(view)
                val itemCount = parent.adapter?.itemCount ?: 0
                when (position) {
                    0 -> outRect.bottom = spacing / 2
                    itemCount - 1 -> outRect.top = spacing / 2
                    else -> {
                        outRect.top = spacing / 2
                        outRect.bottom = spacing / 2
                    }
                }
            }
        })
        viewBinding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        viewBinding?.recyclerView?.adapter = adapter
    }

    private fun initList() {
        val firstCat = ContextCompat.getDrawable(requireContext(), R.drawable.cat)
        val secondCat = ContextCompat.getDrawable(requireContext(), R.drawable.second_cat)
        val thirdCat = ContextCompat.getDrawable(requireContext(), R.drawable.third_cat)

        list = listOf<Posts>(
            Author(
                "Iryna",
                "There's something magical about cats. The way they curl up into tiny fluffy balls, the mysterious look in their eyes, and the soft purring that feels like a " +
                        "lullaby for the soul. Every time I watch my cat stretch out in a sunbeam or chase a dust mote like it's a dragon, I can't help but smile. Cats remind us to slow down, enjoy the little things, and " +
                        "never miss a nap. If you ever need a moment of calm, just find a cat — or better yet, let one find you."
            ),
            Image(firstCat, "Cutie cat"),
            Text(
                "«Печален ты; признайся, что с тобой».\n" +
                        "— Люблю, мой друг! — «Но кто ж тебя пленила?»\n" +
                        "— Она.— «Да кто ж? Глидера ль, Хлоя, Лила?»\n" +
                        "— О, нет! — «Кому ж ты жертвуешь душой?»\n" +
                        "— Ах! ей! — «Ты скромен, друг сердечный!\n" +
                        "Но почему ж ты столько огорчен?\n" +
                        "И кто виной? Супруг, отец, конечно…»\n" +
                        "— Не то, мой друг! — «Но что ж!» — Я ей не он.", "See more"
            ),
            Author(
                "Stepan",
                "Hi, I’m Stepan. I’ve always been fascinated by cars — not just how they look, but how they’re built. There’s something amazing about the engineering behind every " +
                        "engine, every curve of the bodywork, and every little detail in the design. Whether it’s a classic muscle car or a modern electric vehicle, cars tell stories. They reflect innovation, " +
                        "style, and personality. For me, driving is not just a way to get from point A to B — it’s an experience."
            ),
            Image(secondCat, "Another cutie cat"),
            Image(thirdCat, "More cats!!!!")
        )
    }

    private fun updateRecycler(list: List<Posts>) {
        val copyList = list.map { item ->
            when (item) {
                is Author -> item.copy(author = "Author: " + item.author, text = item.text)
                is Image -> item.copy(image = item.image, text = "")
                is Text -> item.copy(text = "Post:\n" + item.text, btnName = item.btnName)
            }
        }

        adapter?.updateView(copyList)
    }

}