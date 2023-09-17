package mate.academy.bookstore.service.impl;

import java.util.List;
import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.BookSearchParameters;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.BookRepository;
import mate.academy.bookstore.repository.BookSpecificationBuilder;
import mate.academy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           BookSpecificationBuilder bookSpecificationBuilder) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookSpecificationBuilder = bookSpecificationBuilder;
    }

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book savedBook = bookRepository.save(bookMapper.toModel(requestDto));
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book bookById = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find the book by id: " + id));
        return bookMapper.toDto(bookById);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        if (bookRepository.findById(id).isPresent()) {
            Book book = bookMapper.toModel(requestDto);
            book.setId(id);
            bookRepository.save(book);
            return bookMapper.toDto(book);
        } else {
            throw new EntityNotFoundException("There are no book in DB to update by id: " + id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("There are no book in DB to delete by id: " + id);
        }
    }

    @Override
    public List<BookDto> search(BookSearchParameters searchParameters) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
