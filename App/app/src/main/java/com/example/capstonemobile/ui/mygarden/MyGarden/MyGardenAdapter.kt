package com.example.capstonemobile.ui.mygarden.MyGarden

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.capstonemobile.R
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ItemMyGardenBinding
import com.example.capstonemobile.ui.home.PlantAdapter
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import java.util.ArrayList

class MyGardenAdapter (private val context: Context): RecyclerView.Adapter<MyGardenAdapter.MyGardenViewHolder>() {

    private var listData = ArrayList<PlantDetail>()
    var onItemClick: ((PlantDetail) -> Unit)? = null

    fun setData(newListData: List<PlantDetail>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyGardenViewHolder {
        val itemMyGardenBinding = ItemMyGardenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyGardenViewHolder(itemMyGardenBinding)
    }

    override fun onBindViewHolder(holder: MyGardenViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class MyGardenViewHolder(private val binding: ItemMyGardenBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: PlantDetail) {
            with(binding) {
                tvUserPlant.text = plant.plantName
                tvPlantPhase.text = plant.plantPhase
                tvCreatedAt.text = plant.createdAt.toString()
                Glide.with(itemView.context)
                    .load(plant.plantImg)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(ivPlantImg)

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }



}