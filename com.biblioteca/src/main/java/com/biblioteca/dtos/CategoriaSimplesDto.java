package com.biblioteca.dtos;


public class CategoriaSimplesDto {

	
	private Long id;
	private String nome;
	private int qtdLivros;
	
	public CategoriaSimplesDto() {}
	
	public CategoriaSimplesDto(Long id, String nome, int qtdLivros) {
		this.id = id;
		this.nome = nome;
		this.qtdLivros = qtdLivros;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getQtdLivros() {
		return qtdLivros;
	}
	
	public void setQtdLivros(int qtdLivros) {
		this.qtdLivros = qtdLivros;
	}
	
}
