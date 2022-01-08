package bll.util;

import be.MovieModel;

import java.util.List;

public interface ISearcher {
    public List<MovieModel> search(List<MovieModel> searchBase, String query, boolean isTitleOn, boolean isCatOn, boolean isRatingOn);

    public boolean compareToMovieName(MovieModel movie, String query);

    public boolean compareToMovieCategory(MovieModel movie, String query);

    public boolean compareToMovieRating(MovieModel movie, String query);
}
