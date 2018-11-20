package com.capgemini.onlineStore;

import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidPurchaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ValidatorTest {

    @Autowired
    private Validator validator;

    @Test(expected = InvalidPurchaseException.class)
    public void shouldThrowExceptionValidatingCustomerAndPurchase() {

        //given

    }
}
