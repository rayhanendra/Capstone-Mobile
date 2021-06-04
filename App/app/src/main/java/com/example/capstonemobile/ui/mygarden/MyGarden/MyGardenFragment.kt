package com.example.capstonemobile.ui.mygarden.MyGarden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstonemobile.databinding.FragmentMyGardenBinding
import com.example.capstonemobile.ui.mygarden.AddPlantActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyGardenFragment: Fragment(){

    private lateinit var binding: FragmentMyGardenBinding

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
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MyGardenViewModel::class.java]

            val adapter = MyGardenAdapter()

            binding.progressBar.visibility = View.VISIBLE
            viewModel.getAllUserPlant().observe(viewLifecycleOwner, { plants ->
                binding.progressBar.visibility = View.GONE
                adapter.setPlants(plants)
                adapter.notifyDataSetChanged()
            })

            with(binding.rvMyGarden) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

     private fun addPlant() {
        val addButton = binding.btnAddMyGarden
        addButton.setOnClickListener {
            val intent = Intent(requireActivity(), AddPlantActivity::class.java)
            startActivity(intent)
        }
    }
}