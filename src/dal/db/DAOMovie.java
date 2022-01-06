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
    public Movie createMovie(String name, double IMDBRating, String pathToFile) throws MovieException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Movie VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, IMDBRating);
            preparedStatement.setDouble(3, -1); // Personal Rating -default to -1 when it is created
            preparedStatement.setString(4, pathToFile);
            preparedStatement.setObject(5, null);
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

        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET title = ?, filepath=?, IMDBrating=? WHERE Id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getPathToFile());
            preparedStatement.setDouble(3, movie.getIMDBRating());
            preparedStatement.setDouble(4, movie.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {
                throw new MovieException("Too many row affected");
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public void updateLastview(Movie movie) throws MovieException {

        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET lastview = ? WHERE Id= ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, movie.getLastView());
            preparedStatement.setDouble(2, movie.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {
                throw new MovieException("Too many row affected");
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public void updatePersonalRating(Movie movie) throws MovieException {

        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET personalrating = ? WHERE Id= ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, movie.getPersonalRating());
            preparedStatement.setDouble(2, movie.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {
                throw new MovieException("Too many row affected");
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }


    @Override
    public void deleteMovie(Movie movie) throws MovieException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE Movie WHERE id = (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, movie.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1){
                throw new MovieException("Too many row affected");
            }

        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public static void main(String[] args) throws IOException, MovieException {
        DAOMovie test = new DAOMovie();
        Movie movie = test.createMovie("test", 3,"//test//");
        System.out.println(movie.getId() + " " + movie.getName() + " " + movie.getIMDBRating()+ " " + movie.getPersonalRating()+ " " + movie.getPathToFile()+ " " + movie.getLastView() );
        //test.deleteMovie(movie);

    }
}
