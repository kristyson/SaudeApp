package com.example.saudeapp;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Consulta {
	private Paciente paciente;
	private Medico medico;
	private LocalDateTime data;

	// Lista estática para armazenar consultas
	private static List<Consulta> consultas = new ArrayList<>();

	public Consulta(Paciente paciente, Medico medico, LocalDateTime data) {
		this.paciente = paciente;
		this.medico = medico;
		this.data = data;
	}

	// Métodos existentes...
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente var) {
		this.paciente = var;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico var) {
		this.medico = var;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime var) {
		this.data = var;
	}

	public boolean agendarConsulta() {
		if (medico.verificarDisponibilidade()) {
			System.out.println("Consulta marcada com sucesso, data da consulta: " + data);
			return true;
		} else {
			System.out.println("Consulta indisponível!");
			return false;
		}
	}

	public void exibirDetalhes() {
		System.out.println("Detalhes da consulta: ");
		System.out.println("Nome do paciente: " + paciente.getNome() + "\nNome do medico: " + medico.getNome() + "\nData da consulta: " + getData());
	}

	// Métodos para gerenciar a lista de consultas
	public static void adicionarConsulta(Consulta consulta) {
		consultas.add(consulta);
		Log.d("Consulta", "Consulta adicionada. Total de consultas: " + consultas.size()); // Log para verificar
	}

	public static List<Consulta> obterConsultas() {
		return consultas;
	}

}
