package com.capgemini.onlineStore.service.impl;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.persistence.mapper.PurchaseMapper;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.persistence.repo.PurchaseRepository;
import com.capgemini.onlineStore.service.PurchaseService;
import com.capgemini.onlineStore.to.CustomerTO;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.PurchaseTO;
import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidOrderWeightException;
import com.capgemini.onlineStore.validator.exception.InvalidPurchaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class PurchaseServiceImpl implements PurchaseService {

    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(25);

    @Autowired
    private List<Validator> validators;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public PurchaseEntity savePurchase(CustomerTO customer, PurchaseTO purchase) {
        PurchaseTO purchaseToSave = purchase;

        try {
            validators.forEach(validator -> validator.validate(customer, purchase));
        } catch (InvalidOrderWeightException e) {
            purchaseToSave = correctPurchase(purchase);
        } catch (InvalidPurchaseException e) {
            throw e;
        }

        PurchaseEntity purchaseEntity = purchaseMapper.mapToPurchaseEntity(purchaseToSave, CycleAvoidingMappingContext.create());
        return purchaseRepository.save(purchaseEntity);
    }

    private PurchaseTO correctPurchase(PurchaseTO purchase) {
        List<OrderProductTO> orders = purchase.getOrders().stream()
                .map(this::splitOrderIfNecessary)
                .flatMap(Collection::stream)
                .collect(toList());

        return new PurchaseTO.PurchaseTOBuilder()
                .withOrders(orders)
                .withStatus(purchase.getStatus())
                .build();
    }

    private List<OrderProductTO> splitOrderIfNecessary(OrderProductTO orderProductTO) {

        BigDecimal productWeight = orderProductTO.getProduct().getWeight();
        BigDecimal amount = BigDecimal.valueOf(orderProductTO.getAmount());
        BigDecimal maxAmountForProductsInOrder = MAX_WEIGHT.divide(productWeight, RoundingMode.FLOOR);

        List<OrderProductTO> orders = new ArrayList<>();
        int remainingAmount = amount.intValue();

        do {
            int amountToDistribute = Math.min(remainingAmount, maxAmountForProductsInOrder.intValue());
            orders.add(new OrderProductTO.OrderProductTOBuilder()
                    .withProduct(orderProductTO.getProduct())
                    .withAmount(amountToDistribute)
                    .build());
            remainingAmount -= amountToDistribute;
        }
        while (remainingAmount > 0);
        return orders;
    }

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
