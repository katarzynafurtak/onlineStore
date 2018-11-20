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

//    @PersistenceContext
//    private EntityManager em;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ProductRepository productRepo;

    @Test(expected = OptimisticLockException.class)
    public void optimisticLockingTest() {

        EntityManager em = entityManagerFactory.createEntityManager();


        //given
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


//
//        Transaction transaction1 = sessionFactory.getCurrentSession().beginTransaction();
//
//        ProductEntity copy1 = productRepo.findAll().get(0);
//        copy1.setName("Suszarka");
//        productRepo.save(copy1);
//
//        Transaction transaction2 = sessionFactory.getCurrentSession().beginTransaction();
//
//        ProductEntity copy2 = productRepo.findAll().get(0);
//        copy2.setName("Lokówka");
//        productRepo.save(copy1);
//
//       transaction1.commit();
//       transaction2.commit();
//
//
//        List<ProductEntity> foundProducts = productRepo.findAll();
//        Assertions.assertThat(foundProducts).hasSize(1);
//        Assertions.assertThat(foundProducts.get(0).getName()).isEqualTo("Suszarka");


    }


//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        CourseEntity savedJavaCourse = cr.save(CourseEtyTestSet.JAVA_COURSE_1);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        CourseEntity detachedCourse = cr.findById(savedJavaCourse.getId()).get();
//        em.detach(detachedCourse);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        CourseEntity course = cr.findById(savedJavaCourse.getId()).get();
//        course.setName("abcdef");
//        cr.save(course);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        detachedCourse.setName("xyz");
//        em.merge(detachedCourse);
//        em.getTransaction().commit();

    }
