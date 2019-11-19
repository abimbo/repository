package com.pank.ett.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.pank.ett.persistence.model.Pr;
import com.pank.ett.service.PrService;


@ManagedBean(name = "prBean")
@ViewScoped

public class PrBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PrBean.class);
	
	private List<Pr> prList;
	private Pr selectedPr;
	private Pr newPr = new Pr();

	@ManagedProperty("#{prService}")
    private PrService prService;
	
	@PostConstruct
	public void init(){
		prList = prService.listPr();
		selectedPr = prList.get(0);
	}
	
	public void create(){
		logger.debug("Create");
		newPr = prService.createPr(newPr);
		prList = prService.listPr();
		selectedPr = prList.get(prList.size()-1);
	}

	public void update(){
		logger.debug("Update");
		selectedPr = prService.updatePr(selectedPr);
		prList = prService.listPr();
	}

	public void delete(){
		logger.debug("Delete");
		selectedPr = prService.deletePr(selectedPr);
		prList = prService.listPr();
		selectedPr = prList.get(0);
	}

	public List<Pr> getPrList() {
		return prList;
	}

	public void setPrList(List<Pr> prList) {
		this.prList = prList;
	}

	public Pr getSelectedPr() {
		return selectedPr;
	}

	public void setSelectedPr(Pr selectedPr) {
		this.selectedPr = selectedPr;
	}

	public Pr getNewPr() {
		return newPr;
	}

	public void setNewPr(Pr newPr) {
		this.newPr = newPr;
	}

	public PrService getPrService() {
		return prService;
	}

	public void setPrService(PrService prService) {
		this.prService = prService;
	}
	
	public void onPrSelected(SelectEvent event){
//		selectedPr = event.getObject().getClass();
	}

}
