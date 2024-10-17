package com.example.lifemart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var buttonEdit: Button
    private lateinit var database: DatabaseReference
    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvBack : TextView

private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        buttonEdit = findViewById(R.id.buttonEdit)
        database = Firebase.database.reference
        tvName = findViewById(R.id.name)
        tvEmail = findViewById(R.id.email)
        tvBack = findViewById(R.id.Back)

        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = db.collection("user").document(userID)
        ref.get().addOnSuccessListener {
if (it!=null){
val name = it.data?.get("name")?.toString()
    val email=it.data?.get("email")?.toString()
    tvName.text=name
    tvEmail.text=email
        }
        }
            .addOnFailureListener {
                Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show()
            }


        tvBack.setOnClickListener {

            intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonEdit.setOnClickListener {

            intent = Intent(this, ChangeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}