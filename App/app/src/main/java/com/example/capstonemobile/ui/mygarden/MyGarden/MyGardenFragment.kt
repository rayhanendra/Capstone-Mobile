package com.example.capstonemobile.ui.mygarden.MyGarden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.capstonemobile.databinding.FragmentMyGardenBinding
import com.example.capstonemobile.ui.MainActivity
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import com.example.capstonemobile.ui.mygarden.addPlant.AddPlantActivity
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import com.ojanbelajar.moviekatalogue.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

@AndroidEntryPoint
class MyGardenFragment: Fragment(){
    private lateinit var session: SessionManagement
    private val model: MyGardenViewModel by viewModels()
    private lateinit var binding: FragmentMyGardenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        session = SessionManagement(requireActivity().applicationContext)
        binding = FragmentMyGardenBinding.inflate(layoutInflater,container,false)
        addPlant()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val adapter = MyGardenAdapter(requireActivity())
            binding.progressBar.visibility = View.VISIBLE
            model.getAllUserPlant(session.user["id"].toString()).observe(viewLifecycleOwner, Observer { plants ->
                if (plants != null){
                    when(plants){
                        is Resource.Success -> {
                            Log.d("ojan",plants.data.toString())
                            if (plants.data != null) {
                                binding.progressBar.visibility = View.GONE
                                adapter.setData(plants.data)
                            }
                        }
                        is Resource.Loading -> {
                            toast("Loading")
                        }
                        is Resource.Error -> {
                            toast("Error")
                        }
                    }
                }

            })
            with(binding.rvMyGarden) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
            adapter.onItemClick = { data ->
                startActivity<PlantDetailActivity>("plant" to data)
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