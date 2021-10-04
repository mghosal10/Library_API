package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Book;
import com.example.demo.service.ILibraryService;

@RestController
public class LibraryController implements ILibraryInterface {

	@Autowired
	private ILibraryService libraryService;
	
	@Override
	public ResponseEntity<String> addBook(Book book) {
		try {
			return ResponseEntity.ok("Book added successfully, BookId:" + libraryService.addBook(book));
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Book> getBook(Long bookId) {
		try {
			Book book = libraryService.getBook(bookId);
			if(book!=null) {
				return ResponseEntity.ok(book);
			}
			else {
				throw new ResponseStatusException(
						  HttpStatus.NOT_FOUND, "entity not found"
						);
			}
		} 
		catch (ResponseStatusException e) {
			throw e;
		}
		catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Book> updateBook(Book book) {
		try {
			return ResponseEntity.ok(libraryService.updateBook(book));
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> removeBook(Long bookId) {
		libraryService.removeBook(bookId);
		return ResponseEntity.ok("Successfully deleted book with id: "+bookId);
	}

	@Override
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			return ResponseEntity.ok(libraryService.getAllBooks());
		} catch (Exception e) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
