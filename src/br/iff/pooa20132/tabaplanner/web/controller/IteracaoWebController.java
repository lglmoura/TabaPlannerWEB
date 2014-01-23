/**
 * 
 */
package br.iff.pooa20132.tabaplanner.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.iff.pooa20132.tabaplanner.persistence.controller.IteracaoPersistence;
import br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence;
import br.iff.pooa20132.tabaplanner.persistence.entity.Iteracao;
import br.iff.pooa20132.tabaplanner.persistence.entity.Projeto;

/**
 * @author lglmoura
 * 
 */

@ManagedBean(name = "iteracaoweb")
@RequestScoped
public class IteracaoWebController {

	@EJB(lookup = "java:app/TabaPlannerEJB/ProjetoPersistence!br.iff.pooa20132.tabaplanner.persistence.controller.ProjetoPersistence")
	private ProjetoPersistence jprojeto;

	@EJB(lookup = "java:app/TabaPlannerEJB/IteracaoPersistence!br.iff.pooa20132.tabaplanner.persistence.controller.IteracaoPersistence")
	private IteracaoPersistence jiteracao;

	private Iteracao iteracao;

	public Iteracao getIteracao() {
		if (iteracao == null) {
			iteracao = new Iteracao();
		}
		return iteracao;
	}

	public List<Iteracao> getAlliteracao() {
		return jiteracao.findAll();
	}
	
	public List<Projeto> getTodosProjetos() {  
		  
        /*List<Projeto> listaProjetos = jprojeto.findAll();  
        //List<SelectItem> listaSelect = new ArrayList<SelectItem>();  
        List<Projeto> listaSelect = new ArrayList<Projeto>();
  
        for (Projeto e : listaProjetos) {  
            listaSelect.add(e);//new SelectItem(new String(e.getUid()), e.getNome()));  
        }  
  
        return listaSelect;*/
        return jprojeto.findAll();
    } 
	

	public String salva() {

		if (jiteracao.find(iteracao.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao Ja Cadastrado"));

		} else {
			jiteracao.insert(iteracao);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		return "ok";
	}

	public String delete() {

		if (jiteracao.find(iteracao.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao não Existe"));

		} else {
			jiteracao.delete(iteracao.getUid());

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao Excluido!"));
		}
		return "ok";
	}

	public String update() {

		if (jiteracao.find(iteracao.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao não Existe"));

		} else {
			jiteracao.update(iteracao);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		return "ok";
	}

	public String busca() {

		iteracao = jiteracao.find(iteracao.getUid());
		if (iteracao == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("iteracao não Existe"));

		} else {
			jiteracao.update(iteracao);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage(""));
		}
		return "ok";
	}

	public IteracaoWebController() {

	}

}
