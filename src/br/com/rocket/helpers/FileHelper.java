package br.com.rocket.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

	private static final String FILENAME = "pessoas.txt";
	private static final String CHARSETDEFAULT = "UTF-8";

	public String readToEnd(String pathName) throws IOException {
		try (InputStream stream = new FileInputStream(pathName)) {
			byte[] aux = new byte[stream.available()];
			stream.read(aux);
			return new String(aux);
		}
	}

	public List<String> readLines() throws IOException {
		List<String> linhas = new ArrayList<>();
		createIfDoesntExists();

		try (InputStream is = new FileInputStream(FILENAME)) {
			try (InputStreamReader isr = new InputStreamReader(is, CHARSETDEFAULT)) {
				try (BufferedReader br = new BufferedReader(isr)) {
					String linha;

					while ((linha = br.readLine()) != null) {
						linhas.add(linha);
					}
				}
			}
		}

		return linhas;
	}

	public void write(String texto) throws IOException {
		try (OutputStream os = new FileOutputStream(FILENAME)) {
			try (OutputStreamWriter osw = new OutputStreamWriter(os, CHARSETDEFAULT)) {
				try (BufferedWriter bw = new BufferedWriter(osw)) {
					bw.write(texto);
				}
			}
		}
	}

	public void append(String texto) throws IOException {
		try (OutputStream os = new FileOutputStream(FILENAME, true)) {
			try (OutputStreamWriter osw = new OutputStreamWriter(os, CHARSETDEFAULT)) {
				try (BufferedWriter bw = new BufferedWriter(osw)) {
					bw.write(texto);
					bw.write(System.lineSeparator());
				}
			}
		}
	}

	private boolean createIfDoesntExists() throws IOException {
		File file = new File(FILENAME);

		if (file.exists()) {
			return true;
		}

		return file.createNewFile();
	}
}
