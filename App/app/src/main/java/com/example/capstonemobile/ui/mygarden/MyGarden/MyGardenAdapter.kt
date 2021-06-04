package com.example.capstonemobile.ui.mygarden.MyGarden

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.capstonemobile.R
import com.example.capstonemobile.data.source.local.entity.UserPlantEntity
import com.example.capstonemobile.databinding.ItemMyGardenBinding
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import com.ojanbelajar.moviekatalogue.utils.Resource

class MyGardenAdapter : RecyclerView.Adapter<MyGardenAdapter.MyGardenViewHolder>() {

    private var listPlants = ArrayList<UserPlantEntity>()

    fun setPlants(plants: List<UserPlantEntity>) {
        if (plants == null) return
        this.listPlants.clear()
        this.listPlants.addAll(plants)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyGardenViewHolder {
        val itemMyGardenBinding = ItemMyGardenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyGardenViewHolder(itemMyGardenBinding)
    }

    override fun onBindViewHolder(holder: MyGardenViewHolder, position: Int) {
        val plant = listPlants[position]
        holder.bind(plant)
    }

    override fun getItemCount(): Int = listPlants.size

    class MyGardenViewHolder(private val binding: ItemMyGardenBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: UserPlantEntity) {
            with(binding) {
                tvUserPlant.text = plant.plantName
                tvPlantPhase.text = plant.plantPhase
                tvCreatedAt.text = plant.createdAt
                Glide.with(itemView.context)
                    .load(plant.plantImg)
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