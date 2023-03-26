package com.vahansahakyan.photos;

import com.vahansahakyan.photos.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {

}
