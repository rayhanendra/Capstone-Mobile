package com.example.capstonemobile.ui.mygarden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.capstonemobile.databinding.FragmentMyGardenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyGardenFragment: Fragment(){

    private lateinit var binding: FragmentMyGardenBinding
    private val model : MyGardenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyGardenBinding.inflate(layoutInflater,container,false)
        addPlant()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

     private fun addPlant() {
        val addButton = binding.btnAddMyGarden
        addButton.setOnClickListener {
            val intent = Intent(requireActivity(), AddPlantActivity::class.java)
            startActivity(intent)
        }
    }
}