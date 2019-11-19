package br.com.fluxo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fluxo.dao.BaseDao;
import br.com.fluxo.domain.FluxoDTO;

public class FileUtils {
	public static void main(String[] args) throws Exception {
		gerarRelatorio("/home/henrique/Documentos/text", 7);
	}

	public static boolean gerarRelatorio(String destino, int dias) throws Exception {
		BaseDao dao = new BaseDao();
		Date agora = DateUtils.dataAtual();
		List<Date> datas = new ArrayList<>();
		List<FluxoDTO> fluxos;
		dias = -dias;
		for (int i = -1; i >= dias; i--) {
			datas.add(DateUtils.addDiaData(agora, i));
		}
		String cabecalho = "Data;Quantidade;";
		escreverArquivoConcatenando(destino + ".csv", cabecalho);
		for (int i = 0; i < datas.size(); i++) {
			if (i == 0) {
				fluxos = dao.getFluxo(datas.get(0), agora);
				System.out.println(fluxos);
				montaArquivo(destino, fluxos);
				continue;
			}
			fluxos = dao.getFluxo(datas.get(i), datas.get(i - 1));
			montaArquivo(destino, fluxos);
			System.out.println(fluxos);
		}
		return false;
	}

	public static void montaArquivo(String destino, List<FluxoDTO> fluxos) {
		try {
			for (FluxoDTO fluxo : fluxos) {
				String linha = fluxo.getData() + ";" + fluxo.getQuantidade();
				escreverArquivoConcatenando(destino + ".csv", linha);
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

	public static void escreverArquivo(String nmArquivo, String conteudo) throws Exception {
		FileWriter escritor = new FileWriter(nmArquivo);
		BufferedWriter bf = new BufferedWriter(escritor);
		bf.append(conteudo);
		bf.close();
	}

	public static void escreverArquivoConcatenando(String nmArquivo, String conteudo) throws Exception {
		FileWriter escritor = new FileWriter(nmArquivo, true);
		BufferedWriter bf = new BufferedWriter(escritor);
		bf.append(conteudo + "\n");
		bf.close();
	}
}
