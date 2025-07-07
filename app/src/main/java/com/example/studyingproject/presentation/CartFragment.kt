package com.example.studyingproject.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyingproject.MainActivity
import com.example.studyingproject.databinding.FragmentCartBinding
import com.example.studyingproject.di.DaggerViewModelFactory
import com.example.studyingproject.presentation.adapter.CartAdapter
import javax.inject.Inject

class CartFragment : Fragment() {
  private lateinit var binding: FragmentCartBinding
    @Inject  lateinit var viewModelFactory: DaggerViewModelFactory
    private val viewModel: CartViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: CartAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CartAdapter(
            onRemoveClick = { cartId ->
                viewModel.removeFromCart(1, cartId)
            }
        )

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        viewModel.loadCartItems()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCartItems()
    }
}