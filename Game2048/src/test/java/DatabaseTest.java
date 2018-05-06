/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import game2048.dao.Database;
import game2048.dao.Score;
import game2048.dao.ScoreDao;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jukka
 */
public class DatabaseTest {
    Score score = new Score(null, "Wizard", 500);
    
    @Test
    public void databaseExists() {
        assertTrue("Score database is missing", databaseFile().exists());
    }

    @Test
    public void scoreExists() throws SQLException {
        findTable("Score");
    }

    @Test
    public void scoreHasNameColumn() throws SQLException {
        hasColumn("Score", "name", "varchar");
    }
    
    public void hasColumn(String table, String columnName, String type) throws SQLException {
        Optional<column> name = getColumns(table).stream().filter(s -> s.name.toLowerCase().equals(columnName)).findFirst();
        if (!name.isPresent()) {
            fail("Table is missing column name");
        }

        if (!name.get().type.toLowerCase().trim().equals(type)) {
            fail("Table's type was wrong");
        }
    }
    

    List<column> getColumns(String table) throws SQLException {
        databaseExists();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseFile().getAbsolutePath())) {
            ResultSet tulos = conn.prepareStatement("SELECT * FROM " + table).executeQuery();
            ResultSetMetaData meta = tulos.getMetaData();

            List<column> columns = new ArrayList<>();
            for (int i = 0; i < meta.getColumnCount(); i++) {
                column col = new column();
                col.name = meta.getColumnName(i + 1);
                col.type = meta.getColumnTypeName(i + 1);

                columns.add(col);
            }

            return columns;
        }

    }

    public ResultSet findTable(String table) throws SQLException {
        databaseExists();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + databaseFile().getAbsolutePath())) {
            ResultSet rs = conn.getMetaData().getTables(null, null, table, null);
            if (!rs.next()) {
                fail("Missing table");
            }

            return rs;
        }
    }
    @Test
    public void findOne() throws Exception{
        Database database = new Database("jdbc:sqlite:scores.db");
        ScoreDao scoreDao = new ScoreDao(database);
        Score helpScore=scoreDao.saveOrUpdate(score);
        assertTrue(scoreDao.findOne(helpScore.getId()).getName().equals("Wizard"));
        scoreDao.delete(helpScore.getId());
    }
    @Test
    public void findAll() throws Exception{
        Database database = new Database("jdbc:sqlite:scores.db");
        ScoreDao scoreDao = new ScoreDao(database);
        assertTrue(scoreDao.findAll().size()>=scoreDao.findTop10().size());
    }
    @Test 
    public void findTop10() throws Exception{
        Database database = new Database("jdbc:sqlite:scores.db");
        ScoreDao scoreDao = new ScoreDao(database);
        assertTrue(scoreDao.findTop10().size()<=10);
    }
    @Test
    public void SaveUpdateDelete() throws Exception{
        Database database = new Database("jdbc:sqlite:scores.db");
        ScoreDao scoreDao = new ScoreDao(database);
        int begin=scoreDao.findAll().size();
        Score helpScore=scoreDao.saveOrUpdate(score);
        int secondSize=scoreDao.findAll().size();
        scoreDao.saveOrUpdate(new Score(helpScore.getId(), helpScore.getName(), 400));
        scoreDao.delete(helpScore.getId());
        assertTrue(begin==scoreDao.findAll().size() && begin<secondSize);
    }
    @Test
    public void noNegativeIds() throws Exception{
        Database database = new Database("jdbc:sqlite:scores.db");
        ScoreDao scoreDao = new ScoreDao(database);
        assertEquals(scoreDao.findOne(-1), null);
    }
    

    static File databaseFile() {
        return new File("scores.db");
    }
    

    static class column {

        String name;
        String type;
    }

}

