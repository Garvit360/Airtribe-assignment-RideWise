package org.example.Interface;

public interface Searchable {
    boolean matchesSearchCriteria(String keyword);

    // Default method
    default void displaySearchResult() {
        System.out.println("Search result found");
    }
}
