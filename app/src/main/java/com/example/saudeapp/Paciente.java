package com.example.saudeapp;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
	private String nome;
	private int idade;
	private String cpf;
	private String telefone;

	// Construtor
	public Paciente(String nome, int idade, String cpf, String telefone) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String var) {
		this.nome = var;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int var) {
		this.idade = var;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String var) {
		this.cpf = var;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String var) {
		this.telefone = var;
	}

	public void exibirDados() {
		System.out.println("Dados do paciente: ");
		System.out.println("Nome: " + nome +
				",\nIdade: " + idade +
				",\nCPF: " + cpf +
				",\nTelefone: " + telefone + "\n");
	}

	public static List<Paciente> filtrarPorEspecialidade(List<Consulta> consultas, String especialidade) {
		List<Paciente> pacientesFiltrados = new ArrayList<>();
		for (Consulta consulta : consultas) {
			if (consulta.getMedico().getEspecialidade().equals(especialidade)) {
				pacientesFiltrados.add(consulta.getPaciente());
			}
		}
		return pacientesFiltrados;
	}

	public int contarConsultas(List<Consulta> consultas) {
		int contador = 0;
		for (Consulta consulta : consultas) {
			if (consulta.getPaciente().equals(this)) {
				contador++;
			}
		}
		return contador;
	}
}
