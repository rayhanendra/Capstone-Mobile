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
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity

class MyGardenAdapter (private val context: Context): PagedListAdapter<PlantDetail, MyGardenAdapter.MyGardenViewHolder>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlantDetail>(){
            override fun areItemsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
                return oldItem.plantId == newItem.plantId
            }

            override fun areContentsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyGardenViewHolder {
        val itemMyGardenBinding = ItemMyGardenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyGardenViewHolder(itemMyGardenBinding)
    }

    override fun onBindViewHolder(holder: MyGardenViewHolder, position: Int) {

    }


    class MyGardenViewHolder(private val binding: ItemMyGardenBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: PlantDetail) {
            with(binding) {
                tvUserPlant.text = plant.userPlantName
                tvPlantPhase.text = plant.plantPhase
                tvCreatedAt.text = plant.createdAt.toString()
                Glide.with(itemView.context)
                    .load(plant.plantImage)
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(ivPlantImg)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, PlantDetailActivity::class.java)
                    intent.putExtra(PlantDetailActivity.EXTRA_PLANT, plant.plantId)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}