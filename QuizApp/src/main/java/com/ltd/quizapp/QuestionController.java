/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ltd.quizapp;

import com.ltd.pojo.Category;
import com.ltd.pojo.Choice;
import com.ltd.pojo.Level;
import com.ltd.pojo.Question;
import com.ltd.services.CategoryServices;
import com.ltd.services.LevelServices;
import com.ltd.services.QuestionServices;
import com.ltd.utils.JdbcConnector;
import com.ltd.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionController implements Initializable {
    @FXML private TextArea txtContent;
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    
    @FXML private ToggleGroup toggleChoice;
    
    private final static CategoryServices cateServices = new CategoryServices();
    private final static LevelServices levelServices = new LevelServices();
    private final static QuestionServices questionServices = new QuestionServices();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {        
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addChoice(ActionEvent event) {
        HBox h =  new HBox();
        h.getStyleClass().add("Main");
              
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        h.getChildren().addAll(r, txt);
        
        this.vboxChoices.getChildren().add(h);
        
        
    }
    
    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(), 
                    this.cbLevels.getSelectionModel().getSelectedItem());
            
            for (var c : this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;
                
                Choice choice = new Choice(((TextField)h.getChildren().get(1)).getText(), 
                        ((RadioButton)h.getChildren().get(0)).isSelected());
                b.addChoice(choice);
            }
            
            questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        }
        catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất bại");
        }
        catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu không hợp lệ");
        }
    }
    
}
