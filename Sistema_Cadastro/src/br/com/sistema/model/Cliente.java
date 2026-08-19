package br.com.sistema.model;

import java.util.Date;

public class Cliente {

	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private Date ddn;
	private boolean ativo;
	
	public Cliente () {
		this.ativo = true;
	}
	
	public Cliente (String nome, String cpf, String email, String senha) {
		this();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDdn() {
		return ddn;
	}

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
