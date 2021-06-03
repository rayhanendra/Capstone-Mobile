package com.example.capstonemobile.ui.home


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.capstonemobile.data.source.local.entity.PlantSlider
import com.example.capstonemobile.databinding.FragmentHomeBinding
import com.example.capstonemobile.utils.SessionManagement

class HomeFragment: Fragment() {
    private lateinit var session: SessionManagement
    private lateinit var binding: FragmentHomeBinding
    private val plants: List<PlantSlider> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        session = SessionManagement(requireActivity().applicationContext)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSlider()
    }

    private fun setupSlider() {
        binding.pagerPlant.adapter = PlantAdapter(plants)
        binding.pagerPlant.clipChildren = false
        binding.pagerPlant.clipToPadding = false
        binding.pagerPlant.offscreenPageLimit = 3
        binding.pagerPlant.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val composite = CompositePageTransformer()
        composite.addTransformer(MarginPageTransformer(40))
        composite.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })

        binding.run {
            composite.addTransformer(MarginPageTransformer(40))
            composite.addTransformer(ViewPager2.PageTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            })

            pagerPlant.setPageTransformer(composite)
            pagerPlant.registerOnPageChangeCallback(object : OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                        Handler(Looper.getMainLooper()).postDelayed({
                            slideRunnable()
                        },3000)
                }
            })
        }
    }

    private fun slideRunnable() = Runnable {
        kotlin.run {
            binding.pagerPlant.currentItem = binding.pagerPlant.currentItem + 1
        }
    }
}