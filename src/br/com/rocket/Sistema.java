package br.com.rocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.naming.directory.InvalidAttributesException;

import br.com.rocket.entities.Pessoa;
import br.com.rocket.helpers.ConsoleHelper;
import br.com.rocket.helpers.FileHelper;

public class Sistema {

	private int opcao;
	private FileHelper fileHelper;
	private ConsoleHelper consoleHelper;
	private Scanner scanner;

	public Sistema() {
		fileHelper = new FileHelper();
		consoleHelper = new ConsoleHelper();
		scanner = new Scanner(System.in);
	}

	public void start() {
		loadCabecalho();

		while (true) {
			menuOpcoes();
			inputReader();
			selecionarOpcao();
		}
	}

	public void end() {
		scanner.close();
		consoleHelper.message("Até Mais!");
		System.exit(-1);
	}

	private void loadCabecalho() {
		try {
			System.out.println(fileHelper.readToEnd("Compasso ART ASCII"));
			System.out.println();
		} catch (Exception e) {
			consoleHelper.erro("Template do Cabeçalho não Encontrado!");
		}
	}

	private void menuOpcoes() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----------------------------------");
		sb.append(System.lineSeparator());
		sb.append("## Escolha uma das opções abaixo ##");
		sb.append(System.lineSeparator());
		sb.append("Opção 1 - Cadastrar Pessoa");
		sb.append(System.lineSeparator());
		sb.append("Opção 2 - Imprimir Pessoas Cadastradas");
		sb.append(System.lineSeparator());
		sb.append("Opção 0 - Sair do Programa");
		sb.append(System.lineSeparator());
		sb.append("-----------------------------------");
		System.out.println(sb.toString());
	}

	private void inputReader() {
		System.out.print("Informe aqui sua opção: ");

		try {
			opcao = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			opcao = -1;
			consoleHelper.erro("Formato Incorreto");
		}
		
		System.out.println("---------------------------");
	}

	private void selecionarOpcao() {
		switch (opcao) {
		case 0:
			end();
			break;
		case 1:
			cadastrarPessoa();
			break;
		case 2:
			mostrarPessoas();
			break;
		// case 3:
		// excluirPessoa();
		// break;
		default:
			consoleHelper.erro("Valor de operação inválido");
			break;
		}
	}

	private void cadastrarPessoa() {
		try {
			Pessoa pessoa = new Pessoa();
			System.out.print("Informe o Código: ");
			pessoa.setCodigo(Integer.parseInt(scanner.nextLine()));
			System.out.print("Informe o Nome: ");
			pessoa.setNome(scanner.nextLine());
			System.out.print("Informe a Idade: ");
			pessoa.setIdade(Integer.parseInt(scanner.nextLine()));
			System.out.print("Informe o Endereço: ");
			pessoa.setEndereco(scanner.nextLine());

			validarDados(pessoa);
			fileHelper.append(pessoa.getInfo());
		} catch (Exception e) {
			consoleHelper.erro("Erro no cadastro: " + e.getMessage());
		}
	}

	private void mostrarPessoas() {
		try {
			List<Pessoa> lista = getPessoasFromFile();
			// lista.sort();
			lista.forEach(p -> System.out.println(p));
		} catch (Exception e) {
			consoleHelper.erro("Erro ao mostrar: " + e.getMessage());
		}
	}

	private void validarDados(Pessoa pessoa) throws Exception {
		List<Pessoa> pessoas = getPessoasFromFile();

		for (Pessoa p : pessoas) {
			if (p.getCodigo() == pessoa.getCodigo()) {
				throw new InvalidAttributesException("Código já existente");
			}
		}
	}

	private List<Pessoa> getPessoasFromFile() throws IOException {
		List<String> linhas = fileHelper.readLines();
		List<Pessoa> lista = new ArrayList<>();

		for (String linha : linhas) {
			try (Scanner scn = new Scanner(linha.trim())) {
				scn.useDelimiter(",");
				Pessoa p = new Pessoa();
				p.setCodigo(scn.nextInt());
				p.setNome(scn.next().trim());
				p.setIdade(scn.nextInt());
				p.setEndereco(scn.next().trim());
				lista.add(p);
			}
		}

		return lista;
	}
}
