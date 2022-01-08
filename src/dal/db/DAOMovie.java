package dal.db;

import be.*;
import dal.interfaces.IMovieRepository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOMovie implements IMovieRepository {

    private MyConnection databaseConnector;

    public DAOMovie() throws IOException {
        databaseConnector = new MyConnection();
    }

    @Override
    public List<MovieModel> getAllMovies() throws MovieException {

        List<MovieModel> allMovies = new ArrayList<>();

        //Create a connection
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT * FROM Movie;";
            String sql1 = "SELECT * FROM Category FULL JOIN CatMovie ON Category.id = CatMovie.CatId WHERE CatMovie.movieId = ?;"; //1. sql command
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1); //Create statement

            //Extract data from DB
            if(preparedStatement.execute()){
                ResultSet resultSet = preparedStatement.getResultSet();
                while(resultSet.next()){
                    int movieId = resultSet.getInt("id");
                    String movieTitle = resultSet.getString("title");
                    double IMDBrating = resultSet.getDouble("IMDBrating");
                    double personalrating = resultSet.getDouble("personalrating");
                    String filepath = resultSet.getString("filepath");
                    Date lastview = (Date) resultSet.getObject("lastview");

                    MovieModel movie = new MovieModel(movieId, movieTitle, IMDBrating, filepath);
                    movie.setPersonalRatingProperty(personalrating);
                    movie.setLastView(lastview);

                    preparedStatement1.setInt(1,movieId);
                    if(preparedStatement1.execute()){
                        ResultSet resultSet1 = preparedStatement1.getResultSet();
                        while(resultSet1.next()) {
                            int catId = resultSet1.getInt("id");
                            String catTitle = resultSet1.getString("title");

                            movie.addCatrgroyModel(new CategoryModel(catId, catTitle));
                        }
                    }
                    allMovies.add(movie);
                }
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
        return allMovies;
    }

    @Override
    public MovieModel createMovie(String name, double IMDBRating, String pathToFile) throws MovieException {
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

                    return new MovieModel(id, name, IMDBRating, pathToFile);
                }
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
        return null;
    }

    @Override
    public void updateMovie(MovieModel movie) throws MovieException {

        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET title = ?, filepath=?, IMDBrating=? WHERE Id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getNameProperty().get());
            preparedStatement.setString(2, movie.getPathToFileProperty().get());
            preparedStatement.setDouble(3, movie.getIMDBRatingProperty().get());
            preparedStatement.setDouble(4, movie.getIdProperty().get());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {
                throw new MovieException("Too many row affected");
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public void updateLastview(MovieModel movie) throws MovieException {

        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET lastview = ? WHERE Id= ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, movie.getLastView());
            preparedStatement.setDouble(2, movie.getIdProperty().get());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {
                throw new MovieException("Too many row affected");
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public void updatePersonalRating(MovieModel movie) throws MovieException {

        try(Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET personalrating = ? WHERE Id= ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, movie.getPersonalRatingProperty().get());
            preparedStatement.setDouble(2, movie.getIdProperty().get());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {
                throw new MovieException("Too many row affected");
            }
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public void addCategoryToMovie(CategoryModel category, MovieModel movie) throws MovieException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO CatMovie VALUES (?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, category.getIdProperty().get());
            preparedStatement.setInt(2, movie.getIdProperty().get());
            preparedStatement.executeUpdate();
        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }


    @Override
    public void deleteMovie(MovieModel movie) throws MovieException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE Movie WHERE id = (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, movie.getIdProperty().get());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1){
                throw new MovieException("Too many row affected");
            }

        } catch (SQLException SQLex) {
            throw new MovieException(ERROR_STRING, SQLex.fillInStackTrace());
        }
    }

    public static void main(String[] args) throws IOException, MovieException {
        DAOMovie daoMovie = new DAOMovie();
        List<MovieModel> test =  daoMovie.getAllMovies();
        System.out.println(test.get(1).getAllCategorysAsString().get().toString());
    }
}
