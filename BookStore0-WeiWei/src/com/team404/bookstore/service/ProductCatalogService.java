package com.team404.bookstore.service;

import com.team404.bookstore.dao.BookDao;
import com.team404.bookstore.dao.CategoryDao;
import com.team404.bookstore.entity.BookEntity;
import com.team404.bookstore.entity.CategoryEntity;

import java.util.List;

public class ProductCatalogService {

    private CategoryDao categoryDao;
    private BookDao bookDao;

    /* gets the list of product categories for the store */
    public List<CategoryEntity> getCategoryList() {
        categoryDao = new CategoryDao();

        List<CategoryEntity> list = categoryDao.ListCategory();

        return list;
    }

    /*gets the list of products for a category*/
    public List<BookEntity> getProductList() {
        bookDao = new BookDao();
        List<BookEntity> list = null;

        list = bookDao.ListBook();

        return list;
    }

    /*gets the list of products for a category*/
    public List<BookEntity> getProductList(int categoryid) {
        bookDao = new BookDao();
        List<BookEntity> list = null;

        list = bookDao.ListBook(categoryid);

        return list;
    }

    /* gets the detailed product information for a product.*/
    public BookEntity getProductInfo(String id) {
        bookDao = new BookDao();
        BookEntity bookEntity = null;

        bookEntity = bookDao.GetBookById(id);

        return  bookEntity;
    }

    /*get the detailed Category info*/
    public CategoryEntity getCategory(int id) {
        categoryDao = new CategoryDao();

        CategoryEntity categoryEntity = categoryDao.getCategoryById(id);

        return categoryEntity;
    }


}
