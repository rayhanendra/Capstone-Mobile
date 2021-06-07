package com.example.capstonemobile.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ItemPlantPagerBinding
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity

class PlantAdapter(private val context: Context): PagedListAdapter<PlantDetail, PlantAdapterViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlantDetail>(){
            override fun areItemsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantAdapterViewHolder =
        PlantAdapterViewHolder(ItemPlantPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PlantAdapterViewHolder, position: Int) {
        val plants = getItem(position)
        if (plants != null){
            holder.bind(plants)
            holder.itemView.setOnClickListener {
                context.startActivity<PlantDetailActivity>("plant" to plants)
            }
        }
    }
}

class PlantAdapterViewHolder(private val binding: ItemPlantPagerBinding) :RecyclerView.ViewHolder(binding.root)  {

    fun bind(plantDetail: PlantDetail){
        Log.d("ojan",plantDetail.toString())
       // binding.slidePlant.imageResource = plantDetail.image
    }

}
