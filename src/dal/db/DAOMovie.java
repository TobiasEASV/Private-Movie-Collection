package dal.db;

import be.Movie;
import be.MovieException;
import dal.interfaces.IMovieRepository;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

public class DAOMovie implements IMovieRepository {

    private MyConnection databaseConnector;

    public DAOMovie() throws IOException {
        databaseConnector = new MyConnection();
    }

    @Override
    public List<Movie> getAllMovies() throws MovieException {
        return null;
    }

    @Override
    public Movie createMovie(String name, double IMDBRating, String pathToFile, Date lastView) throws MovieException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Movie VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, IMDBRating);
            preparedStatement.setDouble(3, -1); // Personal Rating -default to -1 when it is created
            preparedStatement.setString(4, pathToFile);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    return new Movie(id, name, IMDBRating, pathToFile);
                }
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws MovieException {

    }


    @Override
    public void deleteMovie(Movie movie) throws MovieException {

    }

    public static void main(String[] args) throws IOException, MovieException {
        DAOMovie test = new DAOMovie();
        Movie movie = test.createMovie("test", 3.2,"//test//", null);
        movie.setLastView(new Date());
        System.out.println(movie.getId() + " " + movie.getName() + " " + movie.getIMDBRating()+ " " + movie.getPersonalRating()+ " " + movie.getPathToFile()+ " " + movie.getLastView() );
    }
}
