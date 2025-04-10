package com.example.studyingproject
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.studyingproject.databinding.FragmentScrollViewBinding

/*Сделай длинную форму с полями и кнопкой внизу.*/
class ScrollViewFragment : Fragment() {
    private var viewBinding: FragmentScrollViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentScrollViewBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeat(100) {
            addText("Динамический текст №${it + 1}")
        }
        viewBinding?.button4?.setOnClickListener {
            Toast.makeText(requireContext(), "The end of the page", Toast.LENGTH_SHORT).show()
        }
    }

    private fun  addText(text: String) {
        val context = requireContext()
        val textView = TextView(context).apply {
            this.text = text
            textSize = 20f
            setPadding(0, 8, 0, 8)
        }
        viewBinding?.formContainer?.addView(textView, 0)
    }

}