package tutorials.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tutorials.domain.model.Tutorials;
import tutorials.domain.repository.TutorialsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialsController {

	@Autowired
	TutorialsRepository tutorialsRepository;

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorials>> getAllTutorials(@RequestParam(required = false) String title) {

		try {
			List<Tutorials> tutorials = new ArrayList<>();

			if (title == null)
				tutorialsRepository.findAll().forEach(tutorials::add);
			else
				tutorialsRepository.findByTitle(title).forEach(tutorials::add);
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorials> getTutorialsById(@PathVariable("id") long id) {
		Optional<Tutorials> tutorialData = tutorialsRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorials> createTutorials(@RequestBody Tutorials tutorials) {
		try {
			Tutorials tutorialSave = tutorialsRepository.save(tutorials);

			return new ResponseEntity<>(tutorialSave, HttpStatus.CREATED);
			// tutorialsRepository.save(tutorials);
			// return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}") 
	public ResponseEntity<Tutorials> updateTutorials(@PathVariable("id") Long id, @RequestBody Tutorials tutorials) { 
		Optional<Tutorials> tutorialData = tutorialsRepository.findById(id);
		
		if(tutorialData.isPresent()) {
			Tutorials tutorialPresent = tutorialData.get();
			tutorialPresent.setTitle(tutorials.getTitle());
			tutorialPresent.setDescription(tutorials.getDescription());
			tutorialPresent.setPublished(tutorials.getPublished());
			
			return new ResponseEntity<>(tutorialsRepository.save(tutorialPresent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	  
	
	  @DeleteMapping("/tutorials/{id}") public 
	  ResponseEntity<HttpStatus> deleteTutorials(@PathVariable("id") long id) { 
		  try {
			tutorialsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }
	  
	  @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() { 
		  try {
			tutorialsRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }
	  
	  @GetMapping("/tutorials/published") 
	  public ResponseEntity<List<Tutorials>> findByPublished() { 
		  try {
			List<Tutorials> tutorials = tutorialsRepository.findByPublished(true);
			if(tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }
	 	 
}
