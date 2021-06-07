package com.example.capstonemobile.ui.home


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.data.source.local.entity.PlantSlider
import com.example.capstonemobile.databinding.FragmentHomeBinding
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.toast

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var session: SessionManagement
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: PlantAdapter
    private val model: HomeViewModel by viewModels()

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
        adapter = PlantAdapter(requireActivity())
        getAllPlant()
        getPlantByUserId(session.user["id"].toString())
    }

    private fun getAllPlant(){
        model.getAllPlant().observe(viewLifecycleOwner, Observer {  plant ->
            if (plant != null){
                when(plant.status){
                    Status.SUCCESS -> {
                        toast("Got Data")
                    }
                    Status.ERROR -> {
                        toast("Something Wrong")
                    }

                }
            }

        })
    }

    private fun getPlantByUserId(id: String){
        model.getPlantByUserId(id).observe(viewLifecycleOwner, Observer { plant ->
            if (plant != null){
                when(plant.status){
                    Status.SUCCESS -> {
                        Log.d("ojan",plant.data.toString())
                        if (plant.data != null){
                            adapter.submitList(plant.data)
                            setupSlider()
                        }
                    }
                    Status.ERROR -> {
                        toast("Something Wrong")
                    }

                }
            }

        })
    }

    private fun setupSlider() {
        binding.pagerPlant.adapter = adapter
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