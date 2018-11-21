package com.capgemini.onlineStore;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.repo.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ProductRepository productRepo;

    @Test(expected = OptimisticLockException.class)
    public void optimisticLockingTest() {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        ProductEntity product = new ProductEntity();
        product.setName("Pralka");
        ProductEntity saved = productRepo.save(product);
        em.getTransaction().commit();

        em.getTransaction().begin();
        ProductEntity foundProduct = productRepo.findAll().get(0);
        em.detach(foundProduct);
        em.getTransaction().commit();

        em.getTransaction().begin();
        ProductEntity afterDetach = productRepo.findAll().get(0);
        afterDetach.setName("Suszarka");
        ProductEntity saved2 = productRepo.save(afterDetach);
        em.getTransaction().commit();

        em.getTransaction().begin();
        foundProduct.setName("Lokówka");
        em.merge(foundProduct);
        em.getTransaction().commit();
    }
}
