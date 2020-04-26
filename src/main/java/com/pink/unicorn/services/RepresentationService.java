package com.pink.unicorn.services;

import com.pink.unicorn.representations.MainPageRepresentation;
import com.pink.unicorn.repositories.ArticleRepository;
import com.pink.unicorn.repositories.CategoryRepository;
import com.pink.unicorn.repositories.ProductRepository;
import com.pink.unicorn.utils.ArticleConverter;
import com.pink.unicorn.utils.CategoryConverter;
import com.pink.unicorn.utils.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class RepresentationService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    private final ProductConverter productConverter;
    private final CategoryConverter categoryConverter;

    @Autowired
    public RepresentationService (CategoryRepository categoryRepository,
                                  ProductRepository productRepository,
                                  ArticleRepository articleRepository,
                                  ArticleConverter articleConverter,
                                  ProductConverter productConverter,
                                  CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.productConverter = productConverter;
        this.categoryConverter = categoryConverter;
    }

    @Transactional(readOnly = true)
    public MainPageRepresentation setMainPageData() {
        MainPageRepresentation representation = new MainPageRepresentation();
        representation.setSetOfCategories(categoryRepository.findAll().stream().map(cat -> categoryConverter.CategoryToPlain(cat)).collect(Collectors.toSet()));
        representation.setProductCarousel(productRepository.getFirst10().stream().map(prod -> productConverter.ProductToPlain(prod)).collect(Collectors.toSet()));
        representation.setSaleCarousel(productRepository.getFirs10ByInSaleTrue().stream().map(prod -> productConverter.ProductToPlain(prod)).collect(Collectors.toSet()));
        representation.setArticleCarousel(articleRepository.getFirst10Articles().stream().map(art -> articleConverter.ArticleToPlain(art)).collect(Collectors.toSet()));
        return representation;
    }
}
