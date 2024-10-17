package com.example.lifemart

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lifemart.databinding.ActivityChangeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso


class ChangeActivity : AppCompatActivity() {

    private var db = FirebaseFirestore.getInstance()
    private var firebaseStore: FirebaseStorage? = null
    private lateinit var binding: ActivityChangeBinding
    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageUri: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)
        binding = ActivityChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVars()
        registerClickEvents()
        firebaseStore = FirebaseStorage.getInstance()
        storageRef = FirebaseStorage.getInstance().reference
        val photoChange:ImageView = findViewById(R.id.imageViewChange)
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("user").document(userID).get().addOnSuccessListener{document->
            if (document!=null){
                val imageView = document.getString("pic")
                Picasso.get().load(imageView).into(photoChange)
            } else {

            }
        }
            .addOnFailureListener{
                Toast.makeText(this,"Ошибка!!!",Toast.LENGTH_SHORT).show()
            }
    }
private fun back(){
    intent = Intent(this, ProfileActivity::class.java)
    startActivity(intent)
}
    private fun registerClickEvents() {
        binding.uploadBtn.setOnClickListener {
            uploadImage()
        }
        binding.goProfileTv.setOnClickListener {
            back()
        }


        binding.uploadImageBtn.setOnClickListener {
            resultLauncher.launch("image/*")
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {

        imageUri = it
        binding.imageViewChange.setImageURI(it)
    }


    private fun initVars() {

        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun uploadImage() {
        binding.progressBarChange.visibility = View.VISIBLE
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageUri?.let {
            storageRef.putFile(it).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val etFullName: EditText? = findViewById(R.id.editFullName)
                        val etWorkNumber: EditText? = findViewById(R.id.editWorkNumber)
                        val etWorkMail: EditText? = findViewById(R.id.editWorkMail)
                        val etPosition: EditText? = findViewById(R.id.editPosition)
                        val etNickname: EditText? = findViewById(R.id.editNickname)
                        val fullName = etFullName!!.text.toString()
                        val workMail = etWorkMail!!.text.toString()
                        val workNumber = etWorkNumber!!.text.toString()
                        val position = etPosition!!.text.toString()
                        val nickname = etNickname!!.text.toString()
                        val map = HashMap<String, Any>()
                        map["pic"] = uri.toString()
                        map["fullName"] = fullName
                            map["workMail"] = workMail
                                map["workNumber"] = workNumber
                                    map["position"] = position
                                         map["nickname"] = nickname

                        val userID = FirebaseAuth.getInstance().currentUser!!.uid
                        firebaseFirestore.collection("user").document(userID).update(map).addOnCompleteListener { firestoreTask ->

                            if (firestoreTask.isSuccessful){
                                Toast.makeText(this, "Успешное создание контакта", Toast.LENGTH_SHORT).show()

                            }else{
                                Toast.makeText(this,"Ошибка при создании контакта!", Toast.LENGTH_SHORT).show()
                                Toast.makeText(this,firestoreTask.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                            binding.progressBarChange.visibility = View.GONE

                        }
                    }
                } else {
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    binding.progressBarChange.visibility = View.GONE

                }}

    }}}