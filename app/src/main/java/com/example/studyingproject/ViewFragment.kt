package com.example.studyingproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.example.studyingproject.databinding.FragmentSettingsBinding
import com.example.studyingproject.databinding.FragmentViewBinding
import com.google.android.material.tabs.TabLayoutMediator


class ViewFragment : Fragment() {
    private var viewBinding: FragmentViewBinding? = null
    private lateinit var adapter: ViewAdapter
    private var count = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentViewBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewPager()
/*Создай ViewPager2, в который можно добавлять фрагменты во время выполнения,
например, при нажатии на кнопку.
*/
    }
    private fun setupView() {
        viewBinding?.someButton?.setOnClickListener {
            val newFragment = SampleFragment.newInstance("Fragment $count")
            adapter.addFragment(newFragment)
            viewBinding?.viewPager?.setCurrentItem(adapter.itemCount - 1, true)
            count++
        }
    }

    private fun setupViewPager() {
        adapter = ViewAdapter(this)
        viewBinding?.viewPager?.adapter = adapter

        adapter.addFragment(SampleFragment.newInstance("Start Fragment $count"))
        count++
    }
}