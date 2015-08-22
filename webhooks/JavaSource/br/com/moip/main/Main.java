package br.com.moip.main;

import java.util.Scanner;

import br.com.moip.leitor.LeitorLog;

public class Main {

	public static void main(String[] args){
		
		LeitorLog log = new LeitorLog();
		System.out.printf("Informe o caminho do arquivo:\n"); 
		
		// entrada de dados recebe o caminho do arquivo 
		@SuppressWarnings("resource")
		String caminho = new Scanner(System.in).next();
		
		//Inicia o parce do arquivo, caso o retorno for true a impressão é iniciada
		if (log.leitorLog(caminho)) {
			
			//solicita a impressão do da url informado quais posições devem ser impressas
			log.impressaoURL(3);

			//solicita a impressão do dos códigos
			log.impressaoCode();
		}

	}
}
