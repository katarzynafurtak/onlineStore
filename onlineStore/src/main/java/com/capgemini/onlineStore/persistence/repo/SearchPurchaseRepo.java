package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;

import java.util.List;

public interface SearchPurchaseRepo {

    public List<PurchaseEntity > findPurchaseByCriteria(PurchaseSearchCriteria searchCriteria);
}
