package br.com.rocket;

import java.util.List;
import java.util.Scanner;

import br.com.rocket.entities.Pessoa;
import br.com.rocket.helpers.ConsoleHelper;
import br.com.rocket.helpers.FileHelper;
import br.com.rocket.repositories.PessoaRepository;

public class Sistema {

	private int opcao;
	private ConsoleHelper consoleHelper;
	private Scanner scanner;
	private PessoaRepository pessoaRepository;

	public Sistema() {
		consoleHelper = new ConsoleHelper();
		scanner = new Scanner(System.in, FileHelper.CHARSETDEFAULT);
		pessoaRepository = new PessoaRepository();
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
		consoleHelper.message("At� Mais!");
		System.exit(0);
	}

	private void loadCabecalho() {
		try {
			System.out.println(new FileHelper().readToEnd("Compasso ART ASCII"));
			System.out.println();
		} catch (Exception e) {
			consoleHelper.erro("Template do Cabe�alho n�o Encontrado!");
		}
	}

	private void menuOpcoes() {
		StringBuilder sb = new StringBuilder();
		sb.append("-----------------------------------");
		sb.append(System.lineSeparator());
		sb.append("## Escolha uma das op��es abaixo ##");
		sb.append(System.lineSeparator());
		sb.append("Op��o 1 - Cadastrar Pessoa");
		sb.append(System.lineSeparator());
		sb.append("Op��o 2 - Imprimir Pessoas Cadastradas");
		sb.append(System.lineSeparator());
		sb.append("Op��o 3 - Excluir Pessoa");
		sb.append(System.lineSeparator());
		sb.append("Op��o 0 - Sair do Programa");
		sb.append(System.lineSeparator());
		sb.append("-----------------------------------");
		System.out.println(sb.toString());
	}

	private void inputReader() {
		System.out.print("Informe aqui sua op��o: ");

		try {
			opcao = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			opcao = -1;
			consoleHelper.erro("Formato incorreto");
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
		case 3:
			excluirPessoa();
			break;
		default:
			consoleHelper.erro("Valor de opera��o inv�lido");
			break;
		}
	}

	private void cadastrarPessoa() {
		try {
			Pessoa pessoa = new Pessoa();
			System.out.print("Informe o C�digo: ");
			pessoa.setCodigo(Integer.parseInt(scanner.nextLine()));
			System.out.print("Informe o Nome: ");
			pessoa.setNome(scanner.nextLine());
			System.out.print("Informe a Idade: ");
			pessoa.setIdade(Integer.parseInt(scanner.nextLine()));
			System.out.print("Informe o Endere�o: ");
			pessoa.setEndereco(scanner.nextLine());

			this.pessoaRepository.validar(pessoa);
			this.pessoaRepository.adicionar(pessoa);
		} catch (Exception e) {
			this.consoleHelper.erro("Erro ao cadastrar: " + e.getMessage());
		}
	}

	private void mostrarPessoas() {
		try {
			List<Pessoa> lista = this.pessoaRepository.getAll();
			lista.forEach(p -> System.out.println(p));
		} catch (Exception e) {
			consoleHelper.erro("Erro ao mostrar: " + e.getMessage());
		}
	}

	private void excluirPessoa() {
		try {
			System.out.print("Informe o C�digo: ");
			int codigo = Integer.parseInt(scanner.nextLine());
			Pessoa p = this.pessoaRepository.getPessoaByCodigo(codigo);
			this.pessoaRepository.excluir(p);
		} catch (Exception e) {
			consoleHelper.erro("Erro ao excluir: " + e.getMessage());
		}
	}
}
