package com.example.capstonemobile.ui.mygarden.diseaseHistory

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.capstonemobile.R
import com.example.capstonemobile.data.source.local.entity.DiseaseByUserEntity
import com.example.capstonemobile.data.source.local.entity.DiseaseEntity
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ItemHistoryDiseaseBinding
import com.example.capstonemobile.ui.mygarden.diseaseHistoryDetail.DetailDiseaseHistoryActivity
import org.jetbrains.anko.startActivity
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToLong

class DiseaseHistoryAdapter (private val context: Context, private val plantDetail: PlantDetail): RecyclerView.Adapter<DiseaseHistoryAdapter.DiseaseHistoryViewHolder>() {

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
        Log.d("ojan",plantDetail.toString())
        holder.bind(data,plantDetail)
    }

    override fun getItemCount(): Int = listData.size

    inner class DiseaseHistoryViewHolder(private val binding: ItemHistoryDiseaseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diseaseEntity: DiseaseEntity, plantDetail: PlantDetail) {
            with(binding) {
                tvPlantName.text = diseaseEntity.diseaseName
            /*    val formatter = DateTimeFormatter.ofPattern("EEEE,dd MMM yyyy")
                val instant = Instant.ofEpochMilli((plantDetail.createdAt).roundToLong())
                val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).plusYears(52)
                tvPlantDate.text = formatter.format(date)*/
                Glide.with(context)
                    .load(plantDetail.plantImg)
                    .apply(RequestOptions().placeholder(R.drawable.ic_image))
                    .into(binding.ivPlantImg)
                itemView.setOnClickListener {
                    context.startActivity<DetailDiseaseHistoryActivity>("disease" to diseaseEntity)
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