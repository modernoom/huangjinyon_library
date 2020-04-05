package com.huangjinyong.library.controller;

import com.huangjinyong.library.Main;
import com.huangjinyong.library.entity.Student;
import com.huangjinyong.library.entity.User;
import com.huangjinyong.library.service.AdminService;
import com.huangjinyong.library.service.StudentService;
import com.huangjinyong.library.service.impl.AdminServiceImpl;
import com.huangjinyong.library.service.impl.StudentServiceImpl;
import com.huangjinyong.library.util.other.Check;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangjinyong
 */
public class LoginController {
    private Main main;
    /**
     * service
     */
    private AdminService adminService=new AdminServiceImpl();
    private StudentService studentService=new StudentServiceImpl();
    /**
     * 注册相关
     */
    @FXML
    private VBox registBox;
    @FXML
    private TextField registTrueName;
    @FXML
    private TextField registUsername;
    @FXML
    private PasswordField registPassword;
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;

    /**
     * 登录相关
     */
    @FXML
    private VBox loginBox;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private Label loginError;


    /**
     * 跳转到注册页面
     */
    @FXML
    private void toRegist() {
        loginBox.setVisible(false);
        registBox.setVisible(true);
    }

    /**
     * 单击注册事件
     */
    @FXML
    private void regist() {
        String username=registUsername.getText();
        String password=registPassword.getText();
        String tureName=registTrueName.getText();
        //检查用户名和密码格式
        boolean isOk=true;
        String nameMsg= Check.checkUsername(username);
        String passwordMsg=Check.checkPassword(password);
        if(!"".equals(nameMsg)){
            usernameError.setText(nameMsg);
            isOk=false;
        }else{
            usernameError.setText("");
        }
        if(!"".equals(passwordMsg)){
            passwordError.setText(passwordMsg);
            isOk=false;
        }else{
            passwordError.setText("");
        }
        if(!isOk){
            return;
        }
        //注册,若数据库没有重复用户名的数据则成功
        Student student = new Student(username, password, tureName);
        boolean success = studentService.regist(student);
        if(!success){
            usernameError.setText("该用户名已存在");
            return;
        }
        //注册成功,跳转到登录页面
        registBox.setVisible(false);
        loginBox.setVisible(true);
    }

    /**
     * 跳转到登录
     */
    @FXML
    private void toLogin(){
        registBox.setVisible(false);
        loginBox.setVisible(true);
    }

    /**
     * 单击登录事件
     *
     */
    @FXML
    private void login() {
        String username=loginUsername.getText();
        String password=loginPassword.getText();
        User user;
        if(isAdmin.isSelected()){
            user=adminService.login(username,password);
            if(user!=null){
                main.setLoginUser(user);

            }
        }else{
            user=studentService.login(username,password);
            if(user!=null){
                main.setLoginUser(user);
                FXMLLoader fx=new FXMLLoader(Main.class.getClassLoader().getResource("com/huangjinyong/library/view/StudentView.fxml"));
                try {
                    AnchorPane pane = fx.load();
                    StudentController controller = fx.getController();
                    controller.setMain(main);
                    main.getPrimaryStage().setScene(new Scene(pane));
                } catch (IOException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }
        if(user==null){
            loginError.setText("用户名或密码错误");
            return;
        }
        loginError.setText("");
    }

    public void setMain(Main main){
        this.main=main;
    }

}

