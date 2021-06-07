package com.example.capstonemobile.ui.mygarden.addPlant

import android.R
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.capstonemobile.data.source.local.entity.Phase
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ActivityAddPlantBinding
import com.example.capstonemobile.ui.MainActivity
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import com.example.capstonemobile.ui.mygarden.addPlant.PhaseData.listData
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import com.ojanbelajar.moviekatalogue.utils.Status
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class AddPlantActivity: AppCompatActivity(){

    companion object {
        private val REQUEST_IMAGE_CAPTURE = 1
    }

    private lateinit var session: SessionManagement
    private lateinit var binding: ActivityAddPlantBinding
    private val model: AddPlantViewModel by viewModels()
    private var picture: File = File("")
    private var imageString = ""
    private var plants: MutableList<Plant> = mutableListOf()
    private var plant: Plant = Plant()
    private lateinit var adapter: PhaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlantBinding.inflate(layoutInflater)
        session = SessionManagement(applicationContext)
        setContentView(binding.root)
        getAllPlant()
        addPhoto()
        savedPhoto()
        addPlant()
        getPhase()
        back()
    }

    private fun spinnerSetup(){
        val array = ArrayList<String>()
        for (p in plants){
            array.add(p.plantName)
        }
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, R.layout.simple_spinner_item,
            array
        )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerPlant.adapter = spinnerArrayAdapter
    }

    private fun getAllPlant() {
        model.getAllPlant().observe(this, Observer {plant ->
            if (plant != null){
                when(plant){
                    is Resource.Success -> {
                        if (plant.data != null){
                            for(p in plant.data){
                                plants.add(p)
                            }
                            spinnerSetup()
                        }
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

    private fun savedPhoto(){
        binding.savedPhoto.setOnClickListener {
            val reqFile = RequestBody.create(MediaType.parse("image/jpeg"), picture)
            val body = MultipartBody.Part.createFormData("picture", picture.name, reqFile)
            model.uploadImage(body).observe(this, Observer { image ->
                if (image != null){
                    when(image){
                        is Resource.Success -> {
                            if (image.data != null) imageString = image.data.imageUrl
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
    }

    private fun addPhoto() {
        if (picture.exists()){
            binding.addPhoto.visibility = View.GONE
            binding.savedPhoto.visibility = View.VISIBLE
        }
        binding.addPhoto.setOnClickListener {
           takePhoto()
        }
    }

    fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(
                takePictureIntent,
                REQUEST_IMAGE_CAPTURE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            picture = createTempFile(imageBitmap)
            setImageNew(imageBitmap)
        }
    }

    private fun setImageNew(photo: Bitmap) {
        Glide.with(this)
            .asBitmap()
            .load(photo)
            .into(binding.imageAdded)
    }

    private fun createTempFile(bitmap: Bitmap): File {
        val file = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            System.currentTimeMillis().toString() + "_image.JPEG"
        )

        var compressionConstant = 100
        var bitmapData = getByteArray(bitmap, compressionConstant)

        while ((bitmapData.size) > 500000) {
            when {
                compressionConstant > 50 -> compressionConstant -= 15
                compressionConstant > 25 -> compressionConstant -= 10
                compressionConstant > 10 -> compressionConstant -= 5
                else -> compressionConstant--
            }
            bitmapData = getByteArray(bitmap, compressionConstant)
        }

        try {
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    private fun getByteArray(bitmap: Bitmap, compressConstant: Int): ByteArray {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressConstant, bos)
        return bos.toByteArray()
    }

    private fun getPlant(name: String): Plant{
        for (p in plants){
            if (name == p.plantName) {
                return p
            }
        }
        return Plant()
    }

    private fun getPhase(){
        adapter = PhaseAdapter(listData,this)
        binding.rvPhasePick.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvPhasePick.adapter = adapter
        binding.rvPhasePick.setHasFixedSize(true)
    }

    private fun addPlant() {
        plant = getPlant(binding.spinnerPlant.selectedItem.toString())
        val addButton = binding.btnAddPlant
        addButton.setOnClickListener {
           if (imageString == ""){
               toast("Upload Image First")
           } else {
               val result = PlantDetail(
                   "",
                   plant.id,
                   session.user["id"].toString(),
                   binding.addName.text.toString(),
                   0.0,
                   imageString,
                   "",
                   session.phase
               )
               model.insertPlant(session.user["id"].toString(),result).observe(this, Observer {result ->
                   if (result != null){
                       when(result){
                           is Resource.Success -> {
                               startActivity<PlantDetailActivity>("plant" to result.data)
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
        }
    }

    private fun back() {
        val button = binding.btnBack
        button.setOnClickListener {
            finish()
        }
    }

}