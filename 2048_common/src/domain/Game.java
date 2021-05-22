/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nikolab
 */
public class Game implements DomainObject {

    private Long id = -1L;
    private Long userId = -1L;
    private int score = -1;
    private Date date;

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(Long userId, int score, Date date) {
        this.userId = userId;
        this.score = score;
        this.date = date;
    }

    public Game(Long id, Long userId, int score, Date date) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.date = date;
    }
    
    @Override
    public String getTableName() {
        return "game";
    }

    @Override
    public String getAtributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("").append(userId)
                .append(", ").append(score)
                .append(", '").append(new Timestamp(date.getTime()))
                .append("'");
        return sb.toString();
    }

    @Override
    public String getAtributeNames() {
        return "userId, score, date";
    }

    @Override
    public String setAtributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(userId).
                append(",score=").append(score)
                .append(",date='").append(date)
                 .append("'");
        return sb.toString();
    }

    @Override
    public String getNameByColumn(int i) {
        return getAtributeNames().split(",")[i];
    }

    @Override
    public String getWhereCondition() {
        return "id=" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) {
        try {
            Long _id = rs.getLong("id");
            Long _userId = rs.getLong("userId");
            int _score = rs.getInt("score");
            Date _date = rs.getDate("date");
            return new Game(_id, _userId, _score, _date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.userId);
        hash = 29 * hash + this.score;
        hash = 29 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.score != other.score) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", userId=" + userId + ", score=" + score + ", date=" + date + '}';
    }

}
