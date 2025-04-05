package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MyFragment : Fragment() {

    private var button: Button? = null
    private var textView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView2)
        receiveData()
        button = view.findViewById(R.id.moveBtn)
        button?.setOnClickListener {
            moveToSecondFragment()
        }
    }

    private fun moveToSecondFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun receiveData() {
        val result = arguments?.getString("MESSAGE")
        textView?.text = result.toString()
    }
}