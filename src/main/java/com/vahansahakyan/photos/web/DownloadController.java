package com.vahansahakyan.photos.web;

import com.vahansahakyan.photos.model.Photo;
import com.vahansahakyan.photos.service.PhotosService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class DownloadController {
    private final PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }


    @GetMapping("/download/{id}")
    @CrossOrigin
    public ResponseEntity<byte[]> download(@PathVariable Integer id) {
        Photo photo = photosService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment").filename(photo.getFileName()).build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
