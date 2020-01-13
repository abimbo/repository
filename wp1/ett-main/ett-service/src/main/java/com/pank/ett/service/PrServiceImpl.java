package com.pank.ett.service;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pank.ett.persistence.dao.PrDAO;
import com.pank.ett.persistence.model.Pr;



@Service("prService")
public class PrServiceImpl implements PrService, Serializable {	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PrServiceImpl.class);
//    private ImageService service;


	@Autowired
	private PrDAO prDao;

	@Transactional
	public List<Pr> listPr() {
		logger.debug("Listing pr");
		List<Pr> result = new ArrayList<Pr>();
		result = prDao.getList();
//		for (Pr pr :result){
//			pr.setsImage(new DefaultStreamedContent(new ByteArrayInputStream((byte[]) pr.getImage()), "image/jpg"));
//		}
		return result;
	}
	
	@Transactional
	public Pr updatePr(Pr getPr){
		logger.debug("UpdatePr");
		Pr pr = prDao.updatePr(getPr);
//		Pr pr = prDao.update(getPr);
		return pr;
	}
	
	@Transactional
	public Pr deletePr(Pr getPr){
		logger.debug("DeletePr");
		getPr = prDao.deletePr(getPr);
		return getPr;
	}
	
	@Transactional
	public Pr createPr(Pr getPr){
		logger.debug("CreatePr");
		getPr = prDao.createPr(getPr);
		return getPr;
	}
	
	@Transactional
	public byte[] getImageFromPr(int id){
		return prDao.getImageFromPr(id);
	}
	
/*
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
            Image image = imageService.find(Long.valueOf(imageId));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getBytes()));
        }
*/
}
