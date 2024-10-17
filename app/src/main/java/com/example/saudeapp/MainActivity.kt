package com.example.saudeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cadastrarMedico(view: View?) {
        startActivity(Intent(this, MedicoActivity::class.java))
    }

    fun marcarConsulta(view: View?) {
        startActivity(Intent(this, ConsultaActivity::class.java))
    }

    fun consultarConsultas(view: View?) {
        startActivity(Intent(this, VerificarConsultasActivity::class.java))
    }
}