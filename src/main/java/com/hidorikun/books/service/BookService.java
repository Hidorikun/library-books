package com.hidorikun.books.service;

import com.hidorikun.books.model.dto.BookDTO;
import com.hidorikun.books.model.entity.Book;
import com.hidorikun.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    private BookDTO bookToDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO dto = new BookDTO();

        dto.setId(book.getId());
        dto.setDescription(book.getDescription());
        dto.setTitle(book.getTitle());
        dto.setReleaseDate(book.getReleaseDate());

        return dto;
    }

    private Book dtoToBook(BookDTO dto) {
        Book book = new Book();

        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setReleaseDate(dto.getReleaseDate());

        return book;

    }

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO getBook(long bookId) {
        return bookToDTO(bookRepository.findById(bookId).orElse(null));
    }

    public BookDTO addBook(BookDTO bookDTO) {
        return bookToDTO(bookRepository.save(dtoToBook(bookDTO)));
    }

    public BookDTO updateBook(long bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null) {
            return null;
        }

        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setReleaseDate(bookDTO.getReleaseDate());

        return bookToDTO(bookRepository.save(book));
    }

    public void removeBook(long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<BookDTO> getBooks() {
        List<BookDTO> books = new ArrayList<>();

        bookRepository.findAll().forEach(book -> books.add(bookToDTO(book)));

        return books;
    }

    public List<BookDTO> getBooks(List<Long> bookIds) {
        List<BookDTO> books = new ArrayList<>();

        bookRepository.findAllById(bookIds).forEach(book -> books.add(bookToDTO(book)));

        return books;
    }
}
