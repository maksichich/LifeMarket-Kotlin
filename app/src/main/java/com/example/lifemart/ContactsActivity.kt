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

class ContactsActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var usersArrayList: ArrayList<Users>
    private var db = Firebase.firestore
    private lateinit var menu:TextView



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contacts)

        menu = findViewById(R.id.tvBack)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        usersArrayList =  arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("user").get().addOnSuccessListener {
            if(!it.isEmpty){
                for (data in it.documents){
                    val user: Users? = data.toObject(Users::class.java)
                    if (user!=null){
                        usersArrayList.add(user)
                    }
                }
                recyclerView.adapter=MyAdapter(usersArrayList)
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