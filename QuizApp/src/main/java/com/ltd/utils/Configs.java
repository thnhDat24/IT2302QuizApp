/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltd.utils;

import com.ltd.services.CategoryServices;
import com.ltd.services.LevelServices;
import com.ltd.services.questions.QuestionServices;
import com.ltd.services.UpdateQuestionServices;
import com.ltd.services.questions.BaseQuestionServices;

/**
 *
 * @author admin
 */
public class Configs {

    public static final LevelServices levelServices = new LevelServices();
    public static final CategoryServices cateServices = new CategoryServices();
    public static final UpdateQuestionServices uQServices = new UpdateQuestionServices();
    public static BaseQuestionServices questionServices = new QuestionServices();
    
   
}
