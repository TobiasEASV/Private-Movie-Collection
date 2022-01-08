package bll.util;


import be.CategoryModel;
import be.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher implements ISearcher{

    private boolean isTitleOn;
    private boolean isCatOn;
    private boolean isRatingOn;


    @Override
    public List<MovieModel> search(List<MovieModel> searchBase, String query, boolean isTitleOn, boolean isCatOn, boolean isRatingOn) {
        this.isTitleOn = isTitleOn;
        this.isCatOn = isCatOn;
        this.isRatingOn = isRatingOn;
        List<MovieModel> searchResult = new ArrayList<>();

        for (MovieModel movie : searchBase) {
            if(compareToMovieName(movie, query) || compareToMovieCategory(movie, query) || compareToMovieRating(movie, query))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    @Override
    public boolean compareToMovieName(MovieModel movie, String query) {
        if(isTitleOn){
            return movie.getNameProperty().get().toLowerCase().contains(query.toLowerCase());
        }
        return false;
    }

    @Override
    public boolean compareToMovieCategory(MovieModel movie, String query) {
        if(isCatOn){
            List<CategoryModel> CategoriesToCompare = movie.getAllCategoryAsList();
            for (CategoryModel cat: CategoriesToCompare) {
                if(cat.getNameProperty().get().toLowerCase().contains(query.toLowerCase())){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean compareToMovieRating(MovieModel movie, String query) {
        if(isRatingOn && !query.isEmpty()){
            return movie.getIMDBRatingProperty().get() >= Double.parseDouble(query);
        }
        return false;
    }

}
