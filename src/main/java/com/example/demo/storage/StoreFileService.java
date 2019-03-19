package com.example.demo.storage;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
@Service
public class StoreFileService {

	Logger log=LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation=Paths.get("C:\\klm-poc\\dataDummy");
	
	
	public void store(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	
	public Resource loadFile(String fileName) {
		try {
			Path file=rootLocation.resolve(fileName);
			Resource resource=new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("FAIL");
			}
		} catch (MalformedURLException e) {
			// TODO: handle exception
			throw new RuntimeException("FAIL");
		}
	}
	
	
	public void init() {
		try {
			//actual location 
			Files.createDirectory(rootLocation);
		}catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException("storage location unintialized");
		}
	}
	
}
