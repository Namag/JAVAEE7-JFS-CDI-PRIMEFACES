package br.com.alpi.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
//import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;

import br.com.alpi.financeiro.model.Lancamento;
import br.com.alpi.financeiro.model.Pessoa;
import br.com.alpi.financeiro.model.TipoLancamento;
import br.com.alpi.financeiro.repository.Lancamentos;
//import br.com.alpi.financeiro.repository.Lancamentos;
import br.com.alpi.financeiro.repository.Pessoas;
import br.com.alpi.financeiro.service.CadastroLancamentos;
import br.com.alpi.financeiro.service.NegocioException;
//import br.com.alpi.financeiro.util.JpaUtil;

@Named("cadastroLancamentoBean")
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Lancamentos lancamentos;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
		if (this.lancamento == null) {
		this.lancamento = new Lancamento();
		}
		}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}
	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
		}
}
/*
 * public void descricaoModificada(ValueChangeEvent event) {
 * System.out.println("Valor antigo: " + event.getOldValue());
 * System.out.println("Novo valor: " + event.getNewValue());
 * FacesContext.getCurrentInstance().renderResponse(); }
 */

/*
 * private Lancamento lancamento = new Lancamento(); private List<Pessoa>
 * todasPessoas; public void prepararCadastro() { EntityManager manager =
 * JpaUtil.getEntityManager(); try { Pessoas pessoas = new Pessoas(manager);
 * this.todasPessoas = pessoas.todas(); } finally { manager.close(); } } public
 * void salvar() { EntityManager manager = JpaUtil.getEntityManager();
 * EntityTransaction trx = manager.getTransaction(); FacesContext context =
 * FacesContext.getCurrentInstance();
 * 
 * try { trx.begin(); CadastroLancamentos cadastro = new CadastroLancamentos(new
 * Lancamentos(manager)); cadastro.salvar(this.lancamento); this.lancamento =
 * new Lancamento(); context.addMessage(null, new
 * FacesMessage("Lançamento salvo com sucesso!")); trx.commit(); } catch
 * (NegocioException e) { trx.rollback(); FacesMessage mensagem = new
 * FacesMessage(e.getMessage());
 * mensagem.setSeverity(FacesMessage.SEVERITY_ERROR); context.addMessage(null,
 * mensagem); } finally { manager.close(); } }
 */
