package com.demo.entities;
// Generated Mar 12, 2024, 5:35:10 PM by Hibernate Tools 4.3.6.Final

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

/**
 * Kindofroom generated by hbm2java
 */
@Entity
@Table(name = "kindofroom", catalog = "booking_hotel")
public class Kindofroom implements java.io.Serializable {

	private Integer id;
	private Room room;
	private String bed;
	private String quality;
	private Boolean balcony;
	private String orther;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date created;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date updated;

	public Kindofroom() {
	}

	public Kindofroom(Room room) {
		this.room = room;
	}

	public Kindofroom(Room room, String bed, String quality, Boolean balcony, String orther, Date created,
			Date updated) {
		this.room = room;
		this.bed = bed;
		this.quality = quality;
		this.balcony = balcony;
		this.orther = orther;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "bed", length = 250)
	public String getBed() {
		return this.bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	@Column(name = "quality", length = 250)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "balcony")
	public Boolean getBalcony() {
		return this.balcony;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}

	@Column(name = "orther", length = 250)
	public String getOrther() {
		return this.orther;
	}

	public void setOrther(String orther) {
		this.orther = orther;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created", length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updated", length = 10)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
