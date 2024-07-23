package com.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book createBook(Book book) {
		if(bookRepository.findByName(book.getName())!=null) {
			throw new RuntimeException("book already exists");
		}
			return bookRepository.save(book);
			
		}
	
	public Book updateBook(int id,Book book) {
		Book existingcategory=bookRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("id not found"));
		if (!existingcategory.getName().equals(book.getName()) &&
				bookRepository.findByName(book.getName())!=null) {
			throw new RuntimeException("book name is alredy exists");
			
		}
		existingcategory.setName(book.getName());
		existingcategory.setAuthor(book.getAuthor());
		existingcategory.setPrice(book.getPrice());
		return bookRepository.save(book);
	}
	

	public void deleteBook(int id) {
		Book category=bookRepository.findById(id)
				.orElseThrow(()->new RuntimeException("id not found"));
		bookRepository.save(category);
	}
	
	public Optional<Book> getBookById(int id) {
		Optional<Book> book=bookRepository.findById(id);
				if(book==null) {
					throw new RuntimeException("name not found");
				}
				return bookRepository.findById(id);
	}
	public List<Book> getallbooks(){
		return bookRepository.findAll();
	}
	
}
