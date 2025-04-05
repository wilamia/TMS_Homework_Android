package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SecondFragment : Fragment() {


    private var button: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.moveBtn)
        button?.setOnClickListener {
            moveToFirstFragment()
        }
    }

    private fun moveToFirstFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, MyFragment())
            .addToBackStack(null)
            .commit()
    }

}