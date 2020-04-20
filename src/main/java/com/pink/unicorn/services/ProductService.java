package com.pink.unicorn.services;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.CategoryRepository;
import com.pink.unicorn.repositories.ProductRepository;
import com.pink.unicorn.services.interfaces.ICategoryService;
import com.pink.unicorn.services.interfaces.IProductService;
import com.pink.unicorn.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ImageService imageService;
    private final ICategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ICategoryService categoryService,
                          ImageService imageService,
                          ProductConverter productConverter,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.productConverter = productConverter;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public PlainProduct createProduct(Product product) {

        Product newProduct = setProductData(product, new Product());

        productRepository.save(newProduct);

        imageService.createImage(product.getImages()).forEach(image -> newProduct.addImage(image));
        categoryService.findOrCreate(product.getCategories(), newProduct);
        return productConverter.ProductToPlain(newProduct);
    }

    @Override
    @Transactional
    public PlainProduct updateProduct(Product updatedProduct, Long productId) throws EmptyDataException {
        Optional<Product> foundProductOpt = productRepository.findById(productId);
        if (!foundProductOpt.isPresent()) {
            throw new EmptyDataException("No product with id " + productId + " exists!" );
        }
        Product result = foundProductOpt.get();
        result.setName(updatedProduct.getName());
        result.setBrand(updatedProduct.getBrand());
        result.setDescription(updatedProduct.getDescription());
        result.setInSale(updatedProduct.isInSale());
        result.setPrice(updatedProduct.getPrice());
        result.setSalePrice(updatedProduct.getSalePrice());
        result.setCount(updatedProduct.getCount());

        productRepository.save(result);

        return productConverter.ProductToPlain(result);
    }

    @Override
    @Transactional
    public PlainProduct getProduct(Long productId) throws EmptyDataException {
        Optional<Product> foundProductOpt = productRepository.findById(productId);
        if (!foundProductOpt.isPresent()) {
            throw new EmptyDataException("No product with id " + productId + " exists!");
        }
        Product result = foundProductOpt.get();
        return productConverter.ProductToPlain(result);
    }

    @Override
    @Transactional
    public List<PlainProduct> getProductList() {
        List<Product> list = (List<Product>) productRepository.findAll();
        return list.stream().map(product -> productConverter.ProductToPlain(product)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PlainProduct> getFilteredProductList(List<String> filters) {
        List<PlainProduct> result = new ArrayList<>();
        //TODO: realize method for searching
        return result;
    }

    @Override
    @Transactional
    public String deleteProduct(Long id) throws EmptyDataException {
        Optional<Product> productForDeleteOpt = productRepository.findById(id);
        if (!productForDeleteOpt.isPresent()) {
            throw new EmptyDataException("No product with id " + id + "exists!");
        }
        Product productForDelete = productForDeleteOpt.get();
        String productName = productForDelete.getName();
        productForDelete.removeAllImages();
        productForDelete.removeAllCategories();
        productRepository.delete(productForDelete);
        return "Product " + productName + " was completely removed";
    }

    private Product setProductData(Product source, Product target) {
        target.setName(source.getName());
        target.setBrand(source.getBrand());
        target.setDescription(source.getDescription());
        target.setInSale(source.isInSale());
        target.setPrice(source.getPrice());
        target.setSalePrice(source.getSalePrice());
        target.setCount(source.getCount());
        target.setSubCategories(source.getSubCategories());
        return target;
    }
}
