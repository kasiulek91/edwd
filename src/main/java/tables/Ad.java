package tables;

import java.util.Date;

public class Ad {
	private int id;
	private String user;
	private Date createdAt;
	private String title;
	private String description;
	private int category;
	private int categoryLi1;
	private int categoryLi2;
	private int categoryLi3;
	private Boolean privateAd; // true - private, false-buisenes
	private int region;
	private int city;
	private String country;
	private String ip;
	private Boolean offerAd; // true - offer, false-seek
	private int noumberOfPhotos;
	private String attributes;
	private Boolean human; // true - human, false - computer
	private String statusDetails;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return user;
	}

	public void setUserId(String user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCategoryLi1() {
		return categoryLi1;
	}

	public void setCategoryLi1(int categoryLi1) {
		this.categoryLi1 = categoryLi1;
	}

	public int getCategoryLi2() {
		return categoryLi2;
	}

	public void setCategoryLi2(int categoryLi2) {
		this.categoryLi2 = categoryLi2;
	}

	public int getCategoryLi3() {
		return categoryLi3;
	}

	public void setCategoryLi3(int categoryLi3) {
		this.categoryLi3 = categoryLi3;
	}

	public Boolean getPrivateAd() {
		return privateAd;
	}

	public void setPrivateAd(Boolean privateAd) {
		this.privateAd = privateAd;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getOfferAd() {
		return offerAd;
	}

	public void setOfferAd(Boolean offerAd) {
		this.offerAd = offerAd;
	}

	public int getNoumberOfPhotos() {
		return noumberOfPhotos;
	}

	public void setNoumberOfPhotos(int noumberOfPhotos) {
		this.noumberOfPhotos = noumberOfPhotos;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public Boolean getHuman() {
		return human;
	}

	public void setHuman(Boolean human) {
		this.human = human;
	}

	public String getStatusDetails() {
		return statusDetails;
	}

	public void setStatusDetails(String statusDetails) {
		this.statusDetails = statusDetails;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
