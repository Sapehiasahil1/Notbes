package com.example.notbes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notbes.Models.Note

//import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.notbes.databinding.ActivityAddNoteBinding
import com.example.notbes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class AddNote : AppCompatActivity() {

    private lateinit var binding :ActivityAddNoteBinding

    private lateinit var note : Note
    private lateinit var old_note : Note
    var isUpdate  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true
        }catch (e : Exception){

            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener{

            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if(title.isNotEmpty() || note_desc.isNotEmpty()){

                val formatter = SimpleDateFormat("EEE, MM, YYYY,HH:MM")

                if(isUpdate){

                    note = Note(
                        old_note.id,title,note_desc,formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null,title,note_desc,formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else{
                Toast.makeText(this@AddNote, "Please add some data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.imgBackArrow.setOnClickListener {

            onBackPressed()
        }
    }
}