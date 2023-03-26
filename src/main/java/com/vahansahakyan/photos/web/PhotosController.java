package com.vahansahakyan.photos.web;

import com.vahansahakyan.photos.model.Photo;
import com.vahansahakyan.photos.service.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@CrossOrigin
public class PhotosController {

    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/")
    @CrossOrigin
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/photos")
    @CrossOrigin
    public Iterable<Photo> get() {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    @CrossOrigin
    public Photo get(@PathVariable Integer id) {
        Photo photo = photosService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    @CrossOrigin
    public void delete(@PathVariable Integer id) {
        photosService.remove(id);
    }

    @PostMapping("/photos")
    @CrossOrigin
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}