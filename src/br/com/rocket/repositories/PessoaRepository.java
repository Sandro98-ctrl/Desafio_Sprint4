package br.com.rocket.repositories;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Collections;
import java.util.List;

import br.com.rocket.converters.PessoaConverter;
import br.com.rocket.entities.Pessoa;
import br.com.rocket.helpers.ConsoleHelper;
import br.com.rocket.helpers.FileHelper;

public class PessoaRepository {

	private static final String FILENAME = "pessoas.txt";
	private List<Pessoa> pessoas;
	private FileHelper fileHelper;
	private ConsoleHelper consoleHelper;

	public PessoaRepository() {
		this.fileHelper = new FileHelper();
		this.consoleHelper = new ConsoleHelper();
		this.pessoas = getAllInterno();
	}

	public void adicionar(Pessoa p) throws IOException {
		this.fileHelper.append(PessoaConverter.pessoaToLinha(p), FILENAME);
		this.pessoas.add(p);
	}

	public void excluir(int codigo) throws InvalidObjectException {
		Pessoa p = getByCodigo(codigo);
		excluir(p);
	}

	public void excluir(Pessoa p) {
		try {
			List<String> linhas = this.fileHelper.readLines(FILENAME);
			StringBuilder sb = new StringBuilder();
			String pessoaLinha = PessoaConverter.pessoaToLinha(p);

			for (String linha : linhas) {
				if (linha.equals(pessoaLinha))
					continue;

				sb.append(linha);
				sb.append(System.lineSeparator());
			}

			this.fileHelper.write(sb.toString(), FILENAME);
			this.pessoas.remove(p);
		} catch (Exception e) {
			this.consoleHelper.erro("Erro ao excluir: " + e.getMessage());
		}
	}

	public List<Pessoa> getAll() {
		return Collections.unmodifiableList(this.pessoas);
	}

	public Pessoa getByCodigo(int codigo) throws InvalidObjectException {
		for (Pessoa pessoa : this.pessoas) {
			if (codigo == pessoa.getCodigo())
				return pessoa;
		}

		throw new InvalidObjectException("Código não encontrado");
	}

	public void validar(Pessoa pessoa) throws InvalidObjectException {
		for (Pessoa p : this.pessoas) {
			if (p.getCodigo() == pessoa.getCodigo()) {
				throw new InvalidObjectException("Código já existente");
			}
		}
	}

	private List<Pessoa> getAllInterno() {
		try {
			return PessoaConverter.linhasToPessoas(this.fileHelper.readLines(FILENAME));
		} catch (Exception e) {
			this.consoleHelper.erro("Erro ao carregar dados" + e.getMessage());
			return null;
		}
	}
}
