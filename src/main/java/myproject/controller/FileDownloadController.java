package myproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//run Application.java - test http://localhost:9090/download
@RestController
public class FileDownloadController { //File Handling
	
	private final String DOWNLOAD_FILE_DIR = "C:\\";
	private final String DOWNLOAD_FILE_NAME = "log.txt";
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile() throws IOException {
		File file = new File(DOWNLOAD_FILE_DIR+DOWNLOAD_FILE_NAME);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, mustrevalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}
}