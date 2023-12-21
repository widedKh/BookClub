package com.w.BookClub.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.w.BookClub.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
 List<Book>findAll();
}
