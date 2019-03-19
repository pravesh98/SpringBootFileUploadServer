package com.example.demo;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.FileStorageProperties;
import com.example.demo.storage.StoreFileService;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class FlatFilesApplication{

	@RequestMapping("/home")
	@ResponseBody
	String home() {
		return "Flat file test";
	}
	
//	@Resource
//	StoreFileService storageFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(FlatFilesApplication.class, args);
	}

//	@Override
//	public void run(String... arg) throws Exception{
//		storageFileService.init();
//	}
	
}
