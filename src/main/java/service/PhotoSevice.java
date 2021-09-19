package service;

import model.Photo;

import java.util.List;

public interface PhotoSevice {
    List<Photo> findByAlbumId(int albumId);
}
