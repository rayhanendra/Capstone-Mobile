package com.example.capstonemobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.capstonemobile.R
import com.example.capstonemobile.databinding.ActivityMainBinding
import com.example.capstonemobile.ui.disease.DiseaseCheckFragment
import com.example.capstonemobile.ui.home.HomeFragment
import com.example.capstonemobile.ui.mygarden.MyGardenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment: Fragment = HomeFragment()
    private val myGardenFragment: Fragment = MyGardenFragment()
    private val diseaseCheckFragment: Fragment = DiseaseCheckFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        commitFragment(homeFragment)
        val navigation = BottomNavigationView.OnNavigationItemSelectedListener {
            clearFragmentStack()
            when(it.itemId){
                R.id.navigation_home ->  {
                    commitFragment(homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_disease -> {
                    commitFragment(diseaseCheckFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_garden -> {
                    commitFragment(myGardenFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        bottomNavigation.setOnNavigationItemSelectedListener(navigation)
    }

    private fun commitFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

    private fun clearFragmentStack(){
        val fm = this.supportFragmentManager
        for ( i in 0..fm.backStackEntryCount){
            fm.popBackStack()
        }
    }
}