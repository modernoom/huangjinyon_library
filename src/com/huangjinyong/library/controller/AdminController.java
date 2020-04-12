package com.huangjinyong.library.controller;

import com.huangjinyong.library.Main;
import com.huangjinyong.library.entity.*;
import com.huangjinyong.library.service.FloorService;
import com.huangjinyong.library.service.LibraryService;
import com.huangjinyong.library.service.SeatService;
import com.huangjinyong.library.service.StudentService;
import com.huangjinyong.library.service.impl.FloorServiceImpl;
import com.huangjinyong.library.service.impl.LibraryServiceImpl;
import com.huangjinyong.library.service.impl.SeatServiceImpl;
import com.huangjinyong.library.service.impl.StudentServiceImpl;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.other.Check;
import com.huangjinyong.library.util.other.ImageUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.huangjinyong.library.util.other.ImageUp.upload;

/**
 * @author huangjinyong
 */
@SuppressWarnings("unchecked")
public class AdminController {
    private Main main;
    private Admin admin;
    private StudentService studentService=new StudentServiceImpl();
    private LibraryService libraryService=new LibraryServiceImpl();
    private FloorService floorService=new FloorServiceImpl();
    private SeatService seatService=new SeatServiceImpl();
    private int pageSize=8;

    
    @FXML
    private Label showAdminName;

    @FXML
    private void initialize(){
        initStudentTable();
        initLibraryTable();
        studentScene.setVisible(false);
        libraryScene.setVisible(false);
        seatScene.setVisible(true);
        setFloorLibChoice();
        initFloorTable();
        setSeatLibChoice();
        setSeatFloorChoice();
        initSeatType();
        initSeatTable();
        seatLibChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                seatCurrentLib=newValue;
                setSeatFloorChoice();
                fleshSeat();
            }
        });
        seatFloorChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                seatCurrentFloor=newValue;
                fleshSeat();
            }
        });
        floorLibChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                floorLib=newValue;
                fleshFloor();
            }
        });
    }
    @FXML
    private AnchorPane studentScene;
    @FXML
    private Pagination studentPage;
    private TableView<Student> studentTableView=new TableView();

    private void initStudentTable(){
         TableColumn<Student,Integer> studentIdCol=new TableColumn("UID");
         TableColumn<Student,String> studentUsernameCol=new TableColumn("用户名");
         TableColumn<Student,String> studentTrueNameCol=new TableColumn("真实姓名");
         TableColumn<Student,String> studentStatusCol=new TableColumn("状态");
        studentTableView.getColumns().addAll(studentIdCol,studentUsernameCol,studentTrueNameCol,studentStatusCol);
        studentIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        studentUsernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        studentTrueNameCol.setCellValueFactory(new PropertyValueFactory("trueName"));
        studentStatusCol.setCellValueFactory(new PropertyValueFactory("statusString"));
        fleshStuData();
    }
    private void fleshStuData(){
        studentPage.setPageFactory(index->{
            PageBean<Student> page = studentService.findByPage(index + 1, pageSize);
            studentPage.setPageCount(page.getTotalPage());
            ObservableList stuItems=FXCollections.observableArrayList(page.getList());
            studentTableView.setItems(stuItems);
            return studentTableView;
        });
    }

    @FXML
    private TextField pullBlackUID;

    /**
     * 拉黑按钮
     */
    @FXML
    private void pullBlackButton(){
        String UID = pullBlackUID.getText();
        //判断是否输入的是数字
        boolean isNum = Check.checkNum(UID);
        if(!isNum){
            infoAlert(false,"q请输入正确数字");
            return;
        }
        //将字符串解析成数字
        int studentId = Integer.parseInt(UID);
        //关系uid对应的用户状态
        Student student = new Student();
        student.setId(studentId);
        student.setStatus(0);
        boolean success=studentService.updateStatus(student);
        //影响行数为 即数据库中不存在该记录
        if(success){
            infoAlert(true,"成功拉黑");
            //成功 刷新studentPage 里的数据
            fleshStuData();
            return;
        }
        infoAlert(false,"UID不存在后用户已经是拉黑状态");

    }

    /**
     * 恢复用户按钮
     */
    @FXML
    private void pullBackButton(){
        String UID = pullBlackUID.getText();
        //判断是否输入的是数字
        boolean isNum = Check.checkNum(UID);
        if(!isNum){
            infoAlert(false,"q请输入正确数字");
            return;
        }
        //将字符串解析成数字
        int studentId = Integer.parseInt(UID);
        //关系uid对应的用户状态
        Student student = new Student();
        student.setId(studentId);
        student.setStatus(1);
        boolean success=studentService.updateStatus(student);
        //影响行数为 即数据库中不存在该记录
        if(success){
            infoAlert(true,"成功恢复");
            //成功 刷新studentPage 里的数据
            fleshStuData();
            return;
        }
        infoAlert(false,"UID不存在或用户已经是正常状态");
    }


    /**
     * 单击管理用户Button
     */
    @FXML
    private void manageStudent() {
        //显示管理用户场景
        studentScene.setVisible(true);
        libraryScene.setVisible(false);
        floorScene.setVisible(false);
        seatScene.setVisible(false);
        //刷新数据
        fleshStuData();
    }

    @FXML
    private AnchorPane libraryScene;
    @FXML
    private Pagination libraryPage;
    private TableView<Library> libraryTable=new TableView();

    private void initLibraryTable(){
         TableColumn<Library,Integer> libraryIdCol=new TableColumn();
        TableColumn<Library,String> libraryNameCol=new TableColumn();
        libraryTable.getColumns().addAll(libraryIdCol,libraryNameCol);
        libraryIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        libraryNameCol.setCellValueFactory(new PropertyValueFactory("name"));
    }
    private void fleshLibData(){
        libraryPage.setPageFactory(index->{
            PageBean<Library> page = libraryService.findByPage(index + 1, pageSize);
            libraryPage.setPageCount(page.getTotalPage());
            ObservableList libItems=FXCollections.observableArrayList(page.getList());
            libraryTable.setItems(libItems);
            return libraryTable;
        });
    }
    @FXML
    private TextField createLibraryName;
    @FXML
    private TextField createLibFloorName;
    @FXML
    private void createLibraryButton(){
        String libName = createLibraryName.getText();
        String libFloorName=createLibFloorName.getText();
        //检查是否为空
        if("".equals(libName)){
            infoAlert(false,"图书馆名不能为空");
            return;
        }
        //检查是否超过40个字节
        boolean b = Check.checkStringLen(libName, 40);
        if(b){
            infoAlert(false,"图书馆名字过长");
            return;
        }
        //检查是否输入了楼层
        if("".equals(libFloorName)){
            infoAlert(false,"你必须为该图书馆至少添加一层楼层");
            return;
        }
        //检查是否超过40个字节
         b = Check.checkStringLen(libFloorName, 40);
        if(b){
            infoAlert(false,"楼层名字过长");
            return;
        }
        Library library = new Library();
        library.setName(libName);
        int id=libraryService.save(library);
        if(id==-1){
            infoAlert(false,"存在同名图书馆");
            return;
        }
        Floor floor=new Floor();
        floor.setName(libFloorName);
        floor.setLibraryId(id);
        floorService.save(floor);
        //提示创建成功，并刷新数据
        infoAlert(true,"新增成功");
        fleshLibData();
    }
    @FXML
    private TextField removeLibraryId;
    @FXML
    private Label removeLibraryError;
    @FXML
    private void removeLibraryButton(){
        removeLibraryError.setText("");
        String libIdString = removeLibraryId.getText();
        if(!Check.checkNum(libIdString)){
            removeLibraryError.setText("请输入正确数字");
            return;
        }
        Integer id=Integer.parseInt(libIdString);
        //弹出警告框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("警告");
        alert.setContentText("删除图书馆会将属于该馆的座位楼层一并删除");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean success=libraryService.delete(id);
            if(success){
                infoAlert(true,"删除成功");
                fleshLibData();
            }
            infoAlert(false,"未找到该图书馆");
        }
    }

    private void infoAlert(boolean success,String msg){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        if(success){
            alert.setHeaderText("成功");
        }else{
            alert.setHeaderText("失败");
        }
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * 单击管理图书馆Button
     */
    @FXML
    private void manageLibrary() {
        //显示管理图书馆场景
        libraryScene.setVisible(true);
        floorScene.setVisible(false);
        studentScene.setVisible(false);
        seatScene.setVisible(false);
        //刷新数据
        fleshLibData();
    }




    @FXML
    private AnchorPane floorScene;
    @FXML
    private ChoiceBox<Library> floorLibChoice;
    private Library floorLib;
    @FXML
    private Pagination floorPage;
    private TableView<Floor> floorTable=new TableView();

    private void initFloorTable(){
        TableColumn<Floor,Integer> floorIdCol=new TableColumn("编号");
        TableColumn<Floor,String> floorNameCol=new TableColumn("名称");
        TableColumn<Floor,Integer> floorLibIdCol=new TableColumn("所属图书馆编号");
        floorTable.getColumns().addAll(floorIdCol,floorNameCol);
        floorIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        floorNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        floorLibIdCol.setCellValueFactory(new PropertyValueFactory("libraryId"));
        fleshFloor();
    }

    private void setFloorLibChoice(){
        ObservableList<Library> floorChoiceItems;
        List<Library> all = libraryService.findAll();
        floorChoiceItems=FXCollections.observableArrayList(all);
        floorLibChoice.setItems(floorChoiceItems);
        floorLibChoice.setValue(all.get(0));
        floorLib=all.get(0);
    }

    private void fleshFloor(){
        floorPage.setPageFactory(index->{
            PageBean<Floor> page = floorService.findByPage(index + 1, pageSize,floorLib.getId());
            floorPage.setPageCount(page.getTotalPage());
            ObservableList<Floor> floorItems=FXCollections.observableArrayList(page.getList());
            floorTable.setItems(floorItems);
            return floorTable;
        });
    }
    @FXML
    private TextField newFloorName;
    @FXML
    private void newFloorButton(){
        String floorName=newFloorName.getText();
        //检查是否为空
        if("".equals(floorName)){
            infoAlert(false,"不能为空");
            return;
        }
        //检查是否超过40个字节
        boolean b = Check.checkStringLen(floorName, 40);
        if(b){
            infoAlert(false,"名字过长");
            return;
        }
        Floor floor = new Floor();
        floor.setName(floorName);
        floor.setLibraryId(floorLib.getId());
        boolean a=floorService.save(floor);
        if(a==false){
            infoAlert(false,"在该图书馆已存在同名楼层");
            return;
        }
        //提示创建成功，并刷新数据
        infoAlert(true,"新增成功");
        fleshFloor();
    }
    @FXML
    private TextField removeFloorId;
    @FXML
    private void removeFloorButton(){
        String floorIdString = removeFloorId.getText();
        if(!Check.checkNum(floorIdString)){
            infoAlert(false,"请输入正确数字");
            return;
        }
        Integer id=Integer.parseInt(floorIdString);
        //弹出警告框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("警告");
        alert.setContentText("删除楼层会将属于该楼层的座位一并删除");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean success=floorService.delete(id);
            if(success){
                infoAlert(true,"删除成功");
                fleshFloor();
            }
            infoAlert(false,"删除失败,未找到该楼层");
        }

    }

    /**
     * 单击管理楼层Button
     */
    @FXML
    private void manageFloor() {
        floorScene.setVisible(true);
        libraryScene.setVisible(false);
        studentScene.setVisible(false);
        seatScene.setVisible(false);
        setFloorLibChoice();
        fleshFloor();
    }
    
    
    @FXML
    private AnchorPane seatScene;

    @FXML
    private  ChoiceBox<Library> seatLibChoice;
    @FXML
    private ChoiceBox<Floor> seatFloorChoice;
    @FXML
    private ChoiceBox<SeatType> seatTypeChoice;
    private Library seatCurrentLib;
    private Floor seatCurrentFloor;

    private void setSeatLibChoice(){
        ObservableList<Library> seatLibItems;
        List<Library> libraries = libraryService.findAll();
        seatCurrentLib=libraries.get(0);
        seatLibItems=FXCollections.observableArrayList(libraries);
        seatLibChoice.setItems(seatLibItems);
        seatLibChoice.setValue(seatCurrentLib);
    }
    private void setSeatFloorChoice(){
      ObservableList<Floor> seatFloorItems;
        List<Floor> floors = floorService.findAll(seatCurrentLib.getId());
      if(floors.size()!=0){
          seatCurrentFloor=floors.get(0);
      }else{
          seatCurrentFloor=null;
      }
      seatFloorItems=FXCollections.observableArrayList(floors);
      seatFloorChoice.setItems(seatFloorItems);
      seatFloorChoice.setValue(seatCurrentFloor);
    }
    private void initSeatType(){
        ObservableList<SeatType> seatTypeItems;
        List<SeatType> types = seatService.findAllType();
        seatTypeItems=FXCollections.observableArrayList(types);
        seatTypeChoice.setItems(seatTypeItems);
    }

    @FXML
    private Pagination seatPage;
    private TableView<Seat> seatTable=new TableView();
    @FXML
    private ImageView seatImg;
    private void initSeatTable() {
        TableColumn<Seat, Integer>  seatIdCol=new TableColumn("编号");
        TableColumn<Seat, String> seatTypeCol=new TableColumn("座位类型");
        TableColumn<Seat, Integer> seatComfortScoreCol=new TableColumn("提前天数");
        TableColumn<Seat, Integer> seatAffectScoreCol=new TableColumn("起始时间");
        TableColumn<Seat, Integer> seatTotalScoreCol=new TableColumn("终止时间");
        TableColumn<Seat,String> seatStatusCol=new TableColumn("状态");
        seatTable.getColumns().addAll(seatIdCol,seatTypeCol,seatComfortScoreCol,seatAffectScoreCol,seatTotalScoreCol,seatStatusCol);
        seatIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        seatTypeCol.setCellValueFactory(new PropertyValueFactory("type"));
        seatComfortScoreCol.setCellValueFactory(new PropertyValueFactory("usableTime"));
        seatAffectScoreCol.setCellValueFactory(new PropertyValueFactory("timeFrom"));
        seatTotalScoreCol.setCellValueFactory(new PropertyValueFactory("timeTo"));
        seatStatusCol.setCellValueFactory(new PropertyValueFactory("statusString"));
        seatTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                seatImg.setImage(new Image("img/"+newValue.getIcon()));
            }
        });
        fleshSeat();
    }
    private void fleshSeat(){
        seatPage.setPageFactory(null);
        seatPage.setPageFactory(index->{
            ObservableList<Seat> seatItems;
            Map map=new HashMap();
            map.put("library_id",seatCurrentLib.getId());
            if(seatCurrentFloor!=null){
                map.put("floor_id",seatCurrentFloor.getId());
            }
            PageBean page = seatService.findByPage(index + 1, pageSize, map);
            System.out.println(page.getList().size());
            seatItems = FXCollections.observableArrayList(page.getList());
            seatTable.setItems(seatItems);
            seatPage.setPageCount(page.getTotalPage());
            return seatTable;
        });
    }

    @FXML
    private TextField usableDayText;
    @FXML
    private TextField timeFromText;
    @FXML
    private TextField timeToText;
    @FXML
    private TextField changeSeatId;

    @FXML
    private void changeSeatButton(){
        SeatType seatType= seatTypeChoice.getValue();
        String usableDayString=usableDayText.getText();
        String timeFromString=timeFromText.getText();
        String timeToString=timeToText.getText();
        String idString = changeSeatId.getText();
        if(Check.checkIsEmpty(idString)){
            infoAlert(false,"请输入修改座位的id");
            return;
        }
        int id=Integer.parseInt(idString);
        Seat seat = seatService.findById(id);
        if(seat==null){
            infoAlert(false,"修改错误,座位不存在");
            return;
        }
        int usableDay=-1;
        int timeFrom=-1;
        int timeTo=-1;
        if(!Check.checkIsEmpty(usableDayString)){
            if(!Check.checkNum(usableDayString)){
                infoAlert(false,"请输入正确提前天数");
                return;
            }
            usableDay=Integer.parseInt(usableDayString);
        }
        if((!Check.checkIsEmpty(timeFromString))||(!Check.checkIsEmpty(timeToString))){
            if(Check.checkPeriod(timeFromString,timeToString)){
                timeFrom=Integer.parseInt(timeFromString);
                timeTo=Integer.parseInt(timeToString);
            }else{
                infoAlert(false,"请输入正确时间段!");
                return;
            }
        }
        if(usableDay!=-1){
            seat.setUsableTime(usableDay);
        }
        if(timeFrom!=-1&&timeTo!=-1){
            seat.setTimeFrom(timeFrom);
            seat.setTimeTo(timeTo);
        }
        if(seatType!=null){
            seat.setSeatTypeId(seatType.getId());
            seatTypeChoice.setValue(null);
        }
        seatService.update(seat);
        infoAlert(true,"修改成功");
        fleshSeat();
    }

    @FXML
    private void newSeatButton(){
        SeatType seatType= seatTypeChoice.getValue();
        String usableDayString=usableDayText.getText();
        String timeFromString=timeFromText.getText();
        String timeToString=timeToText.getText();
        if(seatType==null){
            infoAlert(false,"请为本座位指定类型");
            return;
        }
        //默认值
        int usableDay=5;
        int timeFrom=0;
        int timeTo=23;
        if(!Check.checkIsEmpty(usableDayString)){
            if(!Check.checkNum(usableDayString)){
                infoAlert(false,"请输入正确提前天数");
                return;
            }
            usableDay=Integer.parseInt(usableDayString);
        }
        if((!Check.checkIsEmpty(timeFromString))||(!Check.checkIsEmpty(timeToString))){
            if(Check.checkPeriod(timeFromString,timeToString)){
                timeFrom=Integer.parseInt(timeFromString);
                timeTo=Integer.parseInt(timeToString);
            }else{
                infoAlert(false,"请输入正确时间段!");
                return;
            }
        }
        Seat seat = new Seat();
        seat.setSeatTypeId(seatType.getId());
        seat.setUsableTime(usableDay);
        seat.setTimeFrom(timeFrom);
        seat.setTimeTo(timeTo);
        seat.setLibraryId(seatCurrentLib.getId());
        seat.setFloorId(seatCurrentFloor.getId());
        seatService.save(seat);
        infoAlert(true,"修改成功");
        fleshSeat();
    }
    @FXML
    private TextField removeSeatId;
    @FXML
    private void removeSeatButton(){
        if(!Check.checkNum(removeSeatId.getText())){
            infoAlert(false,"请输入正确编号");
        }
        int id=Integer.parseInt(removeSeatId.getText());
        boolean success=seatService.delete(id);
        if(!success){
            infoAlert(false,"删除失败，座位未找到");
            return;
        }
        infoAlert(true,"移除成功");
        fleshSeat();
    }
    @FXML
    private TextField imgUpLoadId;
    @FXML
    private TextField imgUpLoadPath;
    @FXML
    private void imgUpLoadButton(){
        if(!Check.checkNum(imgUpLoadId.getText())){
            infoAlert(false,"请输入正确编号");
        }
        int id=Integer.parseInt(imgUpLoadId.getText());
        String path=imgUpLoadPath.getText();
        String imgName;
        try {
            imgName=ImageUp.upload(path);
        } catch (IOException e) {
            infoAlert(false,"图片上传失败，请检查图片是否存在或文件是否为.jpg.png格式");
            return;
        }
        boolean success=seatService.updateImg(id,imgName);
        if(!success){
            infoAlert(false,"图片上传失败，未找到座位");
            return;
        }
        infoAlert(true,"图片上传成功");
        fleshSeat();
    }

    @FXML
    private TextField typeText;

    @FXML
    private void newTypeButton(){
        String typeName=typeText.getText();
        if(Check.isEmpty(typeName)){
            infoAlert(false,"类型名称不能为空");
            return;
        }
        boolean success=seatService.saveType(typeName);
    }
    @FXML
    private void removeTypeButton(){

    }
    
    
    
    


    /**
     * 单击管理座位Button
     */
    @FXML
    private void manageSeat() {
        seatScene.setVisible(true);
        floorScene.setVisible(false);
        libraryScene.setVisible(false);
        studentScene.setVisible(false);
        setSeatLibChoice();
        System.out.println(seatCurrentLib);
        setSeatFloorChoice();
        fleshSeat();
    }

    /**
     * 单击退出Button
     */
    @FXML
    private void quit() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            AnchorPane anchorPane = fxmlLoader.load(Main.class.getClassLoader().getResourceAsStream("com/huangjinyong/library/view/LoginView.fxml"));
            main.getPrimaryStage().setScene(new Scene(anchorPane));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void setMain(Main main) {
        this.main=main;
        this.admin=(Admin) main.getLoginUser();
        showAdminName.setText(admin.getUsername());
    }
}
