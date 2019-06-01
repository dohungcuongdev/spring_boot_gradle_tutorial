package myproject.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//run Application.java - test http://localhost:9090/upload
@RestController
public class FileUploadController { //File Handling
	
	private final String UPLOAD_FILE_DIR = "C:\\";
	
	// working with src/main/resources/templates/upload-file.html
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		File convertFile = new File(UPLOAD_FILE_DIR + file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return "File is upload successfully";
	}
}