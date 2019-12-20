package Beans;


import java.sql.Date;

public class Coupon {
	
	// Fields
	private int id, companyID, amount;
	private Category category;
	private String title, description, image;
	private Date startDate, endDate;
	private double price;
	
	// Constructors
	public Coupon() {
		super();
	}
	// to purchase
	public Coupon(int id) {
		super();
		this.id = id;
	}
	//  to update
	public Coupon(int id, int companyID, int amount, Category category, String title, String description, String image,
			Date  startDate, Date  endDate, double price) {
		super();
		this.id = id;
		this.companyID = companyID;
		this.amount = amount;
		this.category = category;
		this.title = title;
		this.description = description;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}
	//  to create
	public Coupon(int companyID, int amount, Category category, String title, String description, String image,
			Date startDate, Date  endDate, double price) {
		super();
		this.companyID = companyID;
		this.amount = amount;
		this.category = category;
		this.title = title;
		this.description = description;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}
	
	// Getters/Setter
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date  startDate) {
		this.startDate = startDate;
	}
	public Date  getEndDate() {
		return endDate;
	}
	public void setEndDate(Date  endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public Category getCategory() {
		return category;
	}
	
	// toString
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyID + ", amount=" + amount + ", category=" + category
				+ ", title=" + title + ", description=" + description + ", image=" + image + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", price=" + price + "]";
	}
}
