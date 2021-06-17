package com.epam.book.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.book.dto.BookDTO;
import com.epam.book.exception.NoSuchBookException;
import com.epam.book.exception.ZeroBooksException;
import com.epam.book.service.BookService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("book-api/books")
@RestController
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	@Value("${message}")
	private String message;

	@ApiIgnore
	@GetMapping("/message")
	public String getMessage() {
		return message;
	}

	@ApiOperation(nickname = "Get book details", value = "Get all details of a book by giving book ID")
	@GetMapping("{id}")
	public ResponseEntity<BookDTO> get(@PathVariable int id) throws NoSuchBookException {
		log.info("In method get, for getting book by ID");
		log.debug("Takes Book ID as parameter which is path variable");
		return new ResponseEntity<>(bookService.get(id), HttpStatus.OK);
	}

	@ApiOperation(nickname = "Get all books", value = "Get all books present and details of each book")
	@GetMapping
	public ResponseEntity<List<BookDTO>> getAll(@PageableDefault(size = 5) Pageable pageable)
			throws ZeroBooksException {
		log.info("In method getAll, for getting all the books");
		log.debug("getAll method takes Pageable interface from request body");
		return new ResponseEntity<>(bookService.getAll(pageable), HttpStatus.OK);
	}

	@ApiOperation(nickname = "Add book", value = "Add a new book")
	@PostMapping
	public ResponseEntity<BookDTO> post(@Valid @RequestBody BookDTO bookDTO) {
		log.info("In method post, for adding a book");
		log.debug("post method takes BookDTO as parameter from request body");
		return new ResponseEntity<>(bookService.add(bookDTO), HttpStatus.CREATED);
	}

	@ApiOperation(nickname = "Delete book", value = "Delete a book by it's ID")
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) throws NoSuchBookException {
		log.info("In method delete, for deleting a book by ID");
		log.debug("delete method takes Book ID as parameter which is path variable");
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(nickname = "Update book details", value = "Update a book details by giving book ID and updated details")
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable int id, @RequestBody BookDTO bookDTO)
			throws NoSuchBookException {
		log.info("In method update, for updating a book details");
		log.debug("update method takes BookDTO as parameter from request body and Book ID which is path variable");
		bookDTO.setId(id);
		return new ResponseEntity<>(bookService.update(bookDTO), HttpStatus.OK);
	}
}
