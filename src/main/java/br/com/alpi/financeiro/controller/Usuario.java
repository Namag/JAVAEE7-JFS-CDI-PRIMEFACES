package br.com.alpi.financeiro.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@SessionScoped
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
@NotNull
@Size(min = 5, max = 20)
private String nome;
@NotNull

private Date dataLogin;
public boolean isLogado() {
return nome != null;
}
public String getNome() {
return nome;
}
public void setNome(String nome) {
this.nome = nome;
}
public Date getDataLogin() {
return dataLogin;
}
public void setDataLogin(Date dataLogin) {
this.dataLogin = dataLogin;
}
}
