package com.pank.ett.service;

import java.util.List;
import com.pank.ett.persistence.model.Pr;

public interface PrService {
	public List<Pr> listPr();
	public Pr createPr(Pr pr);
	public Pr updatePr(Pr pr);
	public Pr deletePr(Pr pr);
}
