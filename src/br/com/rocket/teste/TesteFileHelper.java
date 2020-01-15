package br.com.rocket.teste;

import java.util.ArrayList;

import br.com.rocket.entities.Pessoa;
import br.com.rocket.helpers.FileHelper;

public class TesteFileHelper {

	public static void main(String[] args) {

		Pessoa p1 = new Pessoa();
		p1.setCodigo(123);
		p1.setNome("Júlia Andrade");
		p1.setIdade(15);
		p1.setEndereco("Avenidade Cascata");

		Pessoa p2 = new Pessoa();
		p2.setCodigo(456);
		p2.setNome("Gustavo Santos");
		p2.setIdade(34);
		p2.setEndereco("Machado Deodoro");

		ArrayList<Pessoa> list = new ArrayList();
		list.add(p1);
		list.add(p2);

		try {
			FileHelper helper = new FileHelper();
			// List<Pessoa> listaNova = helper.readObject();
			// System.out.println(p);
			// listaNova.forEach(pe -> System.out.println(pe));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
