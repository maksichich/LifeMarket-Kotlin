package com.example.lifemart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etName: EditText
    private lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth
    private var db=Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        etEmail = findViewById(R.id.etSEmailAddress)
        etName = findViewById(R.id.etSName)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)
        auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun saveData() {

        val sEmail=etEmail.text.toString().trim()
        val sName=etName.text.toString().trim()

        val userMap = hashMapOf(
            "email" to sEmail,
            "name" to sName
        )
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("user").document(userId).set(userMap)
            .addOnSuccessListener {
                Toast.makeText(this,"Успешная регистрация!", Toast.LENGTH_SHORT).show()
                etEmail.text.clear()
                etName.text.clear()
                etPass.text.clear()
                etConfPass.text.clear()
            }
            .addOnFailureListener{
                Toast.makeText(this,"Ошибка!", Toast.LENGTH_SHORT).show()
            }
    }
    private fun signUpUser() {

        val email = etEmail.text.toString()
        val name = etName.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        if (email.isBlank() || name.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {

            Toast.makeText(this, "Поле не может быть пустым", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {

            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {

            if (it.isSuccessful) {

                auth.currentUser?.sendEmailVerification()
                    ?.addOnSuccessListener {
                        Toast.makeText(this, "Подтвердите адрес почты!", Toast.LENGTH_SHORT).show()
                        saveData()
                    }
                    ?.addOnFailureListener{
                    }
            }
            else {
                Toast.makeText(this, "email уже зарегистрирован или неверен", Toast.LENGTH_SHORT).show()
            }
        }
    }
}