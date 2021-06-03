package com.example.capstonemobile.ui.mygarden

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ItemMyGardenBinding

class AdapterMyGarden(private val context: Context): PagedListAdapter<PlantDetail,MyGardenViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<PlantDetail>(){
            override fun areItemsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PlantDetail, newItem: PlantDetail): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGardenViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyGardenViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class MyGardenViewHolder(private val itemBinding: ItemMyGardenBinding) : RecyclerView.ViewHolder(itemBinding.root){

    fun bind(detail: PlantDetail){
        
    }

}
