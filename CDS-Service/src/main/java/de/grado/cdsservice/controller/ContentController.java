package de.grado.cdsservice.controller;

import de.grado.cdsservice.service.ContentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cds")
@Slf4j
@AllArgsConstructor
public class ContentController
{
    private final ContentService contentService;

    @GetMapping("/list/videos")
    public ResponseEntity<List<String>> listVideos()
    {
        //Gets all videos from aws
        return ResponseEntity.ok(contentService.getFiles());
    }

    @GetMapping("/download/videos/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws Exception
    {
        //Needs filename from frontend
        contentService.downloadFile(filename);

        return ResponseEntity.ok().build();
    }
}
