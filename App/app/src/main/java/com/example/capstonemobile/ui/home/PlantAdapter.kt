package com.example.capstonemobile.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonemobile.data.source.local.entity.PlantSlider
import com.example.capstonemobile.databinding.ItemPlantPagerBinding
import org.jetbrains.anko.imageResource

class PlantAdapter(private val plants: List<PlantSlider>): RecyclerView.Adapter<PlantAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantAdapterViewHolder =
        PlantAdapterViewHolder(ItemPlantPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PlantAdapterViewHolder, position: Int) {
        holder.bind(plants[position])
    }

    override fun getItemCount(): Int = plants.size
}

class PlantAdapterViewHolder(private val binding: ItemPlantPagerBinding) :RecyclerView.ViewHolder(binding.root)  {

    fun bind(plantSlider: PlantSlider){
        binding.slidePlant.imageResource = plantSlider.image
    }

}
