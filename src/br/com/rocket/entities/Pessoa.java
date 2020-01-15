package br.com.rocket.entities;

public class Pessoa {
	
	private int codigo;
	private String nome;
	private int idade;
	private String endereco;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws IllegalArgumentException {
		if (codigo < 0) {
			throw new IllegalArgumentException("Código Inválido");
		}

		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.isEmpty()) {
			throw new IllegalArgumentException("Nome Vazio");
		}

		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) throws IllegalArgumentException {
		if (idade < 0) {
			throw new IllegalArgumentException("Idade Inválida");
		}

		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if (endereco.isEmpty()) {
			throw new IllegalArgumentException("Endereço Vazio");
		}

		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return String.format("Código: %d, Nome: %s, Idade: %d, Endereço: %s", this.codigo, this.nome, this.idade,
				this.endereco);
	}
}
