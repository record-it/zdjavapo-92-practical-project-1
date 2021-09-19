package app;

import model.Photo;
import repository.JpaPhotoRepository;
import repository.PhotoApiRepository;
import repository.PhotoRepository;
import service.PhotoApiService;
import service.PhotoSevice;

import java.util.List;

public class PhotoApiAppLayer {
    private static final PhotoRepository repository = new PhotoApiRepository();
    private static final PhotoSevice service = new PhotoApiService(repository);
    public static void main(String[] args) {
        int albumId = 2;
        List<Photo> list = service.findByAlbumId(albumId);
        System.out.println(list);
    }
}
