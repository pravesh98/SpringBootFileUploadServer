package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.storage.StoreFileService;

@RestController
public class UploadFile {
	
	@Autowired
	StoreFileService storageFileService;
	
	List<String> files=new ArrayList<String>();
	
	@CrossOrigin(origins="*")
	@PostMapping("/post")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
//		String message="";
//		try {
//			storageFileService.store(file);
//			
//			files.add(file.getOriginalFilename());
//			message="FILE UPLOADED SUCCESSFULLY" + file.getOriginalFilename();
//			return ResponseEntity.status(HttpStatus.OK).body(message);
//		}catch (Exception e) {
//			// TODO: handle exception
//			message="FAIL TO UPLOAD FILE"+file.getOriginalFilename();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//		}
	try {	
	storageFileService.store(file);	
	String fileName=file.getOriginalFilename();
	String fileDownloadUri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile").path(fileName).toUriString();
	return ResponseEntity.status(HttpStatus.OK).body("file uploaded successfully");
	}catch (Exception e) {
		// TODO: handle exception
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Cannot upload file");
	}
	}
}
