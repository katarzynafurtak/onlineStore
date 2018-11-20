package com.capgemini.onlineStore.service.impl;

import com.capgemini.onlineStore.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
}
