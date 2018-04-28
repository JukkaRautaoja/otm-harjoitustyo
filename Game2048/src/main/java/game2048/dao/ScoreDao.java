/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2048.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jukka
 */
public class ScoreDao implements Dao<Score, Integer> {

    private Database database;
    
    /**
     * Constructor for ScoreDao.
     * @param database 
     */
    public ScoreDao(Database database) {
        this.database = database;
    }

    /**
     * Deletes Score with the given id.
     * @param key Id for which one to delete
     * @throws SQLException 
     */
    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Score WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
    /**
     * Saves given score to the database
     * @param score
     * @return Saved score
     * @throws SQLException 
     */
    private Score save(Score score) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Score"
                + " (name, maxScore)"
                + " VALUES (?, ?)");
        stmt.setString(1, score.getName());
        stmt.setInt(2, score.getMaxScore());

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM Score"
                + " WHERE name = ?");
        stmt.setString(1, score.getName());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        Score s = new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("maxScore"));

        stmt.close();
        rs.close();

        conn.close();

        return s;
    }

    /**
     * Updates the score matching the given score
     * @param score Update info 
     * @return
     * @throws SQLException 
     */
    private Score update(Score score) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Score SET"
                + " name = ? WHERE id = ?");
        stmt.setInt(1, score.getId());
        stmt.setString(2, score.getName());

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return score;
    }

    /**
     * Checks whether to update or to save a score
     * @param object Score that will be saved or updated
     * @return
     * @throws SQLException 
     */
    @Override
    public Score saveOrUpdate(Score object) throws SQLException {
        if (object.getId() == null) {
            return save(object);
        } else {

            return update(object);
        }
    }

    /**
     * Retrieves all scores from database
     * @return List of all scores
     * @throws SQLException 
     */
    @Override
    public List<Score> findAll() throws SQLException {
        List<Score> scores = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Score score = new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("maxScore"));
            scores.add(score);
        }

        stmt.close();
        rs.close();

        connection.close();
        return scores;
    }

    /**
     * Finds score matching to the given id.
     * @param key Given id
     * @return Score matching to the given id.
     * @throws SQLException 
     */
    @Override
    public Score findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Score WHERE id = ?");
        stmt.setInt(1, key);
        

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Score score = new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("maxScore"));

        stmt.close();
        rs.close();

        conn.close();

        return score;
    }

    /**
     * Retrieves the ten best scores from database
     * @return Top10 scores from the database.
     * @throws SQLException 
     */
    public List<Score> findTop10() throws SQLException {
        List<Score> scores = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Score ORDER BY Score.maxScore DESC LIMIT 10");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Score score = new Score(rs.getInt("id"), rs.getString("name"), rs.getInt("maxScore"));
            scores.add(score);
        }

        stmt.close();
        rs.close();

        connection.close();
        return scores;
    }

}
