package com.example.saudeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
    }

    public void marcarConsulta(View view) {
        EditText pacienteNomeField = findViewById(R.id.paciente_nome);
        EditText pacienteIdadeField = findViewById(R.id.paciente_idade);
        EditText medicoCrmField = findViewById(R.id.medico_crm);
        EditText dataField = findViewById(R.id.data_consulta);

        String pacienteNome = pacienteNomeField.getText().toString();
        String pacienteIdadeStr = pacienteIdadeField.getText().toString();
        String medicoCrm = medicoCrmField.getText().toString();
        String dataStr = dataField.getText().toString();

        // Verifica se os campos estão preenchidos
        if (pacienteNome.isEmpty() || pacienteIdadeStr.isEmpty() || medicoCrm.isEmpty() || dataStr.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Converte e valida os dados
        int pacienteIdade;
        LocalDateTime data;
        try {
            pacienteIdade = Integer.parseInt(pacienteIdadeStr); // Valida a idade
            data = LocalDateTime.parse(dataStr); // Valida a data no formato correto
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Idade inválida!", Toast.LENGTH_SHORT).show();
            return;
        } catch (DateTimeParseException e) {
            Toast.makeText(this, "Data inválida! Use o formato yyyy-MM-ddTHH:mm.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Busca o médico pelo CRM
        Medico medico = buscarMedicoPorCRM(medicoCrm);
        if (medico == null) {
            Toast.makeText(this, "Médico não encontrado!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria o paciente e a consulta
        Paciente paciente = new Paciente(pacienteNome, pacienteIdade, "", "");
        Consulta consulta = new Consulta(paciente, medico, data);

        // Agenda a consulta
        if (consulta.agendarConsulta()) {
            Consulta.adicionarConsulta(consulta); // Adiciona à lista de consultas
            Toast.makeText(this, "Consulta marcada com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Consulta indisponível!", Toast.LENGTH_SHORT).show();
        }
    }


    // Método para buscar um médico pelo CRM
    private Medico buscarMedicoPorCRM(String crm) {
        List<Medico> medicos = Medico.obterMedicos();
        for (Medico medico : medicos) {
            if (medico.getCrm().equals(crm)) {
                return medico;
            }
        }
        return null;
    }
}
