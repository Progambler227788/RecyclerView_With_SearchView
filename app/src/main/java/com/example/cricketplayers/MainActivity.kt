package com.example.cricketplayers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketplayers.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: CricketAdapter
    private lateinit var list : ArrayList<CricketData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        list = ArrayList()
        list.add(CricketData(R.drawable.babarazam,"Babar Azam"))
        list.add(CricketData(R.drawable.virat_kohli,"Virat Kohli"))
        list.add(CricketData(R.drawable.kane_mama,"Kane WilliamSon"))

        adapter = CricketAdapter(list,this)
        binding.recyclerView.adapter = adapter
        makeSearch()


    }
    fun makeSearch(){
      binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
          androidx.appcompat.widget.SearchView.OnQueryTextListener {
          override fun onQueryTextChange(p0: String?): Boolean {
              filterList(p0?.lowercase(Locale.ROOT))
              return true
          }

          override fun onQueryTextSubmit(p0: String?): Boolean {
              return false
          }
      })


    }
    fun filterList(s : String?){
        if(s!=null){
         val filtered= ArrayList<CricketData> ()
            for( i in list ){
                if(i.name.lowercase(Locale.ROOT).contains(s))
                    filtered.add(i)
            }
            if(filtered.isEmpty()){
                Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show()
            }
            else{
                adapter.setFilterList(filtered)
            }

        }


    }
}