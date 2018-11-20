package com.capgemini.onlineStore.service.impl;

import com.capgemini.onlineStore.persistence.entity.*;
import com.capgemini.onlineStore.service.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(readOnly = true)
public class PurchaseServiceImpl implements PurchaseService {

//    @Autowired
//    private List<Validator> validators;

    @Override
    public BigDecimal calculateCostOfOnePurchase(PurchaseEntity purchaseEntity) {
        return purchaseEntity.getOrders().stream()
                .map(o->o.getProduct().getPrice().multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(100).subtract(o.getProduct().getMarge())).multiply(BigDecimal.valueOf(o.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
     }

    @Override
    public BigDecimal getTotalPriceOfOneProduct(ProductEntity productEntity) {
        BigDecimal price = productEntity.getPrice();
        BigDecimal marge = productEntity.getMarge();
        return price.multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(100).subtract(marge));
    }


}
