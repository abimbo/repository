package com.pank.ett.persistence.dao;

import java.util.List;

import com.pank.ett.persistence.model.Pr;

public interface PrDAO extends CRUD<Pr, Integer>, Listable<Pr>{
	
	public List<Pr> getList();
	public Pr deletePr(Pr getPr);
	public Pr createPr(Pr getPr);
	public Pr updatePr(Pr getPr);
	public byte[] getImageFromPr(int id);
	
}
