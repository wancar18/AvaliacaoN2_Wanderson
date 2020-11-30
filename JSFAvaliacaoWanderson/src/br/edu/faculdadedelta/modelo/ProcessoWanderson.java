package br.edu.faculdadedelta.modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProcessoWanderson {

	private Long id;
	private String numeroProc;
	private String assunto;
	private String interessado;
	private Date dataAut;
	private Double valorProc;
	Double desconto = 0.00;
	
	public ProcessoWanderson() {
	}

	public ProcessoWanderson(Long id, String numeroProc, String assunto, String interessado, Date dataAut,
			Double valorProc) {
		this.id = id;
		this.numeroProc = numeroProc;
		this.assunto = assunto;
		this.interessado = interessado;
		this.dataAut = dataAut;
		this.valorProc = valorProc;
		
	}
	
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	  String data = "01/01/2008"; 
	  LocalDate ld = LocalDate.parse(data, formatter);
	  Date dataDesc = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroProc() {
		return numeroProc;
	}

	public void setNumeroProc(String numeroProc) {
		this.numeroProc = numeroProc;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getInteressado() {
		return interessado;
	}

	public void setInteressado(String interessado) {
		this.interessado = interessado;
	}

	public Date getDataAut() {
		return dataAut;
	}

	public void setDataAut(Date dataAut) {
		this.dataAut = dataAut;
	}

	public Double getValorProc() {
		return valorProc;
	}

	public void setValorProc(Double valorProc) {
		this.valorProc = valorProc;
	}
	
	public double getDesconto() {
		
		if (valorProc>100 && dataAut.after(dataDesc)) {
			 desconto = (valorProc * 0.02);
			return desconto;
		}else {
		
		return desconto;
	
		}
	}
	public double getValorTotal() {
		return valorProc - desconto;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessoWanderson other = (ProcessoWanderson) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
