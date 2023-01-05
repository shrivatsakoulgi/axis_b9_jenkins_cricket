package com.axis.springbootdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axis.springbootdemo.entity.Cricketer;

@RestController
public class CricketerController {
	
	private static ArrayList<Cricketer> crickList;
	static {
		crickList=new ArrayList<>();
		crickList.add(new Cricketer(1001, "Suryakumar Yadav", 111, 51, 8, 6, 217.2));
		crickList.add(new Cricketer(1002, "Virat Kohlli", 82, 60, 6, 5, 130.8));
		crickList.add(new Cricketer(1003, "Mithali Raj", 62, 62, 4, 2, 100));
		crickList.add(new Cricketer(1004, "Harmanpreet Kaur", 40, 20, 4, 2, 200.0));
		crickList.add(new Cricketer(1005, "Smrithi Mandhana", 52, 40, 3, 3, 130.0));
		
	}
	


	// Get all Cricketers
	@GetMapping("/cricketers")
	public ArrayList<Cricketer> getCricketer() {
		return crickList;
	}
	
	//Get Cricketer by Id	
	@GetMapping("/cricketer/{cricketerId}")
	public Cricketer getCricketerById(@PathVariable int cricketerId) {
		for(Cricketer ck:crickList) {
			if(ck.getCricketerId()==cricketerId) {
				return ck;		// return Cricketer if Id is found
			}
		}
		return null;			// return null if Cricketer id not found 
	}

	//Add multiple Cricketers to the List
	@PostMapping("/cricketer")
	public ResponseEntity<String> addCricketer(@RequestBody List<Cricketer> newCrickList) {
		crickList.addAll(newCrickList);
		return new ResponseEntity<String>("Cricketer Added Successfully...", HttpStatus.CREATED);
		
	}
	
	 
	// Update request	
	@PutMapping("/cricketer/update/{cricketerId}")
	public ResponseEntity<String> updateCricketer(@PathVariable int cricketerId, @RequestBody Cricketer updatedCricketer){
		if(cricketerId!=updatedCricketer.getCricketerId()) {
			return new ResponseEntity<String>("Cricketer id's do not match!!!",HttpStatus.BAD_REQUEST);
		}
		int index=crickList.indexOf(updatedCricketer);
		if(index==-1) {
			return new ResponseEntity<String>("Cricketer with id:"+cricketerId+" is not Found..!!",HttpStatus.NOT_FOUND);
		}else {
			crickList.get(index).setBalls(updatedCricketer.getBalls());
			crickList.get(index).setRunsScored(updatedCricketer.getRunsScored());
			crickList.get(index).setFours(updatedCricketer.getFours());
			crickList.get(index).setSixes(updatedCricketer.getSixes());
			crickList.get(index).setStrikeRate(updatedCricketer.getStrikeRate());
			return new ResponseEntity<String>("Cricketer Data updated Successfully !!!",HttpStatus.OK);
		}		
	}
	
	// Delete Mapping
	@DeleteMapping("/cricketer/delete/{cricketerId}")
	public ResponseEntity<String> deleteCricketer(@PathVariable int cricketerId){
		
		Cricketer cricketer = getCricketerById(cricketerId);
		if(cricketer==null) {
			return new ResponseEntity<String>("Cricketer with id:"+cricketerId+" is not Found..!!",HttpStatus.NOT_FOUND);
		}else {
			crickList.remove(cricketer);
			return new ResponseEntity<String>("Cricketer with id:"+cricketerId+" deleted Successfully !!!",HttpStatus.OK);
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
