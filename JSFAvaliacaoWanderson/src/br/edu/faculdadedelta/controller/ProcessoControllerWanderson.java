package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.ProcessoDaoWanderson;
import br.edu.faculdadedelta.modelo.ProcessoWanderson;

@ManagedBean
@SessionScoped

public class ProcessoControllerWanderson {

	private ProcessoWanderson proc = new ProcessoWanderson();
	private ProcessoDaoWanderson dao = new ProcessoDaoWanderson();

	private static final String PAGINA_CADASTRO = "cadastroProcesso.xhtml";
	private static final String RELACAO_VENDAS = "listaProcesso.xhtml";
	
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	  String data = "01/01/2000"; 
	  LocalDate ld = LocalDate.parse(data, formatter);
	  Date dataLimite = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

	public ProcessoWanderson getProc() {
		return proc;
	}

	public void setProc(ProcessoWanderson proc) {
		this.proc = proc;
	}

	public ProcessoDaoWanderson getDao() {
		return dao;
	}

	public void setDao(ProcessoDaoWanderson dao) {
		this.dao = dao;
	}

	public void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void limparCampos() {
		proc = new ProcessoWanderson();
	}

	public String salvar() {
		try {
			if(proc.getDataAut().before(new Date()) && proc.getDataAut().after(dataLimite)) {
			if(proc.getId()==null) {
					dao.incluir(proc);
						exibirMensagem("Inclusão realizada com Sucesso!");
						limparCampos();
				}else {
					dao.alterar(proc);
					exibirMensagem("Alteração realizada com Sucesso!");
				}
			}else {
				exibirMensagem("Data deve ser menor que a data Atual e maior que 01/01/2000!");
			}
		}catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return PAGINA_CADASTRO;
	}
	public List<ProcessoWanderson> getLista() {
		List<ProcessoWanderson> listaRetorno = new ArrayList<ProcessoWanderson>();
		try {
			listaRetorno = ProcessoDaoWanderson.listar();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. " + "Tente novamente mais tarde." + e.getMessage());
			e.printStackTrace();
		}
		return listaRetorno;
	}

	public String editar() {
		return PAGINA_CADASTRO;
	}

	public String excluir() {
		try {
			dao.excluir(proc);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			exibirMensagem("Erro ao realizar a operação. Tente novamente mais tarde. " + e.getMessage());
			e.printStackTrace();
		}
		return RELACAO_VENDAS;
	}

}
