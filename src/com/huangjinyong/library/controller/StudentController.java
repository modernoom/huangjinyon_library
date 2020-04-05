package com.huangjinyong.library.controller;

import com.huangjinyong.library.Main;
import com.huangjinyong.library.entity.*;
import com.huangjinyong.library.service.*;
import com.huangjinyong.library.service.impl.*;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.other.TimeFormater;
import com.sun.org.apache.xpath.internal.operations.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author huangjinyong
 */
public class StudentController {
    private StudentService studentService =new StudentServiceImpl();
    private LibraryService libraryService=new LibraryServiceImpl();
    private FloorService floorService=new FloorServiceImpl();
    private SeatService seatService=new SeatServiceImpl();
    private CommentService commentService=new CommentServiceImpl();
    private ReservationService reservationService=new ReservationServiceImpl();
    private Integer pageSize=10;
    private Main main;
    private Student student;
    private Library currentLibrary;
    private Floor currentFloor;
    private SeatType currentSeatType;
    private Seat currentSeat;

    @FXML
    private AnchorPane seatScene;
    @FXML
    private AnchorPane studentInfoScene;
    @FXML
    private AnchorPane reservationScene;

    @FXML
    private Pagination seatPage;
    @FXML
    private Pagination reservationPage;
    @FXML
    private ScrollPane commentScene;
    @FXML
    private ImageView seatImg;
    @FXML
    private Label seatUsableTime;
    @FXML
    private Label seatTotalScore;
    @FXML
    private Label seatTimeFrom;
    @FXML
    private Label seatTimeTo;

    @FXML
    private Label showStudentName;
    @FXML
    private Label showLibraryName;
    @FXML
    private Label showFloorName;

    /**
     *  seat tableView and tableColumn
     */

    private TableView<Seat> seatTable=new TableView();
    private TableColumn<Seat, Integer>   seatIdCol=new TableColumn("编号");
    private TableColumn<Seat, ImageView> seatIconCol=new TableColumn("图片");
    private TableColumn<Seat, String>    seatTypeCol=new TableColumn("座位类型");
    private TableColumn<Seat, Double>    seatComfortScoreCol=new TableColumn("舒适分");
    private TableColumn<Seat, Double>    seatAffectScoreCol=new TableColumn("效率分");
    private TableColumn<Seat, Double>    seatTotalScoreCol=new TableColumn("总分");
    private TableColumn<Seat,String> seatStatusCol=new TableColumn("状态");
    ObservableList<Seat> seatModel;
    private Callback<Integer, Node> defaultPageFactory=index->{
        Map map=new HashMap();
        map.put("library_id",currentLibrary.getId());
        map.put("floor_id",currentFloor.getId());
        if(currentSeatType.getId()!=0){
            map.put("seat_type_id",currentSeatType.getId());
        }
        PageBean page = seatService.findByPage(index + 1, pageSize, map);
        seatModel = FXCollections.observableArrayList(page.getList());
        seatTable.setItems(seatModel);
        seatPage.setPageCount(page.getTotalPage());
        return seatTable;
    };
    private Callback<Integer, Node> unOrderPageFactory=index->{
        Map map=new HashMap();
        map.put("library_id",currentLibrary.getId());
        map.put("floor_id",currentFloor.getId());
        map.put("status",1);
        if(currentSeatType.getId()!=0){
            map.put("seat_type_id",currentSeatType.getId());
        }
        PageBean page = seatService.findByPage(index + 1, pageSize, map);
        seatModel = FXCollections.observableArrayList(page.getList());
        seatTable.setItems(seatModel);
        seatPage.setPageCount(page.getTotalPage());
        return seatTable;
    };

    /**
     * 初始化座位列表
     */
    private void initTable() {
        seatTable.getColumns().addAll(seatIdCol,seatIconCol,seatTypeCol,seatComfortScoreCol,seatAffectScoreCol,seatTotalScoreCol,seatStatusCol);
        seatIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        seatIconCol.setCellValueFactory(new PropertyValueFactory("icon"));
        seatTypeCol.setCellValueFactory(new PropertyValueFactory("type"));
        seatComfortScoreCol.setCellValueFactory(new PropertyValueFactory("comfortScore"));
        seatAffectScoreCol.setCellValueFactory(new PropertyValueFactory("affectScore"));
        seatTotalScoreCol.setCellValueFactory(new PropertyValueFactory("totalScore"));
        seatStatusCol.setCellValueFactory(new PropertyValueFactory("statusString"));
        seatPage.setPageFactory(defaultPageFactory);
    }
    /**
     * 只显示未预定事件
     */
    @FXML
    private void onlyShowUnOrder() {
        seatPage.setPageFactory(unOrderPageFactory);
    }

    /**
     * reservation tableView and column
     */

    private TableView<Reservation> reservationTable=new TableView();
    private TableColumn<Reservation,Integer> reservationIdCol=new TableColumn("编号");
    private TableColumn<Reservation,Integer> reservationSeatIdCol=new TableColumn("座位编号");
    private TableColumn<Reservation, java.lang.String> reservationStatusCol=new TableColumn("状态");
    private TableColumn<Reservation, java.lang.String>reservationTimeCol=new TableColumn("时间");
    ObservableList<Reservation> reservationModel;
    private Callback<Integer, Node> defaultReservationPageFactory=index->{
        Map map=new HashMap();
        map.put("student_id",student.getId());
        PageBean page = reservationService.findByPage(index + 1, pageSize,map);
        reservationModel = FXCollections.observableArrayList(page.getList());
        reservationTable.setItems(reservationModel);
        reservationPage.setPageCount(page.getTotalPage());
        return reservationTable;
    };

    /**
     * 初始化预定列表
     */
    private void initReservationTable() { 
        reservationTable.getColumns().addAll(reservationIdCol,reservationStatusCol,reservationSeatIdCol,reservationTimeCol);
        reservationIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        reservationSeatIdCol.setCellValueFactory(new PropertyValueFactory("seatId"));
        reservationStatusCol.setCellValueFactory(new PropertyValueFactory("statusString"));
        reservationTimeCol.setCellValueFactory(new PropertyValueFactory("orderTime"));
    }
    


    /**
     * library items floor items type items
     */
    @FXML
    private ChoiceBox<Library> libraryChoice;
    @FXML
    private ChoiceBox<Floor> floorChoice;
    @FXML
    private ChoiceBox<SeatType> seatTypeChoice;

    private ObservableList<Library> libraryItems;
    private ObservableList<Floor> floorItems;
    private ObservableList<SeatType> seatTypeItems;

    /**
     * 初始化图书馆和楼层
     */
    private void initLibrary(){
        List<Library> libraries = libraryService.findAll();
        currentLibrary=libraries.get(0);
        libraryItems=FXCollections.observableArrayList(libraries);
        libraryChoice.setItems(libraryItems);
        libraryChoice.setValue(currentLibrary);
    }
    private void initFloor(){
        List<Floor> floors = floorService.findAll(currentLibrary.getId());
        currentFloor=floors.get(0);
        floorItems=FXCollections.observableArrayList(floors);
        floorChoice.setItems(floorItems);
        floorChoice.setValue(currentFloor);
    }
    private void initSeatType(){
        List<SeatType> types = seatService.findAllType();
        seatTypeItems=FXCollections.observableArrayList(types);
        seatTypeItems.add(0,new SeatType(0,"全部"));
        currentSeatType=types.get(0);
        seatTypeChoice.setItems(seatTypeItems);
        seatTypeChoice.setValue(currentSeatType);
    }


    @FXML
    private void initialize(){
        initLibrary();

        /**
         * seat被点击事件
         */
        seatTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentSeat=newValue;
            commentScene.setContent(null);
            commentScene.setPannable(true);
            VBox contents=new VBox();
            contents.setSpacing(10);
            Integer seatId=newValue.getId();
            Map map=new HashMap();
            map.put("seat_id",seatId);
            List<Comment> comments= commentService.findByCondition(map);
                for (int i = 0; i < comments.size(); i++) {
                    VBox vBox = new VBox();vBox.setPrefWidth(489);
                    Comment comment = comments.get(i);
                    Label commenterName = new Label(comment.getStudentName()+":");
                    Label commentContent = new Label(comment.getContext());
                    commentContent.setWrapText(true);
                    commentContent.setMaxHeight(100);
                    commentContent.setMaxWidth(480);
                    Label commentTiem = new Label(TimeFormater.timeFormat("yyyy-MM-dd hh:mm:ss",comment.getTime()));
                    vBox.getChildren().addAll(commenterName,commentContent,commentTiem);
                    contents.getChildren().add(vBox);
                }
            commentScene.setContent(contents);
            System.out.println("img/"+newValue.getIcon());
            seatImg.setImage(new Image("img/"+newValue.getIcon()));
            seatTotalScore.setText(java.lang.String.valueOf(newValue.getTotalScore()));
            seatUsableTime.setText(java.lang.String.valueOf(newValue.getUsableTime()));
            seatTimeFrom.setText(java.lang.String.valueOf(newValue.getTimeFrom()));
            seatTimeTo.setText(java.lang.String.valueOf(newValue.getTimeTo()));
        });

        initFloor();
        initSeatType();
        initTable();
        initReservationTable();

        libraryChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentLibrary=newValue;
            showLibraryName.setText(currentLibrary.getName());
            seatPage.setPageFactory(null);
            seatPage.setPageFactory(defaultPageFactory);
        });
        floorChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentFloor=newValue;
            showFloorName.setText(currentFloor.getName());
            seatPage.setPageFactory(null);
            seatPage.setPageFactory(defaultPageFactory);
        });
        seatTypeChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentSeatType=newValue;
            seatPage.setPageFactory(null);
            seatPage.setPageFactory(defaultPageFactory);
        });


    }



    /**
     * 菜单
     */
    @FXML
    private void seatOrder() {
        reservationScene.setVisible(false);
        studentInfoScene.setVisible(false);
        seatScene.setVisible(true);
    }

    @FXML
    private void lookOrder() {
        seatScene.setVisible(false);
        studentInfoScene.setVisible(false);
        reservationScene.setVisible(true);
        reservationPage.setPageFactory(null);
        reservationPage.setPageFactory(defaultReservationPageFactory);
    }

    @FXML
    private void studentInfo() {
        seatScene.setVisible(false);
        reservationScene.setVisible(false);
        studentInfoScene.setVisible(true);
    }

    @FXML
    private void quit() {
    }

    @FXML
    private DatePicker inputDate;
    @FXML
    private ChoiceBox<Integer> timeFromChoice;
    @FXML
    private ChoiceBox<Integer> timeToChoice;
    @FXML
    private Label reservationTip;
    /**
     * 预约按钮
     */
    @FXML
    private void commitReservation(){
        reservationTip.setText("");
        LocalDate dateValue = inputDate.getValue();
        Integer timeFromChoiceValue = timeFromChoice.getValue();
        Integer timeToChoiceValue = timeToChoice.getValue();
        if(dateValue==null||timeFromChoiceValue==null||timeToChoiceValue==null){
            reservationTip.setText("请输入预约时间");
            return;
        }


        if(currentSeat.getStatus()==0){
            reservationTip.setText("该座位已被预约");
            return;
        }
        if(timeFromChoiceValue>=timeToChoiceValue){
            reservationTip.setText("请输入正确时段");
            return;
        }
        Date orderTime = TimeFormater.timeFormatToDate("yyyy-MM-dd HH:mm:ss", dateValue.toString() + " " +timeFromChoiceValue+ ":00:00");
        System.out.println(orderTime);
        int usableTime = currentSeat.getUsableTime();
        Date nowTime=new Date();
        long millis1 = TimeUnit.DAYS.toMillis(usableTime);
        long millis2=orderTime.getTime()-nowTime.getTime();
        if(millis2>millis1||millis2<=0){
            reservationTip.setText("该座位最多提前"+usableTime+"天预约");
            return;
        }
        int currentSeatTimeFrom = currentSeat.getTimeFrom();
        int currentSeatTimeTo = currentSeat.getTimeTo();
        if(timeFromChoiceValue>=currentSeatTimeFrom&&timeFromChoiceValue<currentSeatTimeTo&&timeToChoiceValue<=currentSeatTimeTo&&timeToChoiceValue>currentSeatTimeFrom){
            Reservation reservation = new Reservation();
            reservation.setSeatId(currentSeat.getId());
            reservation.setStudentId(student.getId());
            reservationService.save(reservation);
            reservationTip.setText("预约成功！！");
        }else{
            reservationTip.setText("该时段暂不支持预约");
            return;
        }


    }











    public void setMain(Main main){
        this.main=main;
        this.student=(Student) main.getLoginUser();
        showStudentName.setText(student.getUsername());
        showLibraryName.setText(libraryChoice.getValue().getName());
        showFloorName.setText(floorChoice.getValue().getName());

    }



}
