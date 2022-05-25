package com.example.wb_week_4_1_2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.wb_week_4_1_2.R
import com.example.wb_week_4_1_2.databinding.ActivityMainBinding
import com.example.wb_week_4_1_2.view.main.MainFragment



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_conteiner, MainFragment.newInstance()).commitAllowingStateLoss()
        }
    }
}