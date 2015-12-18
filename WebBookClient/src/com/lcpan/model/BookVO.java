package com.lcpan.model;

public class BookVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String publisher;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
