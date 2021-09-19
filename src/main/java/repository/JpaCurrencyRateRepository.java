package repository;

import entity.CurrencyRateEntity;
import entity.PhotoEntity;
import model.CurrencyRate;
import model.Photo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class JpaCurrencyRateRepository implements CurrencyRateRepository{
    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("practical-project");
    private static final EntityManager em = factory.createEntityManager();

    static {
        em.getTransaction().begin();
        em.persist(CurrencyRateEntity.builder()
                        .currency("BAT")
                .code("BAT")
                .mid(1.245)
                .build());
        em.persist(CurrencyRateEntity.builder()
                .currency("USD")
                .code("USD")
                .mid(4.23)
                .build());
        em.getTransaction().commit();
    }
    @Override
    public List<CurrencyRate> findAll() {
        em.getTransaction().begin();
        List<CurrencyRate> resultList =
                em.createQuery("from CurrencyRateEntity ", CurrencyRateEntity.class)
                        .getResultList()
                        .stream()
                        .map(e -> CurrencyRate.builder()
                                .currency(e.getCurrency())
                                .code(e.getCode())
                                .mid(e.getMid())
                                .build())
                        .collect(Collectors.toList());
        em.getTransaction().commit();
        return resultList;
    }
}
