package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class SecondFragment : Fragment() {
    private var button: Button? = null
    private var text: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("USERNAME")
        text = view.findViewById(R.id.textView)
        text?.text = username.toString()

        button = view.findViewById(R.id.button)
        button?.setOnClickListener {
            navigateToSecondFragment()
        }
    }
    private fun navigateToSecondFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ThirdFragment())
            .commit()
    }

    fun getInstance(username: String): SecondFragment {
        return SecondFragment().apply {
            arguments = Bundle().apply {
                putString("USERNAME", username)
            }
        }
    }

    companion object {

    }
}