package com.pank.ett.persistence.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pank.ett.persistence.dao.PrDAO;
import com.pank.ett.persistence.model.Pr;

@Repository("prDao")
public class PrDaoImpl extends CRUDAdapter<Pr, Integer> implements PrDAO, Serializable{
	private static final long serialVersionUID = 1L;
    private static final String FIELD_ID = "id";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Criteria createCriteria() {
	return sessionFactory.getCurrentSession().createCriteria(Pr.class);
    }

    @Override
    public String getIdField() {
	return FIELD_ID;
    }
    
    @Override
    public List<Pr> getList() {
	Query resultQuery = sessionFactory.getCurrentSession().getNamedQuery("Pr.findAll");
	return (List<Pr>) resultQuery.list();
    }
    
    @Override
    public Pr deletePr(Pr getPr) {
    	sessionFactory.getCurrentSession().delete(getPr);
    	return null;
    }
	
    @Override
    public Pr createPr(Pr getPr) {
    	sessionFactory.getCurrentSession().persist(getPr);
    	return getPr;
    }
    
    @Override
    public Pr updatePr(Pr getPr) {
    	sessionFactory.getCurrentSession().update(getPr);
    	return getPr;
    }
}
