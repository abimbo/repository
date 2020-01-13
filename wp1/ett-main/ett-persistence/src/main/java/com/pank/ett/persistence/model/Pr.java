package com.pank.ett.persistence.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



/**
 * The persistent class for the pr database table.
 * 
 */
@Entity
@NamedQuery(name="Pr.findAll", query="SELECT p FROM Pr p")
public class Pr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String data;
	
	@Lob
	@Basic (fetch = FetchType.LAZY)
	private byte[] image;
	
	@Transient
	private DefaultStreamedContent sImage;

	@Transient
	private byte[] bImage;

	public Pr() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public DefaultStreamedContent getsImage() {
		return sImage;
	}

	public void setsImage(DefaultStreamedContent sImage) {
		this.sImage = sImage;
	}

	public byte[] getbImage() {
		return bImage;
	}

	public void setbImage(byte[] bImage) {
		this.bImage = bImage;
	}

}