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
		
		//Inicia o parce do arquivo, caso o retorno for true a impress�o � iniciada
		if (log.leitorLog(caminho)) {
			
			//solicita a impress�o do da url informado quais posi��es devem ser impressas
			log.impressaoURL(3);

			//solicita a impress�o do dos c�digos
			log.impressaoCode();
		}

	}
}
