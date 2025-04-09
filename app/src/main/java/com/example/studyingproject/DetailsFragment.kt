package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studyingproject.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar


class DetailsFragment : Fragment() {

    private var navController: NavController? = null
    private var viewBinding: FragmentDetailsBinding? = null
    private var textView: TextView? = null
    private val args: DetailsFragmentArgs by navArgs()
    private var receivedText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*Показать Snackbar с сообщением и кнопкой "Отмена", которая отменяет действие.*/
        Snackbar.make(requireView(), "Переход выполнен", Snackbar.LENGTH_LONG)
            .setAction("Отмена") {
                findNavController().popBackStack()
            }
            .show()
        textView = view.findViewById(R.id.textView)
        receivedText = args.textFromHome
        textView?.text = receivedText
        navController = findNavController()
        setupView()
        navigateToHome()

    }

    private fun setupView() {
        viewBinding?.button?.setOnClickListener {
            navigateToSettings()
        }
        /*Создай экран с TextView и Button. При нажатии на кнопку текст должен измениться.
Используй ViewBinding.*/
        viewBinding?.editBtn?.setOnClickListener {
            textView?.text = "Hi $receivedText"
        }
    }

    /* Задача 3: Обработка возвращаемых данных из фрагмента*/
    private fun navigateToHome() {

        val modifyReceivedText = "Hi, ${textView?.text}"
        parentFragmentManager.setFragmentResult(
            RESULT_KEY,
            bundleOf(MESSAGE_KEY to modifyReceivedText)
        )
    }

    /*Задача 1: Переход через несколько фрагментов с сохранением состояний*/
    private fun navigateToSettings() {
        val navOptions = androidx.navigation.navOptions {
            launchSingleTop = true
            restoreState = true
        }
        navController?.navigate(
            DetailsFragmentDirections.actionDetailsFragmentToSettingsFragment(),
            navOptions
        )
    }

    companion object {
        const val RESULT_KEY = "RESULT"
        const val MESSAGE_KEY = "MESSAGE"
    }
}