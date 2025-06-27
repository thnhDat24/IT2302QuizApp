package com.ltd.quizapp;

import com.ltd.utils.MyAlert;
import com.ltd.utils.MyStage;
import com.ltd.utils.theme.DarkFactory;
import com.ltd.utils.theme.DefaultFactory;
import com.ltd.utils.theme.LightFactory;
import com.ltd.utils.theme.Theme;
import com.ltd.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    @FXML private ComboBox<Theme> cbThemes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void changeTheme (ActionEvent event){
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
           
    }
    
    public void handleQuestionManagement(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("questions.fxml");
    }
    
    public void handlePractice(ActionEvent event){
        MyAlert.getInstance().showMsg("Comming soon...");
    }
    
    public void handleExam(ActionEvent event){
        MyAlert.getInstance().showMsg("Comming soon...");
    }
    
    public void handleRegister(ActionEvent event){
        MyAlert.getInstance().showMsg("Comming soon...");
    }
    
    public void handleLogin(ActionEvent event){
        MyAlert.getInstance().showMsg("Comming soon...");
    }

    
}
