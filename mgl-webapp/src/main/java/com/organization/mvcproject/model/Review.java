package com.organization.mvcproject.model;

public class Review {
	
	/**
	 *  MAY NEED TO BE UPDATED IN THE JSP CHECK LATER
	 * one member declared here is not object oriented refactor it
	 * DONE
	 */
	 
	private String body;
	private String author;
	private Integer rating;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReviewBody() {
		return body;
	}

	public void setReviewBody(String body) {
		this.body = body;
	}
}
