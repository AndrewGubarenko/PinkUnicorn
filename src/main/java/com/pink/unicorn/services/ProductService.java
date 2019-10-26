package com.pink.unicorn.services;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.repositories.ProductRepository;
import com.pink.unicorn.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final TagService tagService;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          TagService tagService,
                          ImageService imageService) {
        this.productRepository = productRepository;
        this.tagService = tagService;
        this.imageService = imageService;
    }

    @Transactional
    public PlainProduct createProduct(PlainProduct plainProduct) {

        Product newProduct = new Product();

        newProduct.setId(plainProduct.getId());
        newProduct.setName(plainProduct.getName());
        newProduct.setCategory(plainProduct.getCategory());
        newProduct.setBrand(plainProduct.getBrand());
        newProduct.setDescription(plainProduct.getDescription());
        newProduct.setInSale(plainProduct.isInSale());
        newProduct.setPrice(plainProduct.getPrice());
        newProduct.setSalePrice(plainProduct.getSalePrice());
        newProduct.setCount(plainProduct.getCount());

        productRepository.save(newProduct);

        tagService.findOrCreate(plainProduct.getTags()).forEach(tag -> newProduct.addTag(tag));

        return ProductConverter.ProductToPlain(newProduct);
    }

    @Transactional
    public String updateProduct(String product, Long id) {
        return null;
    }

    @Transactional
    public String getProduct(Long id) {
        return null;
    }

    @Transactional
    public String getProductList(List<String> tags) {
        return null;
    }

    @Transactional
    public String getProductListByTags(String...filters) {
        return null;
    }

    @Transactional
    public String deleteProduct(Long id) {
        return null;
    }
}
