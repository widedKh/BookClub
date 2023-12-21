package com.w.BookClub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.w.BookClub.models.Book;
import com.w.BookClub.models.User;
import com.w.BookClub.services.BookService;
import com.w.BookClub.services.UserService;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	
	@Autowired
	UserService userService;
	// ------ READ ALL
	@RequestMapping("/books")
	public String booksList(@ModelAttribute("book") Book book, Model model, HttpSession session) {
	    Long userId = (Long) session.getAttribute("user_id");
	    if (userId == null) {
	        return "redirect:/home";
	    } else {
	        User user = userService.findUser(userId); // Retrieve the user object
	        List<Book> books = bookService.allBooks();
	        model.addAttribute("user", user);
	        model.addAttribute("books", books);
	        return "booksList.jsp";  
	    }
	}


    
  //add new book
    @GetMapping("/books/new")
	public String addStudent(@ModelAttribute("book") Book book, Model model, HttpSession session) {
    	Long userId = (Long) session.getAttribute("user_id");
    	model.addAttribute("user", userId);
		return "addBook.jsp";
	}
    
    
    @PostMapping("/books/new")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/"; 
        }

        if (result.hasErrors()) {
            return "addBook.jsp";
        } else {
            User user = userService.findUser(userId);
            book.setUser(user);
     
            bookService.createBook(book);
            return "redirect:/books";
        }
    }


    //display one book
    @GetMapping("/books/{id}")
    public String showBook(@PathVariable("id") Long id, Model model,HttpSession session) {
    	 Long userId = (Long) session.getAttribute("user_id");
    	 if(userId == null) {
			 return "redirect:/";
		 }
    	else {
    		User user = userService.findUser(userId);
    		Book book = bookService.findBook(id);
        	model.addAttribute("book", book);
        	model.addAttribute("user", user);
        	
    	 return "showBook.jsp";
	}
    } 


    @GetMapping("/books/{id}/edit")
    public String editBook(Model model, @PathVariable("id") Long id, HttpSession session) {
    	 Long userId = (Long) session.getAttribute("user_id");
    	 if(userId == null) { 
    		return "redirect:/";
    	}
    	User user =userService.findUser(id);
    	Book book = bookService.findBook(id);
    	model.addAttribute("book", book);
    	model.addAttribute("user", user);
    	
    	return "editBook.jsp";
    }
    
    @PutMapping("/books/{id}/edit")
    public String updateBook(Model model, @Valid @ModelAttribute("book") Book updatedBook, BindingResult result, HttpSession session, @PathVariable("id") Long bookId) {
        Long userId = (Long) session.getAttribute("user_id");

        if (userId == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "editBook.jsp";
        } else {
            Book book = bookService.findBook(bookId);

            if (book == null) {
                return "redirect:/books";
            }

            if (book.getUser().getId().equals(userId)) {
                updatedBook.setUser(book.getUser());
                book = updatedBook;
                bookService.updateBook(book);

                return "redirect:/books";
            } else {
                return "redirect:/books";
            }
        }
    }



    //delete student
    @GetMapping("/books/delete/{id}")
    public String removeBook(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/";
        }
        else {
          bookService.findBook(id);
        

            bookService.deleteBook(id);
        }
        
        return "redirect:/books";
    }
    @GetMapping("/books/{id}/borrow")
    public String borrowBook(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/";
        }

        // Retrieve the user and book
        User user = userService.findUser(userId);
        Book book = bookService.findBook(id);

        // Set the book's user to the borrower (current user)
        book.setUser(user);
        bookService.updateBook(book);

        // Redirect back to the book listing
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/return")
    public String returnBook(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/";
        }

        // Retrieve the book
        Book book = bookService.findBook(id);

        // Reset the book's user to null (returning the book)
        book.setUser(null);
        bookService.updateBook(book);

        // Redirect back to the book listing
        return "redirect:/books";
    }

    



  

}
