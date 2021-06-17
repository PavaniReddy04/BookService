package com.epam.book.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.epam.book.dto.BookDTO;
import com.epam.book.exception.NoSuchBookException;
import com.epam.book.exception.ZeroBooksException;

public interface BookService {
	
	BookDTO get(int id) throws NoSuchBookException;

	List<BookDTO> getAll(Pageable pageable) throws ZeroBooksException;

	BookDTO add(BookDTO bookDTO);

	void delete(int id) throws NoSuchBookException;

	BookDTO update(BookDTO bookDTO) throws NoSuchBookException;

}
