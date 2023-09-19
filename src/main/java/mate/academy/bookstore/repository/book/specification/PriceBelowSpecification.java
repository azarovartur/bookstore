package mate.academy.bookstore.repository.book.specification;

import jakarta.persistence.criteria.Predicate;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceBelowSpecification implements SpecificationProvider<Book> {
    private static final String SEARCH_KEY = "priceBelow";

    @Override
    public String getKey() {
        return SEARCH_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate price = criteriaBuilder.lessThan(root.get("price"), params[0]);
            return criteriaBuilder.and(price);
        };
    }
}
