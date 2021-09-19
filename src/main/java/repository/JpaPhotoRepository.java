package repository;

import entity.PhotoEntity;
import model.Photo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class JpaPhotoRepository implements PhotoRepository{
    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("practical-project");
    private static final EntityManager em = factory.createEntityManager();

    static {
        em.getTransaction().begin();
        em.persist(PhotoEntity.builder()
                .albumId(2)
                .url("AAAA")
                .title("aaa")
                .thumbnailUrl("cccc")
                .build());
        em.persist(PhotoEntity.builder()
                .albumId(2)
                .url("BBBB")
                .title("bbb")
                .thumbnailUrl("nnn")
                .build());
        em.getTransaction().commit();
    }

    @Override
    public List<Photo> findAll() {
        em.getTransaction().begin();
        List<Photo> resultList =
                em.createQuery("from PhotoEntity", PhotoEntity.class)
                        .getResultList()
                        .stream()
                        .map(e -> Photo.builder()
                                .id(e.getId())
                                .title(e.getTitle())
                                .url(e.getUrl())
                                .albumId(e.getAlbumId())
                                .thumbnailUrl(e.getThumbnailUrl())
                                .build())
                        .collect(Collectors.toList());
        em.getTransaction().commit();
        return resultList;
    }
}
