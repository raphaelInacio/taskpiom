package br.com.moip.leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.moip.webhooks.WebHooksCode;
import br.com.moip.webhooks.WebHooksURL;

public class LeitorLog {
 
	public static final String REQUEST_TO = "request_to=\"";
	public static final String RESPONSE_HEADERS = "response_headers";
	public static final String RESPONSE_STATUS = "response_status=\"";
	
	private List<WebHooksURL> listWebHooksURL = new ArrayList<WebHooksURL>();
	private List<WebHooksURL> listWebHooksAuxURL = new ArrayList<WebHooksURL>();
	
	private List<WebHooksCode> listWebHooksCode = new ArrayList<WebHooksCode>();
	private List<WebHooksCode> listWebHooksAuxCode = new ArrayList<WebHooksCode>();
	private BufferedReader br;
	
	public void impressaoURL(int posicao){
		int cont = 1;
		for (WebHooksURL hooksAux : this.listWebHooksAuxURL) {
			System.out.println(hooksAux.getUrl() +" - "+ hooksAux.getQuantdade());
			if(cont >= posicao)break;
			cont++;
		}
		
	}
	
	public void impressaoCode(){
		for (WebHooksCode hooksAux : listWebHooksAuxCode) {
			System.out.println(hooksAux.getCode() +" - "+ hooksAux.getQuantdade());
		}
	}
	
	public void carregarQuantidadeCode(Set<WebHooksCode> ocorrenciasWebHooksCode){
		listWebHooksAuxCode.addAll(ocorrenciasWebHooksCode);
		
		//Iteração sobre cada item da lisda de códigos para calcular a quantidade de ocorrencias
		for (WebHooksCode hooksAuxCode : listWebHooksAuxCode) {
			for (WebHooksCode webHooksCode : listWebHooksCode) {
				if(hooksAuxCode.getCode().equals(webHooksCode.getCode())){
					hooksAuxCode.setQuantdade(hooksAuxCode.getQuantdade()+1);
				}
			}
		}
		
		//Ordenando a lista em ordem decrescente a maior quantidade no primeir lugar
		Collections.sort(this.listWebHooksAuxCode);
	}
	
	
	public void carregarQuantidadeURL(Set<WebHooksURL> ocorrenciasWebHooksURL){
		listWebHooksAuxURL.addAll(ocorrenciasWebHooksURL);
		
		//Iteração sobre cada item da lisda de Urls para calcular a quantidade de ocorrencias
		for (WebHooksURL hooksAuxURL : listWebHooksAuxURL) {
			for (WebHooksURL webHooksURL : listWebHooksURL) {
				if (hooksAuxURL.getUrl().equals(webHooksURL.getUrl())) {
					hooksAuxURL.setQuantdade(hooksAuxURL.getQuantdade() + 1);
				}
			}
		}
		
		//Ordenando a lista em ordem decrescente a maior quantidade no primeir lugar
		Collections.sort(this.listWebHooksAuxURL);
	}
	 
	
	public boolean leitorLog(String caminho){
		try {

		FileInputStream stream = new FileInputStream(caminho);
		InputStreamReader reader = new InputStreamReader(stream);
		br = new BufferedReader(reader);
		String linha;
		Set<WebHooksURL> ocorrenciasWebHooksURL =  new HashSet<WebHooksURL>();
		Set<WebHooksCode> ocorrenciasWebHooksCode =  new HashSet<WebHooksCode>();
		
			
			linha = br.readLine();
			
			while (linha != null) {
				
				WebHooksURL webHooksURL = new WebHooksURL();
				WebHooksCode webHooksCode = new WebHooksCode();
				
				// Parse sobre cada linha do arquivo buscando o conteudo de request_to=
				webHooksURL.setUrl(linha.substring(linha.indexOf(REQUEST_TO) + REQUEST_TO.length(), linha.indexOf(RESPONSE_HEADERS) - 2));
				// Parse sobre cada linha do arquivo buscando o conteudo de response_status=
				webHooksCode.setCode(linha.substring( linha.indexOf(RESPONSE_STATUS) + RESPONSE_STATUS.length(), linha.indexOf(RESPONSE_STATUS) + RESPONSE_STATUS.length() + 3));

				listWebHooksURL.add(webHooksURL);
				listWebHooksCode.add(webHooksCode);
				
				//Separamos uma ocorrencia de cada Code URL com a ajuda da interface Set
				ocorrenciasWebHooksURL.add(webHooksURL);
				//Separamos uma ocorrencia de cada Code com a ajuda da interface Set
				ocorrenciasWebHooksCode.add(webHooksCode);
				
				linha = br.readLine();
			}
			
			carregarQuantidadeCode(ocorrenciasWebHooksCode);
			carregarQuantidadeURL(ocorrenciasWebHooksURL);

			return true;
			
		} catch (FileNotFoundException e) {
			System.err.println("Caminho de arquivo inválido:\n, " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro de leitura de arquivo:\n, " + e.getMessage());
		}
		return false;
	}

}
