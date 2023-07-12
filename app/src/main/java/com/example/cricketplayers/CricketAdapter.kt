package com.example.cricketplayers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cricketplayers.databinding.RvPlayersBinding

class CricketAdapter (private var dataList : ArrayList<CricketData>,var context : Context) : RecyclerView.Adapter<CricketAdapter.MyViewHolder> (){
   inner class MyViewHolder(var binding : RvPlayersBinding) : ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = RvPlayersBinding.inflate(LayoutInflater.from(context),parent,false)
      return MyViewHolder(binding)
   }

   override fun getItemCount(): Int {
      return dataList.size
   }
   fun setFilterList(m : ArrayList<CricketData>){
      dataList = m
      notifyDataSetChanged()
   }

   override fun onBindViewHolder(holder: CricketAdapter.MyViewHolder, position: Int) {
      val currentBinding = holder.binding

      currentBinding.playerImage.setImageResource(dataList[position].imageId)
      currentBinding.playerName.text = dataList[position].name
      currentBinding.playerDescription.text = dataList[position].description
      val isExpandable = dataList[position].isExpandable
      currentBinding.playerDescription.visibility = if(isExpandable) View.VISIBLE else View.GONE
      currentBinding.constraintLayout.setOnClickListener {
         dataList[position].isExpandable = !dataList[position].isExpandable
         notifyItemChanged(position)
      }


   }
}