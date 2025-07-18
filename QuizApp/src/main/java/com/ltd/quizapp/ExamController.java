/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ltd.quizapp;

import com.ltd.pojo.Question;
import com.ltd.services.exam.ExamStrategy;
import com.ltd.services.exam.ExamTypes;
import com.ltd.services.exam.FixedExamStrategy;
import com.ltd.services.exam.SpecificExamStrategy;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamController implements Initializable {
    @FXML private ComboBox<ExamTypes> cbExamTypes;
    @FXML private TextField txtNum;
    @FXML private ListView<Question> lvQuestions;

    private ExamStrategy s;
    private List<Question> questions;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        
        this.txtNum.setVisible(false);
        
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
                this.txtNum.setVisible(true);
            else
                this.txtNum.setVisible(false);
        });
    }  
    
    public void handleStart(ActionEvent event) throws SQLException {
        if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC)
            s = new SpecificExamStrategy(Integer.parseInt(this.txtNum.getText()));
        else
            s = new FixedExamStrategy();
        
        questions = s.getQuestions();
        this.lvQuestions.setItems(FXCollections.observableList(questions));
        
        this.lvQuestions.setCellFactory(params -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);
                
                if (q == null || empty == true)
                    this.setGraphic(null);
                else {
                    VBox v = new VBox(5);
                    v.setStyle("-fx-border-width: 3; -fx-border-color: gray; -fx-padding: 5");
                    
                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    ToggleGroup toggle = new ToggleGroup();
                    for (var c : q.getChoices()) {
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(toggle);
                        
                        v.getChildren().add(r);
                    }
                    
                    this.setGraphic(v);
                }
            }
            
        });
    }
    
    public void handleFinish(ActionEvent event) {
        
    }
    
    public void handleSave(ActionEvent event) {
        
    }
    
}
