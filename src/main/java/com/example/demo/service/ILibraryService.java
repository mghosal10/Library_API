package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Book;

public interface ILibraryService {

	Long addBook(Book book);
	
	Book getBook(Long bookId);
	
	Book updateBook(Book book);
	
	void removeBook(Long bookId);
	
	List<Book> getAllBooks();
	
}
