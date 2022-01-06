package bll.util;

import be.Movie;

import java.util.List;

public interface ISearcher {
    public List<Movie> search(List<Movie> searchBase, String query);

    public boolean compareToMoveName(Movie movie, String query);

    public boolean compareToMovieCategory(Movie movie, String query);
}
