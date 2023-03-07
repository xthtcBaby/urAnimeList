package com.dicoding.uranimelist

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.uranimelist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dAnime = intent.getParcelableExtra<Anime>("DATA")

        binding.tvTitle.text = dAnime?.title
        binding.tvGenre.text = dAnime?.genre
        binding.tvSynopsis.text = dAnime?.synopsis
        Glide.with(this)
            .load(dAnime?.image)
            .into(binding.image)

        binding.actionShare.setOnClickListener{
            val iS = Intent(Intent.ACTION_SEND)
            iS.type = "text/plain"
            iS.putExtra("Share","uranimelist.xtc/")
            val iC = Intent.createChooser(iS,"Sage With")
            startActivity(iC)
        }

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}