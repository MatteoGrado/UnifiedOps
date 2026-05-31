package de.grado.documentationservice.controller;

import de.grado.documentationservice.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documentation")
@AllArgsConstructor
@Slf4j
public class DocController
{
    private final S3Service s3Service;

    @GetMapping("/getFiles")
    public List<String> getFiles()
    {
        //Gets all files from s3
        return s3Service.listFiles();
    }

    @GetMapping("/files/{filename}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename)
    {
        //Gets an specific File
        byte[] file = s3Service.getFile(filename);

        return ResponseEntity.ok()
                .body(file);
    }

    @GetMapping("/download/file/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) {

        byte[] file = s3Service.getFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }

    @PostMapping("/uploadFile/{filename}")
    public void uploadFile(@RequestParam MultipartFile file) throws IOException
    {
        //Requires file to drag and drop in frontend
        s3Service.uploadFile(
                file.getOriginalFilename(),
                file.getBytes()
        );
    }

    @PostMapping("/create/folder/{folderName}")
    public void createFolder(@PathVariable String folderName)
    {
        // Requires filename input in frontend
        s3Service.createFolder(folderName);
    }

    @DeleteMapping("/dlete/folder/{folderName}")
    public void deleteFolder(@PathVariable String folderName)
    {
        //Requires folderName from frontend
        s3Service.deleteFolder(folderName);
    }
}
