package com.example.demo.unitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.LibraryController;
import com.example.demo.model.Book;
import com.example.demo.service.ILibraryService;

@ExtendWith(MockitoExtension.class) 
public class ControllerUnitTest {

	@Mock
	ILibraryService libraryservice;

	@InjectMocks
	LibraryController librarycontroller;

	

	String jsonString = "{\n" +
			"\"id\":1,\n" +
			"\"name\":\"Test Name\",\n" +
			"\"author\":\"Test Author\"\n" +
			"}";

	long bookId = 1;
	Book book = new Book();

	@Before
	public void setUp(){
		book.setId(bookId);
		book.setName("Test Name");
		book.setAuthor("Test Author");
	}

	@Test
	public void addBook_whenPostMethod() throws Exception {
		
		when(libraryservice.addBook(Mockito.any(Book.class))).thenReturn(1l);
		ResponseEntity<String> res = librarycontroller.addBook(book);
		
		assertEquals("Book added successfully, BookId:1", res.getBody());
	}

	@Test
	public void getAllBooks() throws Exception {

		List<Book> books = new ArrayList();
		books.add(new Book());
		Mockito.when(libraryservice.getAllBooks()).thenReturn(books);
		ResponseEntity<List<Book>> res = librarycontroller.getAllBooks();
		
		assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@Test
	public void getBook_whenGetMethod() throws Exception{

		long bookId = 1;
		Mockito.when(libraryservice.getBook(Mockito.any())).thenReturn(book);
		ResponseEntity<Book> res = librarycontroller.getBook(bookId);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@Test
	public void updateItem() throws Exception {

		book.setName("Test Updated Name");
		book.setAuthor("Test Updated Author");
		Mockito.when(libraryservice.updateBook(Mockito.any(Book.class))).thenReturn(book);
		ResponseEntity<Book> res = librarycontroller.updateBook(book);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@Test
	public void removeBookById_whenDeleteMethod() throws Exception {

		long bookId = 1;
		Mockito.doNothing().when(libraryservice).removeBook(Mockito.any());
		ResponseEntity<String> res = librarycontroller.removeBook(bookId);
		
		assertEquals("Successfully deleted book with id: 1", res.getBody());

	}
}
