package br.com.fluxo.domain;

import java.util.Date;

public class FluxoDTO {

	private boolean direcao;
	private Date data;
	private Long quantidade;

	public FluxoDTO(boolean direcao, Long quantidade, Date data) {
		this.direcao = direcao;
		this.quantidade = quantidade;
		this.data = data;
	}

	public boolean isDirecao() {
		return direcao;
	}

	public void setDirecao(boolean direcao) {
		this.direcao = direcao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "FluxoDTO [direcao=" + direcao + ", data=" + data + ", quantidade=" + quantidade + "]";
	}

}
