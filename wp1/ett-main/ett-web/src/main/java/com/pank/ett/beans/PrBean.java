package com.pank.ett.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;


import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.internal.BinaryStreamImpl;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.pank.ett.persistence.model.Pr;
import com.pank.ett.service.PrService;


@ManagedBean(name = "prBean")
@SessionScoped

public class PrBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PrBean.class);
	
	private List<Pr> prList;
	private Pr selectedPr;
	private Pr newPr = new Pr();
	private HashMap<Integer, byte[]> hashImage = new HashMap<Integer, byte[]>();

	@ManagedProperty("#{prService}")
    private PrService prService;
	private BinaryStreamImpl bs;
	
	@PostConstruct
	public void init(){
		prList = prService.listPr();
		for (Pr pr :prList){
			hashImage.put(pr.getId(), pr.getImage());
		}
		selectedPr = prList.get(0);
	}
	
	public StreamedContent getImageFromDB() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			Map<String,String> params = context.getExternalContext().getRequestParameterMap();
			Integer id = Integer.parseInt(params.get("id"));
//			return new DefaultStreamedContent(new ByteArrayInputStream(selectedPr.getImage()),
//			"image/jpg");
			return new DefaultStreamedContent(new ByteArrayInputStream(hashImage.get(id)),
			"image/jpg");
		}
	}

//	public StreamedContent getImageFromDB() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
//		Integer id = Integer.parseInt(params.get("id"));
//		return new DefaultStreamedContent(new ByteArrayInputStream(hashImage.get(5)),"image/jpg");
//	}

	
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
