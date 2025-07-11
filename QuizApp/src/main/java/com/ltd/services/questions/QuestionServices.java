/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltd.services.questions;

import com.ltd.pojo.Category;
import com.ltd.pojo.Choice;
import com.ltd.pojo.Question;
import com.ltd.utils.JdbcConnector;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuestionServices extends BaseQuestionServices {
    
     @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1";
    }

//    public List<Question> getQuestions() throws SQLException{
//        Connection conn = JdbcConnector.getInstance().connect();
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM question");
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            Question q = new Question.Builder(id, content).build();
//            
//            questions.add(q);
//        }
//        return questions;
//    }
    
//    public List<Question> getQuestions(String kw) throws SQLException{
//        Connection conn = JdbcConnector.getInstance().connect();
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ?, '%')");
//        stm.setString(1, kw);
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            Question q = new Question.Builder(id, content).build();
//            
//            questions.add(q);
//        }
//        return questions;
//    }
    
    public List<Question> getQuestions(int num) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stm.setInt(1, num);
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content)
                    .addAllChoice(this.getChoicesByQuestionId(id)).build();
            
            questions.add(q);
        }
        return questions;
    }
    
    public List<Choice> getChoicesByQuestionId(int questionId) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, questionId);
        ResultSet rs = stm.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            boolean correct = rs.getBoolean("is_correct");
            Choice c = new Choice(id, content, correct);
            
            choices.add(c);
        }
        return choices;
    }

    
        
}
