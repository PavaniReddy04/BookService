package com.epam.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
