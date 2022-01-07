package bll.util;

import be.Movie;
import be.MovieModel;

import java.util.List;

public interface ISearcher {
    public List<MovieModel> search(List<MovieModel> searchBase, String query);

    public boolean compareToMoveName(MovieModel movie, String query);

    public boolean compareToMovieCategory(MovieModel movie, String query);
}
