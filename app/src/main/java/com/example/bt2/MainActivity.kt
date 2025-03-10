package com.example.bt2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bt2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val edtHoTen = findViewById<EditText>(R.id.editTextText)
        val edtTuoi = findViewById<EditText>(R.id.editTextText2)
        val btnCheck = findViewById<Button>(R.id.button2)
        val tvResult = findViewById<TextView>(R.id.textView4)

        btnCheck.setOnClickListener({
            val hoTen = edtHoTen.text.toString().trim()
            val tuoi = edtTuoi.text.toString().trim()

            if(hoTen.isEmpty()){
                edtHoTen.error = "Vui lòng nhập tên lại!"
                return@setOnClickListener
            }

            if(tuoi.isEmpty()){
                edtTuoi.error = "Vui lòng nhập tuổi lại!"
                return@setOnClickListener
            }

            val tuoiInt = tuoi.toInt()

            val result = when{
                tuoiInt > 65 ->"Người già"
                tuoiInt in 6..65 -> "Người lớn"
                tuoiInt in 2..5 -> "Trẻ em"
                tuoiInt <2 -> "Em bé"
                else -> "Không hợp lệ"
            }
            edtTuoi.error = null
            edtHoTen.error = null
            tvResult.text = "$hoTen là $result"
        })
    }
}