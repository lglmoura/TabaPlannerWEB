/**
 * 
 */
package br.iff.pooa20132.tabaplanner.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.iff.pooa20132.tabaplanner.persistence.controller.IteracaoPersistence;
import br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence;
import br.iff.pooa20132.tabaplanner.persistence.entity.Iteracao;
import br.iff.pooa20132.tabaplanner.persistence.entity.Projeto;

/**
 * @author lglmoura
 * 
 */

@ManagedBean(name = "iteracaoPrime")
@SessionScoped
public class IteracaoPrimeWebController {

	@EJB(lookup = "java:app/TabaPlannerEJB/ProjetoPersistence!br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence")
	private ProjetoPersistence jprojeto;

	@EJB(lookup = "java:app/TabaPlannerEJB/IteracaoPersistence!br.iff.pooa20132.tabaplanner.persistence.controller.IteracaoPersistence")
	private IteracaoPersistence jiteracao;

	private Iteracao iteracao;
	private Iteracao iteracaoSelecionado;

	public Iteracao getIteracaoSelecionado() {
		if (iteracaoSelecionado == null)
			iteracaoSelecionado = new Iteracao();
		return iteracaoSelecionado;
	}

	public void setIteracaoSelecionado(Iteracao iteracaoSelecionado) {
		this.iteracaoSelecionado = iteracaoSelecionado;
	}

	public Iteracao getIteracao() {
		if (iteracao == null) {
			iteracao = new Iteracao();
		}
		return iteracao;
	}

	public List<Iteracao> getAllIteracao() {
		return jiteracao.findAll();
	}

	public List<Projeto> getTodosProjetos() {

		return jprojeto.findAll();
	}

	public void salva() {

		if (jiteracao.find(iteracao.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao Ja Cadastrado"));

		} else {
			jiteracao.insert(iteracao);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("cadastro.hide()");
	}

	public void delete() {

		if (jiteracao.find(iteracaoSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao não Existe"));

		} else {
			jiteracao.delete(iteracaoSelecionado.getUid());

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao Excluido!"));
		}
		RequestContext.getCurrentInstance().execute("deleta.hide()");
	}

	public void update() {

		if (jiteracao.find(iteracaoSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao não Existe"));

		} else {
			jiteracao.update(iteracaoSelecionado);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()");
	
	}

	public IteracaoPrimeWebController() {

	}

}
