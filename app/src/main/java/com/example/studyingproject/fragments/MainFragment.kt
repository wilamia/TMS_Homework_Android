package com.example.studyingproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyingproject.R
import com.example.studyingproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var viewBinding: FragmentMainBinding? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupView()
    }

    private fun setupView() {
        viewBinding?.elementsBtn?.setOnClickListener {
            navigateToElementsFragment()
        }

        viewBinding?.postsBtn?.setOnClickListener {
            navigateToPostsFragment()
        }
    }

    private fun navigateToElementsFragment() {
        navController?.navigate(R.id.action_mainFragment_to_elementsFragment)
    }

    private fun navigateToPostsFragment() {
        navController?.navigate(R.id.action_mainFragment_to_postsFragment)
    }
}