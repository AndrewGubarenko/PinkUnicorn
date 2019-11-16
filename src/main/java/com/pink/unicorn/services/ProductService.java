package com.pink.unicorn.services;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.ProductRepository;
import com.pink.unicorn.repositories.TagRepository;
import com.pink.unicorn.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ImageService imageService;
    private final TagService tagService;
    private final TagRepository tagRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          TagService tagService,
                          ImageService imageService,
                          ProductConverter productConverter,
                          TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagService = tagService;
        this.imageService = imageService;
        this.productConverter = productConverter;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public PlainProduct createProduct(PlainProduct plainProduct) {

        Product newProduct = setProductData(plainProduct, new Product());

        productRepository.save(newProduct);

        imageService.createImage(plainProduct.getImages()).forEach(image -> newProduct.addImage(image));
        tagService.findOrCreate(plainProduct.getTags()).forEach(tag -> newProduct.addTag(tag));

        return productConverter.ProductToPlain(newProduct);
    }

    @Transactional
    public PlainProduct updateProduct(PlainProduct updatedPlainProduct, Long productId) throws EmptyDataException {
        Optional<Product> foundProductOpt = productRepository.findById(productId);
        if (!foundProductOpt.isPresent()) {
            throw new EmptyDataException("No product with id " + productId + " exists!" );
        }
        Product result = setProductData(updatedPlainProduct, foundProductOpt.get());

        productRepository.save(result);

        return productConverter.ProductToPlain(result);
    }

    @Transactional
    public PlainProduct getProduct(Long productId) throws EmptyDataException {
        Optional<Product> foundProductOpt = productRepository.findById(productId);
        if (!foundProductOpt.isPresent()) {
            throw new EmptyDataException("No product with id " + productId + " exists!");
        }
        Product result = foundProductOpt.get();
        return productConverter.ProductToPlain(result);
    }

    @Transactional
    public List<PlainProduct> getProductList() {
        List<PlainProduct> result = new ArrayList<>();
        productRepository.findAll().forEach(product -> result.add(productConverter.ProductToPlain(product)));
        return result;
    }

    @Transactional
    public Set<PlainProduct> getProductListByTags(List<String> tags) {
        Set<PlainProduct> result = new HashSet<>();
        Set<Long> ids = new HashSet<>();
        tags.forEach(tag -> productRepository.findAllByTags(tagRepository.findByName(tag).get()).forEach(product -> {
            if(!ids.contains(product.getId())) {
                ids.add(product.getId());
                result.add(productConverter.ProductToPlain(product));
            }
        }));
        return result;
    }

    @Transactional
    public String deleteProduct(Long id) throws EmptyDataException {
        Optional<Product> productForDeleteOpt = productRepository.findById(id);
        if (!productForDeleteOpt.isPresent()) {
            throw new EmptyDataException("No product with id " + id + "exists!");
        }
        String productName = productForDeleteOpt.get().getName();
        productRepository.delete(productForDeleteOpt.get());
        return "Product " + productName + " was completely removed";
    }

    private Product setProductData(PlainProduct source, Product target) {
        target.setName(source.getName());
        target.setCategory(source.getCategory());
        target.setBrand(source.getBrand());
        target.setDescription(source.getDescription());
        target.setInSale(source.isInSale());
        target.setPrice(source.getPrice());
        target.setSalePrice(source.getSalePrice());
        target.setCount(source.getCount());
        return target;
    }
}
