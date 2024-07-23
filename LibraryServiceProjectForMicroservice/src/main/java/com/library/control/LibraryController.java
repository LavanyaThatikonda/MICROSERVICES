package com.library.control;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.library.entity.Book;
import com.library.entity.Library;
import com.library.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private RestTemplate restTemplate;
	

	@PostMapping("/create")
	public Library createLibrary(@RequestBody Library libray) {
		return libraryService.createLibrary(libray);
	}
	
	@PutMapping("/update/{id}")
	public Library updateLibrary(@PathVariable int id, Library library) {
		return libraryService.updateLibrary(id, library);
	}
	
	 @GetMapping("/get/{id}")
	    public Library getLibraryById(@PathVariable int id) {
	        Book book = restTemplate.getForObject("http://localhost:8081/book/get/" + id, Book.class);
	     Library library = libraryService.getLibraryById(id);
	        library.setBook(book);
	        return library;
	    }
	 
	@DeleteMapping("/delete")
	public void deleteLibrary(int id) {
		libraryService.deleteLibrary(id);
	}
	@GetMapping("/getall")
    public List<Library> getall(){
    return	libraryService.getalllibrary();
    }
}
