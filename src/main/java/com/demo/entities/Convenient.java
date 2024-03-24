package com.demo.entities;

import java.util.Date;

public class Convenient {
	private Integer id;
	private String bed;
	private String quality;
	private Boolean balcony;
	private String orther;
	
	private Integer roomId;
	private Integer kfrId;
	private Integer quantityDate;
	private Integer price;
	
	private Date created;
	private Date updated;

	public Integer getKfrId() {
		return kfrId;
	}
	public void setKfrId(Integer kfrId) {
		this.kfrId = kfrId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public Boolean getBalcony() {
		return balcony;
	}
	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}
	public String getOrther() {
		return orther;
	}
	public void setOrther(String orther) {
		this.orther = orther;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getQuantityDate() {
		return quantityDate;
	}
	public void setQuantityDate(Integer quantityDate) {
		this.quantityDate = quantityDate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
