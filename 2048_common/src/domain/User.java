/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author nikolab
 */
public class User implements DomainObject {

    private Long id = -1L;
    private String username;
    private String password;
    private int topScore;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public User(Long id, String username, String password, int topScore) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.topScore = topScore;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getAtributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(username)
                .append("', '").append(password)
                .append("', ").append(topScore)
                .append("");
        return sb.toString();
    }

    @Override
    public String getAtributeNames() {
        return "id, username, password, topScore";
    }

    @Override
    public String setAtributeValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("username='").append(username).
                append("',password='").append(password)
                .append("',topScore=").append(topScore);
        return sb.toString();
    }

    @Override
    public String getNameByColumn(int i) {
        return new String[]{"id", "username", "password", "topScore"}[i];
    }

    @Override
    public String getWhereCondition() {
        return "username='" + username + "'";
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) {
        try {
            long dbId = rs.getLong("id");
            String dbUSername = rs.getString("username");
            String dbPassword = rs.getString("password");
            int dbTopScore = (int) rs.getLong("topScore");

            return new User(dbId, dbUSername, dbPassword, topScore);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTopScore() {
        return topScore;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.topScore);
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
        final User other = (User) obj;
        if (this.topScore != other.topScore) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User(id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", topScore=").append(topScore);
        sb.append(')');
        return sb.toString();
    }

}
