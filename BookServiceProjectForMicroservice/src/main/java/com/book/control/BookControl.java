package com.book.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Book;
import com.book.service.BookService;

@RestController
@RequestMapping("/book")
public class BookControl {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/create")
	public Book createBook(@RequestBody Book book) {
		return bookService.createBook(book);
		
	}
	
	@GetMapping("/get/{id}")
	public Optional<Book> getBookByName(@PathVariable int id) {
		return bookService.getBookById(id);
	}
	
	@DeleteMapping("/delete")
   public void deleteBook(int id) {
	 bookService.deleteBook(id);
 }
	@PutMapping("/update/{id}")
	public Book updateBook(@PathVariable int id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}
	@GetMapping("getall")
	public List<Book>getall(){
		return bookService.getallbooks();
	}
}
