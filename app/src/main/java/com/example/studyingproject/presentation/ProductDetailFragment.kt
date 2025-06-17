package com.example.studyingproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.studyingproject.R
import com.example.studyingproject.data.Product
import com.example.studyingproject.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()
    private var productId: Int = 0
    private var cartId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productId = args.productId
        viewModel.loadProduct(productId)
        viewModel.product.observe(viewLifecycleOwner) {
            binding.title.setText(it.title)
            binding.description.setText(it.description)
            binding.price.setText(it.price.toString())
            binding.image.load(it.image)
        }

        binding.updateButton.setOnClickListener {
            val updatedProduct = Product(
                productId,
                binding.title.text.toString(),
                binding.price.text.toString().toDoubleOrNull() ?: 0.0,
                binding.description.text.toString(),
                viewModel.product.value?.image ?: ""
            )
            viewModel.updateProduct(updatedProduct)
        }

        binding.addToCartButton.setOnClickListener {
            viewModel.addToCart(productId, cartId)
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_cart -> {
                    findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
                    true
                }
                else -> false
            }
        }
    }
}