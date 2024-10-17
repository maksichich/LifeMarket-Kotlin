package com.example.lifemart

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifemart.databinding.NoteListBinding
import com.example.lifemart.databinding.QuizItemRecyclerRowBinding
import com.squareup.picasso.Picasso

class MyAdapterNotes (private val usersList:ArrayList<UserNotes>):
    RecyclerView.Adapter<MyAdapterNotes.MyViewHolder>() {

        class MyViewHolder (private val binding: NoteListBinding): RecyclerView.ViewHolder(binding.root) {



    fun bind(model: UserNotes) {
        binding.apply {
            val notesTitleAdapter: TextView = itemView.findViewById(R.id.notes_title)
            val notesDescriptionAdapter: TextView = itemView.findViewById(R.id.notes_description)
            notesTitleAdapter.text = model.noteTitle
            notesDescriptionAdapter.text = model.noteDescription


            root.setOnClickListener {
                val intent = Intent(root.context, NotePageActivity::class.java)
                root.context.startActivity(intent)
            }
        }
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = NoteListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(usersList[position])


    }


}

