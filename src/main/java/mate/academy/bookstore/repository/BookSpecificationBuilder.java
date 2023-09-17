package mate.academy.bookstore.repository;

import mate.academy.bookstore.dto.BookSearchParameters;
import mate.academy.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Autowired
    public BookSpecificationBuilder(SpecificationProviderManager<Book>
                                            bookSpecificationProviderManager) {
        this.bookSpecificationProviderManager = bookSpecificationProviderManager;
    }

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParameters.authors()));
        }
        return spec;
    }
}
