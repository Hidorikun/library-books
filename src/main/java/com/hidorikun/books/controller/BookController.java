package com.hidorikun.books.controller;

import com.hidorikun.books.model.dto.BookDTO;
import com.hidorikun.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book/{bookId}")
    public ResponseEntity<BookDTO> getBook(@PathVariable() long bookId) {
        BookDTO bookDTO = bookService.getBook(bookId);

        if (bookDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping("book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @PutMapping("book/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long bookId, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);

        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("book/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable long bookId) {
        bookService.removeBook(bookId);
        return ResponseEntity.ok().build();
    }
}
