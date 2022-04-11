package com.hjjang.backend.domain.image.domain.repository;

import com.hjjang.backend.domain.image.domain.entity.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaImageRepository implements ImageRepository {

    private final EntityManager em;

    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Image save(Image image) {
        em.persist(image);
        return image;
    }

    @Override
    public Optional<Image> findById(Long id) {
        Image image = em.find(Image.class, id);
        return Optional.ofNullable(image);
    }

    @Override
    public List<Image> findAll() {
        return em.createQuery("select m from Image m", Image.class)
                .getResultList();
    }
}
