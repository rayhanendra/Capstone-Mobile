package com.example.capstonemobile.ui.mygarden


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capstonemobile.databinding.FragmentMyGardenBinding

class MyGardenFragment: Fragment(){

    private lateinit var binding: FragmentMyGardenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyGardenBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}