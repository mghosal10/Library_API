package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.ILibraryRepository;

@Service
public class LibraryService implements ILibraryService {

	@Autowired
	private ILibraryRepository iLibraryRepository;
	
	@Override
	public Long addBook(Book book) {
		return iLibraryRepository.save(book).getId();
	}

	@Override
	public Book getBook(Long bookId) {
		Optional<Book> optional = iLibraryRepository.findById(bookId);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Book updateBook(Book book) {
		return iLibraryRepository.save(book);
	}

	@Override
	public void removeBook(Long bookId) {
		iLibraryRepository.deleteById(bookId);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> result = new ArrayList<Book>();
		iLibraryRepository.findAll().forEach(result::add);
		return result;
	}

}
