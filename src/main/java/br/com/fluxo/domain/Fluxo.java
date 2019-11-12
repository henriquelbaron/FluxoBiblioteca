package br.com.fluxo.domain;

import java.util.Date;

public class Fluxo {

	private Long id;
	private Date timeStamp;
	private Integer pessoasDentro;

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

	public Integer getPessoasDentro() {
		return pessoasDentro;
	}

	public void setPessoasDentro(Integer pessoasDentro) {
		this.pessoasDentro = pessoasDentro;
	}

}
