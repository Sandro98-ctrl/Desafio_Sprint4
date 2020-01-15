package br.com.rocket.helpers;

public class ConsoleHelper {

	public void message(String msg) {
		String border = buildBorder(msg);
		System.out.println(border);
		System.out.println(msg);
		System.out.println(border);
	}

	public void erro(String erro) {
		String border = buildBorder(erro);
		System.err.println(border);
		System.err.println(erro);
		System.err.println(border);
	}

	private String buildBorder(String msg) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < msg.length(); i++) {
			sb.append('=');
		}

		return sb.toString();
	}
}
