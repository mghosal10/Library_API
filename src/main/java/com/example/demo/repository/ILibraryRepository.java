package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface ILibraryRepository extends CrudRepository<Book, Long> {

}
