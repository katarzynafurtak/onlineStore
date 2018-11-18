package com.capgemini.onlineStore.persistence.repo;

import java.util.List;

public interface OrderProductRepoCustom {


    double calculateTotalCostOfTransaction(Long id);

    List<String> findSpecificAmountOfBestSellers(int amountOfBestSellers);


}
