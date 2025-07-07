package com.example.studyingproject.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.MainActivity
import com.example.studyingproject.R
import com.example.studyingproject.databinding.FragmentProductBinding
import com.example.studyingproject.di.DaggerViewModelFactory
import com.example.studyingproject.presentation.adapter.ProductAdapter
import javax.inject.Inject

class ProductListFragment : Fragment() {
   private lateinit var binding: FragmentProductBinding
    @Inject  lateinit var viewModelFactory: DaggerViewModelFactory
    private val viewModel: ProductListViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: ProductAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).component.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(onItemClick = { product ->
            val action = ProductListFragmentDirections.actionProductFragmentToDetailFragment(product.id)
            findNavController().navigate(action)
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.fetchProducts()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_cart -> {
                    findNavController().navigate(R.id.action_productFragment_to_cartFragment)
                    true
                }
                else -> false
            }
        }
    }
}