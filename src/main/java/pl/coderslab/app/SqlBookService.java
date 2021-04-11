package pl.coderslab.app;

import org.springframework.stereotype.Service;
import pl.coderslab.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SqlBookService implements BookService{

    private final BookRepository bookRepository;

    public SqlBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> get(Long id) {

        return Optional.ofNullable(bookRepository.findFirstById(id));
    }

    @Override
    public void delete(Long id) {
    Book book = bookRepository.findFirstById(id);
    bookRepository.delete(book);
    }

    @Override
    public void update(Book book) {
    bookRepository.save(book);
    }
}
