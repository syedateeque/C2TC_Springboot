package com.syed.malladmin;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class MallAdminController {

	@Autowired
	private MallAdminService service;
	
	@GetMapping("/malladmin")
	public List<MallAdmin> list(){
		return service.listAll();
	}
	
	@GetMapping("/malladmin/{id}")
	public ResponseEntity<MallAdmin> get(@PathVariable Integer id){
		try {
			MallAdmin malladmin =service.get(id);
			return new ResponseEntity<MallAdmin>(malladmin, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<MallAdmin>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/malladmin")
		public void add(@RequestBody MallAdmin malladmin) {
		service.save(malladmin);
	}
	
	@PutMapping("/malladmin/{id}")
	public ResponseEntity<?> update(@RequestBody MallAdmin malladmin, @PathVariable Integer id){		
		try {
			MallAdmin existMallAdmin=service.get(id);
			service.save(malladmin);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("malladmin/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}