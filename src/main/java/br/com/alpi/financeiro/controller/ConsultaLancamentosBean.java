package br.com.alpi.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;

import br.com.alpi.financeiro.model.Lancamento;
import br.com.alpi.financeiro.repository.Lancamentos;
import br.com.alpi.financeiro.service.CadastroLancamentos;
//import br.com.alpi.financeiro.util.JpaUtil;
import br.com.alpi.financeiro.service.NegocioException;
//import br.com.alpi.financeiro.controller.CadastroLancamentos;

@Named("consultaLancamentosBean")
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;
	
	private List<Lancamento> lancamentos;
	
	@Inject
	private CadastroLancamentos cadastro;

	//private Lancamento lancamento;
	
	private Lancamento lancamentoSelecionado;
	



	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;

	}
 //Lancamento lancamento;
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			this.cadastro.excluir(this.lancamentoSelecionado);
	//this.lancamento= new Lancamento();
		   	this.consultar();
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
}
/*
 * public class ConsultaLancamentosBean implements Serializable { private static
 * final long serialVersionUID = 1L;
 * 
 * private List<Lancamento> lancamentos; public void consultar() { EntityManager
 * manager = JpaUtil.getEntityManager(); TypedQuery<Lancamento> query =
 * manager.createQuery("from Lancamento", Lancamento.class); this.lancamentos =
 * query.getResultList(); manager.close(); } public List<Lancamento>
 * getLancamentos() { return lancamentos; }
 * 
 * /*private List<Lancamento> lancamentos; public void consultar() {
 * EntityManager manager = JpaUtil.getEntityManager(); Lancamentos lancamentos =
 * new Lancamentos(manager); this.lancamentos = lancamentos.todos();
 * manager.close(); }
 */
