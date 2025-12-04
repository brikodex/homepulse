package com.homepulse.repository;

import com.homepulse.model.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext(unitName = "default") // Remplacez par votre unité de persistence
    private EntityManager em;

    @Override
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public Category findByName(String name) {
        try {
            TypedQuery<Category> query = em.createQuery(
                    "SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
