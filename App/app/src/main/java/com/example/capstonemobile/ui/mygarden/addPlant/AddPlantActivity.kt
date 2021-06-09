package com.example.capstonemobile.ui.mygarden.addPlant

import android.Manifest
import android.R
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.capstonemobile.data.source.local.entity.Plant
import com.example.capstonemobile.data.source.local.entity.PlantDetail
import com.example.capstonemobile.databinding.ActivityAddPlantBinding
import com.example.capstonemobile.ui.mygarden.PlantDetailActivity
import com.example.capstonemobile.ui.mygarden.addPlant.PhaseData.listData
import com.example.capstonemobile.utils.SessionManagement
import com.ojanbelajar.moviekatalogue.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.M)
class AddPlantActivity: AppCompatActivity(){

    companion object {
        private const val CAMERA_REQUEST = 1888
        private const val MY_CAMERA_PERMISSION_CODE = 100

    }

    private lateinit var session: SessionManagement
    private lateinit var binding: ActivityAddPlantBinding
    private val model: AddPlantViewModel by viewModels()
    private var picture: File = File("")
    private var imageString = ""
    private var plants: MutableList<Plant> = mutableListOf()
    private var plant: Plant = Plant()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPlantBinding.inflate(layoutInflater)
        session = SessionManagement(applicationContext)
        setContentView(binding.root)
        getAllPlant()
        addPhoto()
        savedPhoto()
        addPlant()
        setupPhase()
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
                    is Resource.Error -> {
                        toast("Error")
                    }
                }
            }
        })
    }

    private fun savedPhoto(){
        binding.savedPhoto.setOnClickListener {
            val reqFile = RequestBody.create(MediaType.parse("image/jpg"), picture)
            val body = MultipartBody.Part.createFormData("file", picture.name, reqFile)
            model.uploadImage(body).observe(this, Observer { image ->
                if (image != null){
                    when(image){
                        is Resource.Success -> {
                            if (image.data != null) {
                                imageString = image.data.imageUrl
                                toast("Upload Success")
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
    }

    private fun addPhoto() {
        binding.addPhoto.setOnClickListener {
           takePhoto()
        }
    }

    fun takePhoto() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, CAMERA_REQUEST)
            } catch (e: ActivityNotFoundException) {
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode === MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CAMERA_REQUEST && resultCode === RESULT_OK) {
            val photo = data!!.extras!!["data"] as Bitmap
            binding.imageAdded.visibility = View.VISIBLE
            picture = createTempFile(photo)
            binding.imageAdded.setImageBitmap(photo)
            binding.addPhoto.visibility = View.INVISIBLE
            binding.savedPhoto.visibility = View.VISIBLE
        }
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

    private fun setupPhase(){
        val array = ArrayList<String>()
        for (p in listData){
            array.add(p.name)
        }
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, R.layout.simple_spinner_item,
            array
        )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerPhase.adapter = spinnerArrayAdapter
    }

    private fun addPlant() {

        val addButton = binding.btnAddPlant
        addButton.setOnClickListener {
            plant = getPlant(binding.spinnerPlant.selectedItem.toString())
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
                   binding.addDetail.text.toString(),
                   binding.spinnerPhase.selectedItem.toString()
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