package com.rsn.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@EntityScan("com.rsn.*")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Lob
	@Column(name = "imagedata", length = 1000)
	private byte[] imageData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Image(Integer id, byte[] imageData) {
		super();
		this.id = id;
		this.imageData = imageData;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", imageData=" + Arrays.toString(imageData) + ", getId()=" + getId()
				+ ", getImageData()=" + Arrays.toString(getImageData()) + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
