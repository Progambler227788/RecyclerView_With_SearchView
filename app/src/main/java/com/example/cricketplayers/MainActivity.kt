package com.example.cricketplayers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketplayers.apis.ApiInterface
import com.example.cricketplayers.apis.ApiUtilities
import com.example.cricketplayers.constants.Constant
import com.example.cricketplayers.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
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

        val matchApi = ApiUtilities.getInstance().create(ApiInterface::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            val result = matchApi.getPlayers(Constant.API_KEY,Constant.PLAYER_ID)
            val data = result.body()!!.data
            if(data!=null){
              list.add(CricketData(R.drawable.babarazam,data.name,"" +
                      "Country : ${data.country}\nDate of Birth : ${data.country}\nBatting Style: ${data.battingStyle}\n" +
                      "Bowling Style: ${data.bowlingStyle}\nBatting Style: ${data.battingStyle}"))

                Log.i("Data","${result.body()!!.data.placeOfBirth}")
            }
            withContext(Dispatchers.Main){
                adapter = CricketAdapter(list,this@MainActivity)
                binding.recyclerView.adapter = adapter
            }
        }


        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        list = ArrayList()
        list.add(CricketData(R.drawable.babarazam,"Babar Azam","Great batsman"))
        list.add(CricketData(R.drawable.virat_kohli,"Virat Kohli","GOAT"))
        list.add(CricketData(R.drawable.kane_mama,"Kane WilliamSon","Defensive"))

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