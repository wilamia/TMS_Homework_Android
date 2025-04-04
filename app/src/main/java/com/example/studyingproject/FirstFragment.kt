package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class FirstFragment : Fragment() {

    private var button: Button? = null
    private var text:EditText? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.button)
        text = view.findViewById(R.id.editText)
        val username = text?.text.toString()
        button?.setOnClickListener {
            navigateToSecondFragment(username)
        }
    }

    private fun navigateToSecondFragment(text: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment().getInstance(text))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val USERNAME_KEY = "USERNAME"
    }

}
