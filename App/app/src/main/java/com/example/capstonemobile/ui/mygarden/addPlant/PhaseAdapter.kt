package com.example.capstonemobile.ui.mygarden.addPlant

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonemobile.R
import com.example.capstonemobile.data.source.local.entity.Phase
import com.example.capstonemobile.databinding.ItemPhaseBinding
import com.example.capstonemobile.utils.SessionManagement


class PhaseAdapter(private val phases: List<Phase>, private val context: Context): RecyclerView.Adapter<PhaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhaseViewHolder =
        PhaseViewHolder(ItemPhaseBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: PhaseViewHolder, position: Int) {
        val phase0 = phases[0]
        val phase1 = phases[1]
        val phase2 = phases[2]

        holder.init(context)
        holder.itemView.setOnClickListener {
            when(position){
                0 -> holder.binding(context,phase0)
                1 -> holder.binding(context,phase1)
                2 -> holder.binding(context,phase2)
            }

        }
    }

    override fun getItemCount(): Int = 3

}

class PhaseViewHolder(private val itemBinding: ItemPhaseBinding): RecyclerView.ViewHolder(itemBinding.root) {
    lateinit var session: SessionManagement

    fun init(context: Context){
        DrawableCompat.setTint(itemBinding.imagePhase.drawable, ContextCompat.getColor(context, R.color.grey_darker))
        itemBinding.textPhase.setTextColor(ContextCompat.getColor(context,R.color.grey_darker))
    }

    fun binding(context: Context,phase: Phase){
        session = SessionManagement(context.applicationContext)
        DrawableCompat.setTint(itemBinding.imagePhase.drawable, ContextCompat.getColor(context, R.color.black))
        itemBinding.textPhase.setTextColor(ContextCompat.getColor(context,R.color.black))
        session.setPhase(phase.name)
    }
}
