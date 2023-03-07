package com.dicoding.uranimelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.uranimelist.databinding.ActivityAboutBinding
import com.dicoding.uranimelist.databinding.ActivityDetailBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_about)
    }
}