package com.learning.kotlinsimplecurd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class insertactivity : AppCompatActivity() {

    private lateinit var etName : EditText
    private lateinit var etNohp : EditText
    private lateinit var etNoktp : EditText
    private lateinit var etAlamat : EditText
    private lateinit var etPekerjaan : EditText
    private lateinit var etRincian : EditText
    private lateinit var etTujuan : EditText
    private lateinit var btnSaveData : Button
    private lateinit var btnMenu : Button

    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertactivity)

        etName = findViewById(R.id.etName)
        etNohp = findViewById(R.id.etNohp)
        etNoktp = findViewById(R.id.etNoktp)
        etAlamat = findViewById(R.id.etAlamat)
        etPekerjaan = findViewById(R.id.etPekerjaan)
        etRincian = findViewById(R.id.etRincian)
        etTujuan = findViewById(R.id.etTujuan)
        btnSaveData = findViewById(R.id.btnSave)
        btnMenu = findViewById(R.id.btnMenu)

        dbref = FirebaseDatabase.getInstance().getReference("Permohonan Informasi")

        btnMenu.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData(){

        //getting values
        val name = etName.text.toString()
        val nohp = etNohp.text.toString()
        val noktp = etNoktp.text.toString()
        val alamat = etAlamat.text.toString()
        val pekerjaan = etPekerjaan.text.toString()
        val rincian_informasi = etRincian.text.toString()
        val tujuan_informasi = etTujuan.text.toString()

        if (name.isEmpty()){
            etName.error = "Please enter drug name"
        }
        if (nohp.isEmpty()){
            etNohp.error = "Please enter drug age"
        }
        if (noktp.isEmpty()){
            etNoktp.error = "Please enter drug salary"
        }
        if (alamat.isEmpty()){
            etAlamat.error = "Please enter drug salary"
        }
        if (pekerjaan.isEmpty()){
            etPekerjaan.error = "Please enter drug salary"
        }
        if (rincian_informasi.isEmpty()){
            etRincian.error = "Please enter drug salary"
        }
        if (tujuan_informasi.isEmpty()){
            etTujuan.error = "Please enter drug salary"
        }

        val empId = dbref.push().key!!

        val employee = EmployeeModel(empId, name, nohp, noktp, alamat, pekerjaan, rincian_informasi, tujuan_informasi)

        dbref.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted succesfuly", Toast.LENGTH_LONG).show()

                etName.text.clear()
                etNohp.text.clear()
                etNoktp.text.clear()
                etAlamat.text.clear()
                etPekerjaan.text.clear()
                etRincian.text.clear()
                etTujuan.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Eror ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}