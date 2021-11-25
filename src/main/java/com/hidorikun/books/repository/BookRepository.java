package com.hidorikun.books.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hidorikun.books.model.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
