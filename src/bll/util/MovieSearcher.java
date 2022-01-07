package bll.util;

import be.Category;
import be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher implements ISearcher{
    @Override
    public List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase) {
            if(compareToMoveName(movie, query) || compareToMovieCategory(movie, query))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    @Override
    public boolean compareToMoveName(Movie movie, String query) {
        return movie.getName().toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public boolean compareToMovieCategory(Movie movie, String query) {
        List<Category> CategoriesToCompare = movie.getCategories();
        return true; //movie.getCategories().toLowerCase().contains(query.toLowerCase());
    }
}
