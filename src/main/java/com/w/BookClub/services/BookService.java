package com.w.BookClub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w.BookClub.models.Book;
import com.w.BookClub.repositories.BookRepository;


@Service
public class BookService {
   
	@Autowired
	BookRepository bookRepository;
	
	// READ ALL
	public List<Book>allBooks(){
		return bookRepository.findAll();
	}
	
	// create
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}
	
	// READ ONE
	public Book findBook(Long id) {
		Optional<Book>book=bookRepository.findById(id);
		if(book.isPresent()) {
			return book.get();
		}else {
			return null;
		}
	}
	 
	  //edit a book
	    public Book updateBook(Book b) {
	    	return bookRepository.save(b);
	    }
	
	//delete a Book
	public void deleteBook(Long id) {
		 bookRepository.deleteById(id);
	}
	  
}
