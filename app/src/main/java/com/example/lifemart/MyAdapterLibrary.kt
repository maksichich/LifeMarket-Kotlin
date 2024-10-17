package com.example.lifemart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapterLibrary(private val usersList1:ArrayList<Users1>):
    RecyclerView.Adapter<MyAdapterLibrary.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvNameLibrary: TextView =itemView.findViewById(R.id.tv_name_library)
        val tvTitleLibrary: TextView =itemView.findViewById(R.id.tv_title_library)
        val tvDescription1Library: TextView =itemView.findViewById(R.id.tv_description1_library)
        val tvDescription2Library: TextView =itemView.findViewById(R.id.tv_description2_library)
        val tvDescription3Library: TextView =itemView.findViewById(R.id.tv_description3_library)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_library,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return usersList1.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var name2 =  usersList1[position].name1
        var title2 = usersList1[position].title1
        var Desc1 = usersList1[position].description1
        var Desc2 = usersList1[position].description2
        var Desc3 = usersList1[position].description3
        holder.tvNameLibrary.text =  "$name2"
        holder.tvTitleLibrary.text =  "$title2"
        holder.tvDescription1Library.text = "$Desc1"
        holder.tvDescription2Library.text = "$Desc2"
        holder.tvDescription3Library.text = "$Desc3"

    }
}