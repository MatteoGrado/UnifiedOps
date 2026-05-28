package de.grado.documentationservice.controller;

import de.grado.documentationservice.config.S3Config;
import de.grado.documentationservice.dto.FileUploadRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/documentation")
@AllArgsConstructor
@Slf4j
public class DocController
{
    @GetMapping("/getFiles")
    public Map<String, File> getFiles(S3Config s3)
    {
    }

    @GetMapping("/getFile/{filename}")
    public File getFile(@PathVariable String filename)
    {
    }

    @PostMapping("/uploadFile/{filename}")
    public void uploadFile(@PathVariable String filename, @RequestBody FileUploadRequest uploadRequest)
    {
    }
}
