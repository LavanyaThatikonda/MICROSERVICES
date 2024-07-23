package com.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Library;
import com.library.repository.LibraryRepository;

@Service
public class LibraryService {
	
	@Autowired
	private LibraryRepository libraryRepository;

	public Library createLibrary(Library library) {
		if(libraryRepository.findByName(library.getName())!=null) {
			throw new RuntimeException("library is alreday exists");
		}
		return libraryRepository.save(library);
	}
	
	
	public Library updateLibrary(int id, Library library) {
		Library existingcategory=libraryRepository.findById(id)
				.orElseThrow(()->new RuntimeException("id not found"));
		if(!existingcategory.getName().equals(library.getName())&&
				libraryRepository.findByName(library.getName())!=null){
					throw new RuntimeException("book name alredy exists");
				}
		existingcategory.setName(library.getName());
		existingcategory.setAddress(library.getAddress());
		existingcategory.setMail(library.getMail());
		
		return libraryRepository.save(library);
	}
	
	public void deleteLibrary(int id) {
		Library library=libraryRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("id not found"));
		libraryRepository.delete(library); 
		
	}
	 public Library getLibraryById(int id) {
	        // Use Optional to handle the absence of the library
	        Optional<Library> optionalLibrary = libraryRepository.findById(id);

	        // Check if the library is present, otherwise throw an exception
	        if (!optionalLibrary.isPresent()) {
	            throw new RuntimeException("Library with this ID does not exist");
	        }

	        // Return the library if present
	        return optionalLibrary.get();
	    }

	public List<Library> getalllibrary(){
		return libraryRepository.findAll();
	}
	
}
