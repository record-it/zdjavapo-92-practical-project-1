package repository;

import model.Photo;

import java.util.List;

public interface PhotoRepository {
    List<Photo> findAll();
}
