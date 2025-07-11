/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ltd.quizapp;

import com.ltd.pojo.Question;
import com.ltd.services.questions.QuestionServices;
import com.ltd.utils.Configs;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class PracticeController implements Initializable {
    @FXML private TextField txtNum;
    @FXML private VBox vboxChoices;
    @FXML private Text txtContent;
    @FXML private Text txtResult;
    private List<Question> questions;
    private int currentQuestion = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void handleStart(ActionEvent event) {
//        try {
//            this.questions = Configs.questionServices.getQuestions(Integer.parseInt(this.txtNum.getText()));
//            loadQuestion();
//        } catch (SQLException ex) {
//            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void handleNext(ActionEvent event) {
        if(this.currentQuestion < this.questions.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        }
    }
    
    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++) {
            if (q.getChoices().get(i).isCorrect()) {
                RadioButton rdo = (RadioButton) this.vboxChoices.getChildren().get(i);
                
                this.txtResult.getStyleClass().clear();
                if (rdo.isSelected()) {
                    this.txtResult.setText("Congratulation! Exactly!");
                    this.txtResult.getStyleClass().add("Correct");
                } else {
                    this.txtResult.setText("Sorry! Wrong!");
                    this.txtResult.getStyleClass().add("Wrong");
                }
            }
        } 
    }
    
    private void loadQuestion(){
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());
        
        this.vboxChoices.getChildren().clear();
        ToggleGroup t = new ToggleGroup();
        for(var c : q.getChoices()) {
            RadioButton r = new RadioButton(c.getContent());
            r.setToggleGroup(t);
            this.vboxChoices.getChildren().add(r);
        }
    }
    
}
