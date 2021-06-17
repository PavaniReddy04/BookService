package com.epam.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.epam.book.dto.BookDTO;
import com.epam.book.entity.Book;
import com.epam.book.exception.NoSuchBookException;
import com.epam.book.exception.ZeroBooksException;
import com.epam.book.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public BookDTO get(int id) throws NoSuchBookException {
		log.info("In method get, for getting book by ID");
		log.debug("get method takes Book ID as parameter");
		return mapper.map(getBookIfExsists(id), BookDTO.class);
	}

	@Override
	public List<BookDTO> getAll(Pageable pageable) throws ZeroBooksException {
		log.info("In method getAll, for getting all the books");
		log.debug("getAll method takes Pageable interface as parameter");
		Page<Book> books = bookRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
		if (books.getNumberOfElements() == 0) {
			throw new ZeroBooksException("No books available to display on this page");
		}
		log.debug("getAll method returning the list of BookDTO's");
		return books.stream().map(user -> mapper.map(user, BookDTO.class)).collect(Collectors.toList());
	}

	@Override
	public BookDTO add(BookDTO bookDTO) {
		log.info("In method add, for adding a book");
		log.debug("add method takes BookDTO as parameter");
		Book book = mapper.map(bookDTO, Book.class);
		bookRepository.save(book);
		log.debug("add method returning the BookDTO of added book");
		return mapper.map(book, BookDTO.class);
	}

	@Override
	public void delete(int id) throws NoSuchBookException {
		log.info("In method delete, for deleting a book by ID");
		log.debug("delete method takes Book ID as parameter");
		checkIfBookExsists(id);
		bookRepository.deleteById(id);
		log.info("Book is deleted");
	}

	private void checkIfBookExsists(int id) throws NoSuchBookException {
		log.debug("In method checkIfBookExsists that takes Book ID as parameter");
		if (!bookRepository.existsById(id)) {
			throw new NoSuchBookException("Book not found. Please enter a valid Book ID");
		}
	}

	@Override
	public BookDTO update(BookDTO bookDTO) throws NoSuchBookException {
		log.info("In method update, for updating a book details");
		log.debug("update method takes BookDTO as parameter and gets the book by ID");
		Book book = getBookIfExsists(bookDTO.getId());
		updateChangedFeilds(book, bookDTO);
		log.info("Saving the new book details");
		bookRepository.save(book);
		log.debug("update method returning the BookDTO of updated book");
		return mapper.map(book, BookDTO.class);
	}

	private Book getBookIfExsists(int id) throws NoSuchBookException {
		log.debug("In method getBookIfExsists that takes Book ID as parameter");
		return bookRepository.findById(id)
				.orElseThrow(() -> new NoSuchBookException("Book not found. Please enter a valid book ID."));
	}

	private void updateChangedFeilds(Book book, BookDTO bookDTO) {
		log.debug("In method updateChangedFeilds that takes Book and corresponding DTO as parameters");
		Object temp;
		temp = bookDTO.getName();
		if (temp != null && (!book.getName().equals(temp))) {
			book.setName((String) temp);
		}

		temp = bookDTO.getAuthor();
		if (temp != null && (!book.getAuthor().equals(temp))) {
			book.setAuthor((String) temp);
		}

		temp = bookDTO.getDescription();
		if (temp != null && (!book.getDescription().equals(temp))) {
			book.setDescription((String) temp);
		}

		temp = bookDTO.getCategory();
		if (temp != null && (!book.getCategory().equals(temp))) {
			book.setCategory((String) temp);
		}

		temp = bookDTO.getPrice();
		if (temp != null && (book.getPrice() != (float) temp)) {
			book.setPrice((float) temp);
		}
		log.debug("Updated the changed feilds");
	}
}
