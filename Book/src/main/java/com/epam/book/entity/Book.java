package com.epam.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@NotNull(message = "Name should not be null")
	@Size(min = 2, max = 50, message = "Book name should be between 2 and 50 characters")
	@Column(name = "bookName")
	String name;

	@NotNull(message = "Author name should not be null")
	@Size(min = 2, max = 50, message = "Author name should be between 2 and 50 characters")
	@Column(name = "authorName")
	String author;

	String category;

	String description;
	
	@NotNull(message = "Price of the book should not be null")
	@Positive(message = "Price of the book should be a positive number")
	float price;

}
