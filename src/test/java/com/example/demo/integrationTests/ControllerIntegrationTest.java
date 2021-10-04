package com.example.demo.integrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.LibraryApiApplication;
import com.example.demo.model.Book;


@SpringBootTest(classes = LibraryApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	long bookId = 1;

	private String getbaseURL() {
		return "http://localhost:" + port;
	}

	@Test
	void testAddBook() {

		Book book = new Book();
		book.setId(bookId);
		book.setName("Test Name");
		book.setAuthor("Test Author");

		ResponseEntity<String> response = restTemplate.postForEntity(getbaseURL() + "/libraryApi/add", book, String.class);

		assertNotNull(response);
		assertNotNull(response.getBody());

	}

	@Test
	public void testGetAllBooks() {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<List> response = restTemplate.exchange(getbaseURL() + "/libraryApi/getAllBooks", HttpMethod.GET, entity, List.class);  
		assertNotNull(response.getBody());
		System.out.println(getbaseURL() +response.getBody());

	}

	@Test
	public void testGetBook() {

		Book book = restTemplate.getForObject(getbaseURL() + "/libraryApi/get/" + bookId, Book.class);
		assertNotNull(book);
		
	}

	@Test
	public void testUpdateBook() {

		Book book = restTemplate.getForObject(getbaseURL() + "/libraryApi/update/" + bookId, Book.class);
		book.setName("Test Updated Name");
		book.setAuthor("Test Updated Author");
		restTemplate.put(getbaseURL() + "/employees/" + bookId, book);
		Book updatedBook = restTemplate.getForObject(getbaseURL() + "/libraryApi/update/" + bookId, Book.class);

		assertNotNull(updatedBook);

	}

	@Test
	public void testRemoveBook() {

		Book book = restTemplate.getForObject(getbaseURL() + "/libraryApi/remove/" + bookId, Book.class);

		assertNotNull(book);

		restTemplate.delete(getbaseURL() + "/libraryApi/remove/" + bookId);
		try {
			book = restTemplate.getForObject(getbaseURL() + "/remove/" + bookId, Book.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
		
	}

}
