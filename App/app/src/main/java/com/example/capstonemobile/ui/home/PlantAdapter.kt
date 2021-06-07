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
import com.example.capstonemobile.ui.mygarden.addPlant.PhaseViewHolder
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class PlantAdapter(private val context: Context):RecyclerView.Adapter<PlantAdapter.PlantAdapterViewHolder>(){

    private var listData = ArrayList<PlantDetail>()
    var onItemClick: ((PlantDetail) -> Unit)? = null

    fun setData(newListData: List<PlantDetail>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantAdapterViewHolder =
        PlantAdapterViewHolder(ItemPlantPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PlantAdapterViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int = listData.size

inner class PlantAdapterViewHolder(private val binding: ItemPlantPagerBinding) :RecyclerView.ViewHolder(binding.root) {

    fun bind(plantDetail: PlantDetail) {
        Log.d("ojan", plantDetail.toString())
        // binding.slidePlant.imageResource = plantDetail.image
    }

    init {
        binding.root.setOnClickListener {
            onItemClick?.invoke(listData[adapterPosition])
        }
    }
}
}
