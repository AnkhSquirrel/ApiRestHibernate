package fr.kp.apiresthibernate.repositories;

import fr.kp.apiresthibernate.dto.ClassificationDto;
import fr.kp.apiresthibernate.entities.ClassificationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class ClassificationRepository {

    EntityManager entityManager = Persistence.createEntityManagerFactory("fr.kp.apiresthibernate").createEntityManager();

    public List<ClassificationDto> getAll() {
        return ClassificationDto.toClassificationDtoList(entityManager.createNamedQuery("classification.getAll", ClassificationEntity.class).getResultList());
    }

    public ClassificationDto getById(int id) {
        return ClassificationDto.toClassificationDto(entityManager.find(ClassificationEntity.class, id));
    }

    @Transactional
    public boolean update(ClassificationDto classification) {
        ClassificationDto updatingClassification = getById(classification.getIdClassification());
        if (updatingClassification == null) return false;
        entityManager.getTransaction().begin();
        entityManager.merge(classification);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    public int create(ClassificationDto classification) {
        entityManager.getTransaction().begin();
        entityManager.persist(classification);
        entityManager.getTransaction().commit();
        return classification.getIdClassification();
    }

    @Transactional
    public boolean delete(ClassificationDto classification) {
        ClassificationDto deletingClassification = getById(classification.getIdClassification());
        if (deletingClassification == null) return false;
        entityManager.getTransaction().begin();
        entityManager.remove(deletingClassification);
        entityManager.getTransaction().commit();
        return true;
    }
}
