package com.example.studyingproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.studyingproject.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private var navController: NavController? = null
    private var viewBinding: FragmentHomeBinding? = null
    private var editText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = view.findViewById(R.id.editTextHome)

        navController = findNavController()
        setupView()
    }

    private fun setupView() {
        viewBinding?.button?.setOnClickListener {
            navigateToDetails()
        }
        listenFragmentResult()
        viewBinding?.button2?.setOnClickListener {
            navigateToView()
        }
    }


    private fun listenFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            RESULT_KEY,
            this
        ) { requestKey, bundle ->
            val result = bundle.getString(MESSAGE_KEY)
            Log.d("MYTAG", "Recieved result: $result")
        }

    }
    private fun navigateToView() {

        val navOptions = androidx.navigation.navOptions {
            launchSingleTop = true
            restoreState = true
        }

        navController?.navigate(HomeFragmentDirections.actionHomeFragmentToViewFragment(), navOptions)

    }
/*Задача 2: Переход между фрагментами с передачей аргументов*/
    private fun navigateToDetails() {
        val text = editText?.text.toString()

        val navOptions = androidx.navigation.navOptions {
            launchSingleTop = true
            restoreState = true
        }

        navController?.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(text), navOptions)

    }
    companion object {
        const val RESULT_KEY = "RESULT"
        const val MESSAGE_KEY = "MESSAGE"
    }
}