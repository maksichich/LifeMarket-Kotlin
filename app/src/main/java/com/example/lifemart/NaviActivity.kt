package com.example.lifemart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class NaviActivity : AppCompatActivity() {

    private lateinit var linkToReg: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_navi)

        linkToReg = findViewById(R.id.link_to_reg)
        linkToReg.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Firebase.auth.signOut()

        }

        val buttonContacts: Button = findViewById(R.id.button_link_to_contacts)

        buttonContacts.setOnClickListener {

            val intent = Intent(this, ContactsActivity::class.java)
            startActivity(intent)
            finish()
        }

        val buttonList: Button = findViewById(R.id.button_link_to_list)

        buttonList.setOnClickListener {

            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)

        }
        val buttonLibrary: Button = findViewById(R.id.button_link_to_library)

        buttonLibrary.setOnClickListener {

            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
            finish()
        }
        val buttonTest: Button = findViewById(R.id.button_link_to_test)

        buttonTest.setOnClickListener {

            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
            finish()
        }
        val buttonProfile: Button = findViewById(R.id.button_profile)
        buttonProfile.setOnClickListener{

            val intent = Intent (this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}