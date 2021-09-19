package service;

import model.Photo;
import repository.PhotoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PhotoApiService implements PhotoSevice{
    private final PhotoRepository repository;

    public PhotoApiService(PhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Photo> findByAlbumId(int albumId) {
        return repository.findAll()
                .stream()
                .filter(photo -> photo.getAlbumId() == albumId)
                .collect(Collectors.toList());
    }
}
