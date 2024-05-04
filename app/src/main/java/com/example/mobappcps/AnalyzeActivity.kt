package com.example.mobappcps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobappcps.databinding.ActivityAnalyzeBinding

class AnalyzeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnalyzeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAnalyzeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }
}