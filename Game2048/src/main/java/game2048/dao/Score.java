/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2048.dao;

/**
 *
 * @author jukka
 */
public class Score {

    private Integer id;
    private String name;
    private Integer maxScore;
    /**
     * Constructor for Score.
     * @param id
     * @param name
     * @param max 
     */
    public Score(Integer id, String name, Integer max) {
        this.id = id;
        this.name = name;
        this.maxScore = max;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getMaxScore() {
        return this.maxScore;
    }
}
