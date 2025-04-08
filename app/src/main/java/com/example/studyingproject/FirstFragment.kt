package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.studyingproject.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var navController: NavController? = null

    private var viewBinding: FragmentFirstBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
    }

    private fun setupView() {
        viewBinding?.button?.setOnClickListener {
            navigateToSecondFragment()
        }
    }

    private fun navigateToSecondFragment() {
        navController?.navigate(R.id.action_firstFragment_to_secondFragment)
    }

    companion object {
        const val USERNAME_KEY = "USERNAME"
    }

}
