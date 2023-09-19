package mate.academy.bookstore.repository.book.specification;

import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecification implements SpecificationProvider<Book> {
    private static final String SEARCH_KEY = "title";

    @Override
    public String getKey() {
        return SEARCH_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.upper(root.get(SEARCH_KEY)),
                        "%" + params[0].toUpperCase() + "%");
    }
}
