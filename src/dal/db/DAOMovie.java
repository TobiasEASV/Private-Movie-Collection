package dal.db;

import be.Movie;
import be.MovieException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.interfaces.IMovieRepository;

import java.io.IOException;
import java.sql.*;
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
    public Movie createMovie(String title, String IMDBrating, String genre, double duration, String pathToFile) throws MovieException {
        /**try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Movie VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, IMDBrating);

            preparedStatement.setDouble(3, personalrating);
            preparedStatement.setString(4, filepath);
            preparedStatement.setObject(5, lastview);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    return new Movie(id, title, artist, genre, duration, pathToFile);
                }
            }
        } catch (SQLException | MovieException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }*/
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws MovieException {

    }

    @Override
    public void deleteMovie(Movie movie) throws MovieException {

    }

}

