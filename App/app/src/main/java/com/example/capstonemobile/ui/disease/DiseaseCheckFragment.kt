package com.example.capstonemobile.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capstonemobile.databinding.FragmentDiseaseCheckBinding
import com.example.capstonemobile.ui.disease.camera.CameraActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.startActivity

@AndroidEntryPoint
class DiseaseCheckFragment: Fragment() {

    private lateinit var binding: FragmentDiseaseCheckBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiseaseCheckBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.plantDisease.setOnClickListener {
            startActivity<CameraActivity>()
        }

        binding.myPlantDisease.setOnClickListener {
            startActivity<DiseaseCheckActivity>()
        }

    }



}