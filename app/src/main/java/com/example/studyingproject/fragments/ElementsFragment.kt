package com.example.studyingproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.adapters.ElementsMultiTypeAdapter
import com.example.studyingproject.classes.Elements
import com.example.studyingproject.classes.Elements.*
import com.example.studyingproject.databinding.FragmentElementsBinding

class ElementsFragment : Fragment() {
    private var viewBinding: FragmentElementsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentElementsBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf<Elements>(
            Header("Узник"),
            PostText(
                "Сижу за решеткой в темнице сырой.\n" +
                        "Вскормленный в неволе орел молодой,\n" +
                        "Мой грустный товарищ, махая крылом,\n" +
                        "Кровавую пищу клюет под окном,\n" +
                        "Клюет, и бросает, и смотрит в окно,\n" +
                        "Как будто со мною задумал одно.\n" +
                        "Зовет меня взглядом и криком своим\n" +
                        "И вымолвить хочет: «Давай улетим!\n" +
                        "Мы вольные птицы; пора, брат, пора!\n" +
                        "Туда, где за тучей белеет гора,\n" +
                        "Туда, где синеют морские края,\n" +
                        "Туда, где гуляем лишь ветер… да я!..»"
            ),
            Button("Like")
        )
        initRecycler(list)
    }

    private fun initRecycler(list: List<Elements>) {
        viewBinding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        viewBinding?.recyclerView?.adapter = ElementsMultiTypeAdapter(list)
    }
}