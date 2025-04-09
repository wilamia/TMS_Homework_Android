package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.example.studyingproject.databinding.FragmentSettingsBinding
import com.google.android.material.tabs.TabLayoutMediator

class SettingsFragment : Fragment() {
    private var viewBinding: FragmentSettingsBinding? = null
    private val tabTitles = listOf("Home", "Language")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        /*Сделай экран с TabLayout и ViewPager2, в каждом табе — отдельный фрагмент.*/
    }

    private fun setupViewPager() {
        withBinding {
            val adapter = MyPagerAdapter(this@SettingsFragment)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitles[position]
                tab.icon = context?.let { AppCompatResources.getDrawable(it, android.R.drawable.ic_menu_compass) }
            }.attach()
        }
    }

    private fun withBinding(block: FragmentSettingsBinding.() -> Unit) {
        viewBinding?.let {
            block.invoke(it)
        }
    }
}