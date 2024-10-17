package com.example.lifemart


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifemart.R.id.layout_click
import com.example.lifemart.databinding.ActivityNotesBinding
import com.example.lifemart.databinding.ActivityTestBinding
import com.example.lifemart.databinding.CreateNoteBinding
import com.example.lifemart.databinding.NoteListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class NotesActivity : AppCompatActivity() {

    private var db=Firebase.firestore
    private lateinit var button1: Button
    private lateinit var tv_note: TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var usersArrayList: ArrayList<UserNotes>
    private var notesTitle : EditText? = null
    private var notesDescription : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        tv_note = findViewById(R.id.note_tv_To_Navi)
        button1 = findViewById(R.id.note_Btn_To_Create)
        recyclerView = findViewById(R.id.recycler_view_notes)

        recyclerView.layoutManager = LinearLayoutManager(this)
        usersArrayList =  arrayListOf()
        db = FirebaseFirestore.getInstance()
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("user").document(userID).collection("notes").get().addOnSuccessListener {
            if(!it.isEmpty){
                for (data in it.documents){
                    val notes: UserNotes? = data.toObject(UserNotes::class.java)
                    if (notes!=null){
                        usersArrayList.add(notes)
                    }
                }
                recyclerView.adapter=MyAdapterNotes(usersArrayList)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

        tv_note.setOnClickListener {
            intent = Intent(this, NaviActivity::class.java)
            startActivity(intent)
        }

        button1.setOnClickListener {
            
                val dialogBinding = CreateNoteBinding.inflate(layoutInflater)
                dialogBinding.apply {
                    failedSave.visibility= View.GONE
                    completeSave.visibility= View.GONE
                   notesTitle = dialogBinding.nTitle1
                    notesDescription = dialogBinding.nDesription1
                    
                    dialogBinding.noteBtn1.setOnClickListener {

                        val mapNote = HashMap<String, Any>()
                        val nTitle = notesTitle!!.text.toString()
                        val nDescription = notesDescription!!.text.toString()
                        mapNote["noteTitle"] = nTitle
                        mapNote["noteDescription"] = nDescription

                        db.collection("user").document(userID).collection("notes").add(mapNote)
                            .addOnCompleteListener {
                                completeSave.visibility= View.VISIBLE
                                failedSave.visibility= View.GONE
                                notesTitle!!.text.clear()
                                notesDescription!!.text.clear()
                                recreate()

                            }
                            .addOnFailureListener{
                                failedSave.visibility= View.VISIBLE
                                completeSave.visibility= View.GONE
                            }
                    }}
                AlertDialog.Builder(this)
                    .setView(dialogBinding.root)
                    .setCancelable(true)
                    .show()
            
            
            }

    }
    }





