package com.huangjinyong.library;

import com.huangjinyong.library.controller.LoginController;
import com.huangjinyong.library.entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * @author huangjinyong
 */
public class Main extends Application {

    private User loginUser;
    private Stage primaryStage;


    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }



    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        //load login page
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane anchorPane = fxmlLoader.load(Main.class.getClassLoader().getResourceAsStream("com/huangjinyong/library/view/LoginView.fxml"));
        primaryStage.setScene(new Scene(anchorPane));
        //set Main to login page
        LoginController loginController =fxmlLoader.getController();
        loginController.setMain(this);
        //show stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
