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

import br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence;
import br.iff.pooa20132.tabaplanner.persistence.entity.Projeto;

/**
 * @author lglmoura
 * 
 */

@ManagedBean(name = "projetoPrime")
@SessionScoped 
public class ProjetoPWebController {

	@EJB(lookup = "java:app/TabaPlannerEJB/ProjetoPersistence!br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence")
	private ProjetoPersistence jprojeto;

	private Projeto projeto;
	private Projeto projetoSelecionado = new Projeto();
	

	public Projeto getProjeto() {
		if (projeto == null) {
			projeto = new Projeto();
		}
		return projeto;
	}

	public List<Projeto> getAllProjeto() {
		return jprojeto.findAll();
	}

	public void salva() {

		if (jprojeto.find(projeto.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto Ja Cadastrado"));

		} else {
			jprojeto.insert(projeto);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("cadastro.hide()"); ;
	}

	
	
	public void delete() {

		if (jprojeto.find(projetoSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto não Existe"));

		} else {
			jprojeto.delete(projetoSelecionado.getUid());

			
		}
		RequestContext.getCurrentInstance().execute("deleta.hide()"); 
	}

	public Projeto getProjetoSelecionado() {
		
		
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
		
	}

	
	public void update() {
		
		if (jprojeto.find(projetoSelecionado.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto não Existe"));

		} else {
			
			jprojeto.update(projetoSelecionado);
			

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		RequestContext.getCurrentInstance().execute("altera.hide()"); ;
	}

	

	public ProjetoPWebController() {

	}

	
}
