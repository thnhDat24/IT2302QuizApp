/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltd.services.exam;

import com.ltd.pojo.Question;
import com.ltd.services.questions.BaseQuestionServices;
import com.ltd.services.questions.LimitQuestionServicesDecorator;
import com.ltd.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExamStrategy extends ExamStrategy {
    private int num;

    public SpecificExamStrategy(int num) {
        this.num = num;
    }
        
    @Override
    public List<Question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitQuestionServicesDecorator(Configs.questionServices, this.num);
        return s.list();
    }
    
}
