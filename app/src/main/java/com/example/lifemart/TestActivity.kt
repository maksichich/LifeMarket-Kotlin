package com.example.lifemart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifemart.databinding.ActivityTestBinding
import com.google.firebase.database.FirebaseDatabase

class TestActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestBinding
    lateinit var quizModelList:MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter
    lateinit var tv_back_quiz: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
tv_back_quiz = findViewById(R.id.tv_back_quiz)
        tv_back_quiz.setOnClickListener{
            intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
        }
        quizModelList = mutableListOf()
        getDataFromFirebase()
    }
    private fun setupRecyclerView(){
        binding.progressBar.visibility=View.GONE
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter =adapter
    }
    private fun getDataFromFirebase(){
        binding.progressBar.visibility= View.VISIBLE
FirebaseDatabase.getInstance().reference
    .get()
    .addOnSuccessListener {dataSnapshot->
        if(dataSnapshot.exists()){
            for (snapshot in dataSnapshot.children){
                val quizModel=snapshot.getValue(QuizModel::class.java)
                if (quizModel != null) {
                    quizModelList.add(quizModel)
                }
            }
        }
        setupRecyclerView()
    }

    }
}