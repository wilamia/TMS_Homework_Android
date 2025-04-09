package com.example.studyingproject

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

class SampleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val title = arguments?.getString(ARG_TITLE) ?: "Fragment"

        val textView = TextView(requireContext()).apply {
            text = title
            textSize = 24f
            gravity = Gravity.CENTER
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }

        return FrameLayout(requireContext()).apply {
            addView(textView)
        }
    }

    companion object {
        private const val ARG_TITLE = "title"

        fun newInstance(title: String): SampleFragment {
            val fragment = SampleFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

}