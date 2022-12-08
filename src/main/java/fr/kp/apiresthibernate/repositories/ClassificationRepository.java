package fr.kp.apiresthibernate.repositories;

import fr.kp.apiresthibernate.entities.ClassificationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class ClassificationRepository {

    EntityManager entityManager = Persistence.createEntityManagerFactory("fr.kp.apiresthibernate").createEntityManager();

    public List<ClassificationEntity> getAll() {
        return entityManager.createNamedQuery("classification.getAll", ClassificationEntity.class).getResultList();
    }

    public ClassificationEntity getById(int id) {
        return entityManager.find(ClassificationEntity.class, id);
    }

    @Transactional
    public boolean update(ClassificationEntity classification) {
        ClassificationEntity updatingClassification = getById(classification.getIdClassification());
        if (updatingClassification == null) return false;
        entityManager.getTransaction().begin();
        entityManager.merge(classification);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    public int create(ClassificationEntity classification) {
        entityManager.getTransaction().begin();
        entityManager.persist(classification);
        entityManager.getTransaction().commit();
        return classification.getIdClassification();
    }

    @Transactional
    public boolean delete(ClassificationEntity classification) {
        ClassificationEntity deletingClassification = getById(classification.getIdClassification());
        if (deletingClassification == null) return false;
        entityManager.getTransaction().begin();
        entityManager.remove(deletingClassification);
        entityManager.getTransaction().commit();
        return true;
    }
}
