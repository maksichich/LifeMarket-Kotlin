package com.example.lifemart

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LibraryActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var usersArrayList: ArrayList<Users1>
    private var db = Firebase.firestore
    private lateinit var menu:TextView



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_library)

        menu = findViewById(R.id.tvGoBack)
        recyclerView = findViewById(R.id.recyclerviewLibrary)
        recyclerView.layoutManager = LinearLayoutManager(this)
        usersArrayList =  arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("library").get().addOnSuccessListener {
            if(!it.isEmpty){
                for (data in it.documents){
                    val library: Users1? = data.toObject(Users1::class.java)
                    if (library!=null){
                        usersArrayList.add(library)
                    }
                }
                recyclerView.adapter=MyAdapterLibrary(usersArrayList)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

        menu.setOnClickListener {

            intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}