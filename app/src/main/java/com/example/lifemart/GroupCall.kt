package com.example.lifemart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



class GroupCall : AppCompatActivity() {

    private lateinit var linkToReg: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.groupcall)

        linkToReg = findViewById(R.id.goBackToContacts)
        linkToReg.setOnClickListener {

            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)

        }

        val buttonChooseUsers: Button = findViewById(R.id.chooseUsers)
        buttonChooseUsers.setOnClickListener{

            val intent = Intent (this, ProfileActivity::class.java)
            startActivity(intent)
            finish()

        }
        val buttonCreateCall: Button = findViewById(R.id.createCall)
        buttonCreateCall.setOnClickListener{

            val intent = Intent (this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}