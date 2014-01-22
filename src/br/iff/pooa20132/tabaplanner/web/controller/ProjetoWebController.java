/**
 * 
 */
package br.iff.pooa20132.tabaplanner.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence;
import br.iff.pooa20132.tabaplanner.persistence.entity.Projeto;

/**
 * @author lglmoura
 * 
 */

@ManagedBean(name = "projetoweb")
@RequestScoped
public class ProjetoWebController {

	@EJB(lookup = "java:app/TabaPlannerEJB/ProjetoPersistence!br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence")
	private ProjetoPersistence jprojeto;

	private Projeto projeto;

	public Projeto getprojeto() {
		if (projeto == null) {
			projeto = new Projeto();
		}
		return projeto;
	}

	public List<Projeto> getAllprojeto() {
		return jprojeto.findAll();
	}

	public String salva() {

		if (jprojeto.find(projeto.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto Ja Cadastrado"));

		} else {
			jprojeto.insert(projeto);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		return "ok";
	}

	public String delete() {

		if (jprojeto.find(projeto.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto não Existe"));

		} else {
			jprojeto.delete(projeto.getUid());

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto Excluido!"));
		}
		return "ok";
	}

	public String update() {

		if (jprojeto.find(projeto.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto não Existe"));

		} else {
			jprojeto.update(projeto);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		return "ok";
	}

	public String busca() {

		projeto = jprojeto.find(projeto.getUid());
		if (projeto == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("projeto não Existe"));

		} else {
			jprojeto.update(projeto);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage(""));
		}
		return "ok";
	}

	public ProjetoWebController() {

	}

}
