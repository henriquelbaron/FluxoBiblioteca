package br.com.fluxo.domain;

import java.util.Date;

public class Fluxo {

	private Long id;
	private Date timeStamp;
	private boolean direcao; // true == entou false == saiu

	public boolean isDirecao() {
		return direcao;
	}

	public void setDirecao(boolean direcao) {
		this.direcao = direcao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
