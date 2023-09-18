package mate.academy.bookstore.repository.book;

import java.util.Map;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationBuilder;
import mate.academy.bookstore.repository.SpecificationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Autowired
    public BookSpecificationBuilder(SpecificationProviderManager<Book>
                                            bookSpecificationProviderManager) {
        this.bookSpecificationProviderManager = bookSpecificationProviderManager;
    }

    @Override
    public Specification<Book> build(Map<String,String> searchParameters) {
        Specification<Book> spec = Specification.where(null);
        searchParameters.entrySet().forEach(System.out::println);
        if (searchParameters.containsKey("authors")
                && searchParameters.get("authors") != null) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("authors")
                    .getSpecification(searchParameters.get("authors").split(",")));
        }
        if (searchParameters.containsKey("priceBelow")
                && searchParameters.get("priceBelow") != null) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("priceBelow")
                    .getSpecification(searchParameters.get("priceBelow").split(",")));
        }
        if (searchParameters.containsKey("priceAbove")
                && searchParameters.get("priceAbove") != null) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("priceAbove")
                    .getSpecification(searchParameters.get("priceAbove").split(",")));
        }
        if (searchParameters.containsKey("title")
                && searchParameters.get("title") != null) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParameters.get("title").split(",")));
        }
        return spec;
    }
}
