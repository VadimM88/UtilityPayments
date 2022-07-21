package com.example.legalreportviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class LegalReportViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalReportViewerApplication.class, args);
	}

}
