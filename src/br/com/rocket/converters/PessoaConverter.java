package br.com.rocket.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.rocket.entities.Pessoa;

public class PessoaConverter {

	private static final String DELIMITER = ";";

	public static List<Pessoa> linhasToPessoas(List<String> linhas) {
		List<Pessoa> lista = new ArrayList<>();

		for (String linha : linhas) {
			lista.add(linhaToPessoa(linha));
		}

		return lista;
	}

	public static Pessoa linhaToPessoa(String linha) {
		try (Scanner scn = new Scanner(linha.trim())) {
			scn.useDelimiter(DELIMITER);
			Pessoa p = new Pessoa();
			p.setCodigo(scn.nextInt());
			p.setNome(scn.next().trim());
			p.setIdade(scn.nextInt());
			p.setEndereco(scn.next().trim());
			return p;
		}
	}

	public static String pessoaToLinha(Pessoa p) {
		String codigo = String.valueOf(p.getCodigo()).trim();
		String nome = p.getNome().trim();
		String idade = String.valueOf(p.getIdade()).trim();
		String endereco = p.getEndereco().trim();

		return String.join(DELIMITER, codigo, nome, idade, endereco);
	}

}
