package com.example.lifemart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    private lateinit var tvRedirectSignUp: TextView
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmailAddress)
        etPass = findViewById(R.id.etPassword)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {

            login()
        }

        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
    override fun  onStart(){

        super.onStart()
        val currentUser = auth.currentUser
        val verification = auth.currentUser?.isEmailVerified
        if (currentUser != null){
        if (verification==true ) {

            val intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
        }
    }}
    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        if (email.isBlank() || pass.isBlank()) {

            Toast.makeText(this, "Поле не может быть пустым", Toast.LENGTH_SHORT).show()
            return
        }


        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {

            if (it.isSuccessful) {
                val verification = auth.currentUser?.isEmailVerified
                if (verification==true) {


                    Toast.makeText(this, "Успешный вход", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, NaviActivity::class.java)
                    startActivity(intent)
                }

                else
                {
                    auth.currentUser?.sendEmailVerification()
                    Toast.makeText(this, "Подтвердите почту!", Toast.LENGTH_SHORT).show()
                }

            }
        else

                Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
        }
    }
}


