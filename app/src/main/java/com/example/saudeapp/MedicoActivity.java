package com.example.saudeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MedicoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);
    }

    public void cadastrarMedico(View view) {
        EditText nomeField = findViewById(R.id.nome);
        EditText idadeField = findViewById(R.id.idade);
        EditText crmField = findViewById(R.id.crm);
        EditText especialidadeField = findViewById(R.id.especialidade);

        String nome = nomeField.getText().toString();
        int idade = Integer.parseInt(idadeField.getText().toString());
        String crm = crmField.getText().toString();
        String especialidade = especialidadeField.getText().toString();

        Medico medico = new Medico(nome, idade, crm, especialidade);
        Medico.adicionarMedico(medico); // Armazenar o médico

        Toast.makeText(this, "Médico cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
