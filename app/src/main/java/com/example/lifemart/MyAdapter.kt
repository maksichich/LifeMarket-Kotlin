package com.example.lifemart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MyAdapter(private val usersList:ArrayList<Users>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var imageViewContacts:ImageView=itemView.findViewById(R.id.imageViewContacts)
        val tvFullName:TextView=itemView.findViewById(R.id.tv_fullName)
        val tvWorkMail:TextView=itemView.findViewById(R.id.tv_workMail)
        val tvWorkNumber:TextView=itemView.findViewById(R.id.tv_workNumber)
        val tvPosition:TextView=itemView.findViewById(R.id.tv_posititon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_contacts,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.imageViewContacts) {
            with(usersList[position].pic) {
                val imageView:ImageView=findViewById(R.id.imageViewContacts)
                Picasso.get().load(this).into(imageView)
            }
        }
        var workMailDescr =  usersList[position].workMail
        var workNumberDescr = usersList[position].workNumber
        var workPositionDescr = usersList[position].position
        holder.tvFullName.text = usersList[position].fullName
        holder.tvWorkMail.text = "почта: $workMailDescr"
        holder.tvWorkNumber.text =  "телефон: $workNumberDescr"
        holder.tvPosition.text = "должность: $workPositionDescr"
    }
}

private fun ImageView.setImageURI() {
    TODO("Not yet implemented")
}


