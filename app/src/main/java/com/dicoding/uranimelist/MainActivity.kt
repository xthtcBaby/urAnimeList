package com.dicoding.uranimelist

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.uranimelist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAnime = binding.rvAnime
        rvAnime.setHasFixedSize(true)

        list.addAll(getAnimeList())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                rvAnime.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAnime.layoutManager = GridLayoutManager(this,2)
            }
            R.id.about -> {
                val iA = Intent(this@MainActivity,AboutActivity::class.java)
                startActivity(iA)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?,v: View?,menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_main,menu)
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAA = ListAnimeAdapter(list)
        rvAnime.adapter = listAA

        listAA.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Anime) {
                showSelectedAnime(data)
            }
        })
    }

    private fun showSelectedAnime(anime: Anime){
        val iD = Intent(this@MainActivity,DetailActivity::class.java)
        iD.putExtra("DATA", anime)
        startActivity(iD)
    }

    private fun getAnimeList(): ArrayList<Anime> {
        val title = resources.getStringArray(R.array.title)
        val genre = resources.getStringArray(R.array.genre)
        val synopsis = resources.getStringArray(R.array.synopsis)
        val image = resources.getStringArray(R.array.image)
        val listAnime = ArrayList<Anime>()
        for (i in title.indices){
            val anime = Anime(title[i], genre[i], synopsis[i], image[i])
            listAnime.add(anime)
        }
        return listAnime
    }
}