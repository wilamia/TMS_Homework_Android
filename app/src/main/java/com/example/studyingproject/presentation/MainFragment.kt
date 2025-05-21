package com.example.studyingproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.R
import com.example.studyingproject.adapter.RecyclerViewAdapter
import com.example.studyingproject.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RecyclerViewAdapter()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewBinding.recyclerView.adapter = adapter

        viewBinding.button.setOnClickListener {
            lifecycleScope.launch {
                val apiKey = requireContext().getString(R.string.API_KEY)
                viewModel.init(apiKey)
                viewModel.loadPokemon()
            }
        }
        lifecycleScope.launch {
            viewModel.pokemonList.collect { list ->
                    adapter.submitList(list)
            }
        }
    }
}