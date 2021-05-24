package com.example.capstonemobile.ui.disease

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capstonemobile.databinding.FragmentDiseaseCheckBinding

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




}