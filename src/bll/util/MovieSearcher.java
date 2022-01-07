package bll.util;

import be.Category;
import be.CategoryModel;
import be.Movie;
import be.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher implements ISearcher{
    @Override
    public List<MovieModel> search(List<MovieModel> searchBase, String query) {
        List<MovieModel> searchResult = new ArrayList<>();

        for (MovieModel movie : searchBase) {
            if(compareToMoveName(movie, query) || compareToMovieCategory(movie, query))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    @Override
    public boolean compareToMoveName(MovieModel movie, String query) {
        return movie.getNameProperty().get().toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public boolean compareToMovieCategory(MovieModel movie, String query) {
       // List<CategoryModel> CategoriesToCompare = movie.getCategorys();
        return true; //movie.getCategories().toLowerCase().contains(query.toLowerCase());
    }
}
