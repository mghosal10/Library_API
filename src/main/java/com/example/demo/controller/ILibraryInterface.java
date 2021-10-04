package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Book;

@RequestMapping("libraryApi")
public interface ILibraryInterface {
	
	@PostMapping(path="/add")
	ResponseEntity<String> addBook(@RequestBody Book book);
	
	@GetMapping(path="/get/{bookId}")
	ResponseEntity<Book> getBook(@PathVariable Long bookId);
	
	@GetMapping("/getAllBooks")
	ResponseEntity<List<Book>> getAllBooks();
	
	@PutMapping("/update")
	ResponseEntity<Book> updateBook(@RequestBody Book book);
	
	@DeleteMapping("/delete/{bookId}")
	ResponseEntity<String> removeBook(@PathVariable Long bookId);
	
}
