package com.example.saudeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class VerificarConsultasActivity extends AppCompatActivity {

    private ListView listViewConsultas;
    private ArrayAdapter<String> consultasAdapter;
    private List<String> consultasList;
    private SearchView searchViewEspecialidade;
    private TextView consultaCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_consultas);

        listViewConsultas = findViewById(R.id.listViewConsultas);
        searchViewEspecialidade = findViewById(R.id.searchEspecialidade);
        consultaCountTextView = findViewById(R.id.consultaCountTextView);

        // Carregar todas as consultas inicialmente
        consultasList = carregarConsultas();
        consultasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consultasList);
        listViewConsultas.setAdapter(consultasAdapter);

        // Configurar o SearchView para filtrar por especialidade
        searchViewEspecialidade.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtrarPacientesPorEspecialidade(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Opcional: Filtrar conforme o texto é digitado
                filtrarPacientesPorEspecialidade(newText);
                return true;
            }
        });

        // Atualizar a contagem de consultas
        atualizarContagemDeConsultas(Consulta.obterConsultas());
    }

    // Método para carregar as consultas na lista
    private List<String> carregarConsultas() {
        List<String> listaConsultas = new ArrayList<>();
        List<Consulta> consultas = Consulta.obterConsultas();

        for (Consulta consulta : consultas) {
            String detalhesConsulta = "Paciente: " + consulta.getPaciente().getNome() +
                    ", Médico: " + consulta.getMedico().getNome() +
                    ", Especialidade: " + consulta.getMedico().getEspecialidade() +
                    ", Data: " + consulta.getData().toString();
            listaConsultas.add(detalhesConsulta);
        }

        return listaConsultas;
    }

    // Método para filtrar os pacientes por especialidade médica
    private void filtrarPacientesPorEspecialidade(String especialidade) {
        List<Consulta> consultas = Consulta.obterConsultas();

        // Usa o método 'filtrarPorEspecialidade' para obter os pacientes filtrados
        List<Paciente> pacientesFiltrados = Paciente.filtrarPorEspecialidade(consultas, especialidade);
        List<String> pacientesDetalhes = new ArrayList<>();

        // Adiciona os detalhes de cada paciente filtrado na lista
        for (Paciente paciente : pacientesFiltrados) {
            String detalhesPaciente = "Nome: " + paciente.getNome() +
                    ", Idade: " + paciente.getIdade() +
                    ", Telefone: " + paciente.getTelefone() +
                    ", Consultas Agendadas: " + paciente.contarConsultas(consultas); // Exibe a contagem de consultas
            pacientesDetalhes.add(detalhesPaciente);
        }

        // Atualiza o adapter com os pacientes filtrados
        consultasAdapter.clear();
        consultasAdapter.addAll(pacientesDetalhes);
        consultasAdapter.notifyDataSetChanged();

        // Atualizar a contagem de consultas
        atualizarContagemDeConsultas(consultas);
    }

    // Método para atualizar a contagem de consultas no TextView
    private void atualizarContagemDeConsultas(List<Consulta> consultas) {
        int totalConsultas = consultas.size();
        consultaCountTextView.setText("Total de consultas: " + totalConsultas);
    }
}