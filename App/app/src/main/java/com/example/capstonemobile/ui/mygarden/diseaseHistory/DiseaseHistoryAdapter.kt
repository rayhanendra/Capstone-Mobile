package com.example.capstonemobile.ui.mygarden.diseaseHistory

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonemobile.data.source.local.entity.DiseaseByUserEntity
import com.example.capstonemobile.data.source.local.entity.DiseaseEntity
import com.example.capstonemobile.databinding.ItemHistoryDiseaseBinding
import com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail.DetailDiseaseHistoryActivity

class DiseaseHistoryAdapter (private val context: Context): RecyclerView.Adapter<DiseaseHistoryAdapter.DiseaseHistoryViewHolder>() {

    private var listData = ArrayList<DiseaseEntity>()
    var onItemClick: ((DiseaseEntity) -> Unit)? = null

    fun setData(newListData: List<DiseaseEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseHistoryViewHolder {
        val itemDiseaseHistoryBinding = ItemHistoryDiseaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiseaseHistoryViewHolder(itemDiseaseHistoryBinding)
    }

    override fun onBindViewHolder(holder: DiseaseHistoryViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class DiseaseHistoryViewHolder(private val binding: ItemHistoryDiseaseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diseaseEntity: DiseaseEntity) {
            with(binding) {
                tvPlantName.text = diseaseEntity.diseasesName
                tvPlantDate.text= diseaseEntity.createdAt

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailDiseaseHistoryActivity::class.java)
                    intent.putExtra(DetailDiseaseHistoryActivity.EXTRA_DISEASE, diseaseEntity.id)
                    intent.putExtra(DetailDiseaseHistoryActivity.EXTRA_PLANT, diseaseEntity.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}