package br.com.alpi.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

//import br.com.alpi.financeiro.model.Lancamento;
import br.com.alpi.financeiro.model.Pessoa;

public class Pessoas implements Serializable {
private static final long serialVersionUID = 1L;

private EntityManager manager;

@Inject
public Pessoas(EntityManager manager) {
this.manager = manager;
}
public Pessoa porId(Long id) {
return manager.find(Pessoa.class, id);
}
public List<Pessoa> todas() {
TypedQuery<Pessoa> query = manager.createQuery("from Pessoa", Pessoa.class);
return query.getResultList();

}
/*public void adicionar(Pessoa pessoa) {
	/*EntityTransaction trx = this.manager.getTransaction();
	trx.begin();
	this.manager.persist(pessoa);
	trx.commit();
this.manager.persist(pessoa);
}*/

}