package mate.academy.bookstore;

import java.math.BigDecimal;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
    private final BookService bookService;

    @Autowired
    public BookstoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Book harryPotter = new Book();
                harryPotter.setDescription("Book about Harry Potter");
                harryPotter.setAuthor("Joanne Rowling");
                harryPotter.setIsbn("123414123");
                harryPotter.setPrice(BigDecimal.valueOf(123));
                harryPotter.setCoverImage("coverimage.png");
                harryPotter.setTitle("Harry Potter");
                System.out.println(bookService.save(harryPotter));
                System.out.println(bookService.findAll());
            }
        };
    }
}
