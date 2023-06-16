package com.example.notesapp

import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.Adapters.NotesAdapter
import com.example.notesapp.Entity.Database.RoomDB
import com.example.notesapp.Entity.NotesEntity
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.AddDialoagBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var db: RoomDB
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.init(this)

        initview()

    }

    private fun initview() {
        binding.add.setOnClickListener {
            addNoteDialog()
        }

        adapter = NotesAdapter {
            var isPin = false
            if (it.pin) {
                isPin = false

            } else {
                isPin = true
            }

            var data = NotesEntity(it.title, it.text, it.date, it.month, it.year, isPin)
            data.id = it.id
            db.note().updateNote(data)
            adapter.update(filternote(db.note().getNotes()))
        }
        adapter.setNotes(db.note().getNotes())
        binding.notelist.layoutManager = GridLayoutManager(this, 2)
        binding.notelist.adapter = adapter



        fun adddata(notesEntity: NotesEntity) {
            var dialog = Dialog(this)
            var bind = AddDialoagBinding.inflate(layoutInflater)
            dialog.setContentView(bind.root)

        }
    }

    fun filternote(list: List<NotesEntity>) : ArrayList<NotesEntity>{
        var newlist = ArrayList<NotesEntity>()
        for (l: NotesEntity in list){
            if (l.pin){
                newlist.add(l)
            }
        }
        for (l: NotesEntity in list){
            if (!l.pin){
                newlist.add(l)
            }
        }

        return newlist
    }


    fun addNoteDialog() {
        var dialog = Dialog(this)
        var bind = AddDialoagBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)
        bind.edtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YYYY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            bind.edtdate.text = currentDate

            dialog.show()
        }

        bind.edttime.setOnClickListener {

            var date = Date()

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            bind.edttime.text = currentTime
            var seleTime = currentTime

            var dialog1 = TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                }

            }, 10, 0, false)

            dialog1.show()
        }

        bind.btnsubmit.setOnClickListener {

            var title = bind.edttitle.text.toString()
            var text = bind.edttext.text.toString()
            var date = bind.edtdate.text.toString()
            var month = bind.edtdate.text.toString()
            var year = bind.edtdate.text.toString()
            var format = SimpleDateFormat("DD-MM-YYYY hh:mm")
            var current = format.format(Date())
            var data = NotesEntity(title, text, date, month, year,false)
            db.note().addNote(data)
            adapter.update(db.note().getNotes())
            dialog.dismiss()
        }
        dialog.show()
    }

}