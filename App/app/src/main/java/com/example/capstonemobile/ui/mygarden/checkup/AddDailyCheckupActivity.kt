package com.example.capstonemobile.ui.mygarden.checkup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstonemobile.databinding.ActivityAddDailyCheckupBinding
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject

@AndroidEntryPoint
class AddDailyCheckupActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddDailyCheckupBinding
    private val model: CheckupViewModel by viewModels()
    private var enumModel: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDailyCheckupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plant: String = intent.getStringExtra("plant")!!
        getPlant(plant)
        save()
        back()
    }

    private fun getPlant(id: String){
        model.getPlantDetail(id).observe(this, Observer { detail ->
            if (detail != null){
                when(detail){
                    is Resource.Success -> {
                        checkEnum(detail.data!!.plantName)
                    }
                    is Resource.Loading -> {
                        toast("Loading")
                    }
                    is Resource.Error -> {
                        toast("Error")
                    }

                }
            }
        })
    }

    private fun checkEnum(id: String){
        when(id){
            "Jagung" -> enumModel = 0
            "Anggur" -> enumModel = 1
            "Apel" -> enumModel = 2
            "Jeruk" -> enumModel = 3
        }
    }


    private fun createJsonRequestBody(vararg params: Pair<String, ArrayList<ArrayList<Double>>>) =
        RequestBody.create(
            okhttp3.MediaType.parse("application/json"),
            JSONObject(mapOf(*params)).toString()
        )

    private fun save() {
        val button = binding.btnSave
        button.setOnClickListener {
            if (binding.editHumidity.text.isNotEmpty() && binding.editTemperature.text.isNotEmpty()) {
                val list = ArrayList<ArrayList<Double>>()
                val firstList = ArrayList<Double>()
                firstList.add(binding.editTemperature.text.toString().toDouble())
                firstList.add(binding.editHumidity.text.toString().toDouble())
                firstList.add(enumModel.toDouble())
                firstList.add(0.0)
                val secondList = ArrayList<Double>()
                secondList.add(binding.editTemperature.text.toString().toDouble())
                secondList.add(binding.editHumidity.text.toString().toDouble())
                secondList.add(enumModel.toDouble())
                secondList.add(1.0)
                val thirdList = ArrayList<Double>()
                thirdList.add(binding.editTemperature.text.toString().toDouble())
                thirdList.add(binding.editHumidity.text.toString().toDouble())
                thirdList.add(enumModel.toDouble())
                thirdList.add(2.0)
                list.add(firstList)
                list.add(secondList)
                list.add(thirdList)
                val body = createJsonRequestBody("instances" to list)
                model.insertNpk(body).observe(this, Observer { detail ->
                    if (detail != null) {
                        when (detail) {
                            is Resource.Success -> {
                                val npk = detail.data
                                startActivity<DetailDailyCheckupActivity>("npk" to npk)
                            }
                            is Resource.Loading -> {
                                toast("Loading")
                            }
                            is Resource.Error -> {
                                Log.d("ojan", detail.message.toString())
                                toast("Error")
                            }

                        }
                    }
                })
            }
        }
    }



    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }

}