package com.capgemini.onlineStore.validator;

import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.PurchaseTO;

public interface Validator {

    void validate(CustomerTO customer, PurchaseTO purchase);
}
