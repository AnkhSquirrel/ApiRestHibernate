package fr.kp.apiresthibernate.repositories;

import fr.kp.apiresthibernate.entities.FamilleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class FamilleRepository {

    EntityManager entityManager = Persistence.createEntityManagerFactory("fr.kp.apiresthibernate").createEntityManager();

    public List<FamilleEntity> getAll() {
        return entityManager.createNamedQuery("famille.getAll", FamilleEntity.class).getResultList();
    }

    public FamilleEntity getById(int id) {
        return entityManager.find(FamilleEntity.class, id);
    }

    @Transactional
    public boolean update(FamilleEntity famille) {
        FamilleEntity updatingFamille = getById(famille.getIdFamille());
        if (updatingFamille == null) return false;
        entityManager.getTransaction().begin();
        entityManager.merge(famille);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    public int create(FamilleEntity famille) {
        entityManager.getTransaction().begin();
        entityManager.persist(famille);
        entityManager.getTransaction().commit();
        return famille.getIdFamille();
    }

    @Transactional
    public boolean delete(FamilleEntity famille) {
        FamilleEntity deletingFamille = getById(famille.getIdFamille());
        if (deletingFamille == null) return false;
        entityManager.getTransaction().begin();
        entityManager.remove(deletingFamille);
        entityManager.getTransaction().commit();
        return true;
    }
}
