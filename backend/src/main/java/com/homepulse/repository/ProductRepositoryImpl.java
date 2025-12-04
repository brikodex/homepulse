package com.homepulse.repository;

import com.homepulse.model.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }


    @Override
    public void deleteById(Long id) {
        Product product = findById(id);
        if (product != null) {
            em.remove(product);
        }
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return em.createQuery("SELECT p FROM Product p WHERE p.category.id = :categoryId", Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Product> search(String keyword) {
        return em.createQuery("SELECT p FROM Product p WHERE LOWER(p.name) LIKE :keyword OR LOWER(p.description) LIKE :keyword", Product.class)
                .setParameter("keyword", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }
}
