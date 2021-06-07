package com.example.capstonemobile.ui.mygarden.addPlant

import com.example.capstonemobile.R
import com.example.capstonemobile.data.source.local.entity.Phase

object PhaseData {
    private val phaseName = arrayOf(
        "Sprout",
        "Seedling",
        "Vegetative"
    )
    private val phasePhoto = intArrayOf(
        R.drawable.sprout,
        R.drawable.seedling,
        R.drawable.vegetetive
    )

    val listData: ArrayList<Phase>
        get() {
            val list = arrayListOf<Phase>()
            for (position in phaseName.indices){
                val phase = Phase()
                phase.name = phaseName[position]
                phase.picture = phasePhoto[position]
                list.add(phase)
            }
            return list
        }
}