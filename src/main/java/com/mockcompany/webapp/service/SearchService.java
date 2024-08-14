package com.mockcompany.webapp.service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {
    /**
     * This is a instance field.  It is provided by the spring framework through the constructor because of the
     * @Autowired annotation.  Autowire tells the spring framework to automatically find and use an instance of
     * the declared class when creating this class.
     */
    private final ProductItemRepository productItemRepository;

    @Autowired
    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public Collection<ProductItem> search(String query) {

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        // Determine whether the user wants exact matches to their search
        boolean isExactMatch = (query.startsWith("\"") && query.endsWith("\"")) || (query.startsWith("\'") && query.endsWith("\'"));

        // If yes to exact matches, truncate the extra quotes
        String lowerCaseQuery = isExactMatch
                ? query.substring(1, query.length() - 1).toLowerCase()
                : query.toLowerCase();

        for (ProductItem item : allItems) {
            if (isExactMatch) {
                // Check for matches in both item name and description
                if ((item.getName() != null && item.getName().equalsIgnoreCase(lowerCaseQuery)) ||
                        (item.getDescription() != null && item.getDescription().equalsIgnoreCase(lowerCaseQuery))) {
                    itemList.add(item);
                }
            } else {
                // Check for matches in both item name and description
                if ((item.getName() != null && item.getName().toLowerCase().contains(lowerCaseQuery)) ||
                        (item.getDescription() != null && item.getDescription().toLowerCase().contains(lowerCaseQuery))) {
                    itemList.add(item);
                }
            }
        }
        return itemList;
    }
}
