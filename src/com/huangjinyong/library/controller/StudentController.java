package com.huangjinyong.library.controller;

import com.huangjinyong.library.Main;
import com.huangjinyong.library.entity.*;
import com.huangjinyong.library.service.*;
import com.huangjinyong.library.service.impl.*;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.other.Check;
import com.huangjinyong.library.util.other.TimeFormater;
import com.sun.org.apache.xpath.internal.operations.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;



/**
 * @author huangjinyong
 */
@SuppressWarnings("unchecked")
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
        currentSeatType=seatTypeItems.get(0);
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
            if(newValue!=null) {
                currentSeat = newValue;
                commentScene.setContent(null);
                commentScene.setPannable(true);
                VBox contents = new VBox();
                contents.setSpacing(10);
                Integer seatId = newValue.getId();
                Map map = new HashMap();
                map.put("seat_id", seatId);
                List<Comment> comments = commentService.findByCondition(map);
                for (int i = 0; i < comments.size(); i++) {
                    VBox vBox = new VBox();
                    vBox.setPrefWidth(489);
                    Comment comment = comments.get(i);
                    Label commenterName = new Label(comment.getStudentName() + ":");
                    Label commentContent = new Label(comment.getContext());
                    commentContent.setWrapText(true);
                    commentContent.setMaxHeight(100);
                    commentContent.setMaxWidth(480);
                    Label commentTiem = new Label(TimeFormater.timeFormat("yyyy-MM-dd hh:mm:ss", comment.getTime()));
                    vBox.getChildren().addAll(commenterName, commentContent, commentTiem);
                    contents.getChildren().add(vBox);
                }
                commentScene.setContent(contents);
                System.out.println("img/" + newValue.getIcon());
                seatImg.setImage(new Image("img/" + newValue.getIcon()));
                seatTotalScore.setText(java.lang.String.valueOf(newValue.getTotalScore()));
                seatUsableTime.setText(java.lang.String.valueOf(newValue.getUsableTime()));
                seatTimeFrom.setText(java.lang.String.valueOf(newValue.getTimeFrom()));
                seatTimeTo.setText(java.lang.String.valueOf(newValue.getTimeTo()));
            }
        });

        initFloor();
        initSeatType();
        initTable();


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

        //取消预约按钮
        cancelButton.setOnAction(event -> {
            //弹出对话框
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("您确认要取消本次预约吗？");
            Optional<ButtonType> buttonType = alert.showAndWait();
            //用户点击确认
            if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
                //更新订单状态
                currentReservation.setIsScore(1);
                currentReservation.setStatus(0);
                reservationService.updateStatus(currentReservation);
                //更新订单对应的椅子的状态
                reservationSeat.setStatus(1);
                seatService.updateStatus(reservationSeat);
                //切换显示信息
                showCurrentReservation.setVisible(false);
                showFinishScene.setVisible(false);
                showNoReservation.setVisible(true);
            }
        });
        //结束预约按钮
        finishButton.setOnAction(event -> {
            //弹出对话框
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("您确认要结束本次预约吗？");
            Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)){
                    //更新预约状态
                    currentReservation.setIsFinish(1);
                    reservationService.updateFinish(currentReservation);
                    //更新订单对应的椅子的状态
                    reservationSeat.setStatus(1);
                    seatService.updateStatus(reservationSeat);
                    //切换显示信息
                    showCurrentReservation.setVisible(false);
                    showNoReservation.setVisible(false);
                    showFinishScene.setVisible(true);
                }
        });
        //提交评价按钮
        submitEvaluation.setOnAction(event->{
            //获取评分
            Integer comfortScore = comfortChoice.getValue();
            Integer affectScore=affectChoice.getValue();
            if(comfortScore==null||affectScore==null){
                evaluationError.setText("您必须作出评分");
                return;
            }
            //更新订单状态,用户评分之后，该订单才算结束
            currentReservation.setIsScore(1);
            currentReservation.setStatus(0);
            reservationService.updateStatus(currentReservation);
            //更新椅子评分
            seatService.updateScore(reservationSeat,comfortScore,affectScore);
            //处理用户输入的评论
            java.lang.String comment=commentField.getText();
            //用户有评论
            if(!Check.checkIsEmpty(comment)){
                Comment userComment= new Comment();
                userComment.setContext(comment);
                userComment.setStudentName(student.getUsername());
                userComment.setSeatId(reservationSeat.getId());
                commentService.save(userComment);
            }
            //弹出成功提示框
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("成功");
            alert.setContentText("感谢您的本次使用");
            alert.showAndWait();
            //切换到无预约页面
            showNoReservation.setVisible(true);
            showCurrentReservation.setVisible(false);
            showFinishScene.setVisible(false);
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
    private AnchorPane showCurrentReservation;
    @FXML
    private Label showNoReservation;
    @FXML
    private AnchorPane showFinishScene;
    @FXML
    private ChoiceBox<Integer> comfortChoice;
    @FXML
    private ChoiceBox<Integer> affectChoice;
    @FXML
    private Label evaluationError;
    @FXML
    private TextArea commentField;
    @FXML
    private Button submitEvaluation;
    @FXML
    private Label currentReservationDate;
    @FXML
    private Label currentReservationTimeFrom;
    @FXML
    private Label currentReservationTimeTo;
    @FXML
    private ImageView currentReservationImage;
    @FXML
    private Label currentReservationSeatId;
    @FXML
    private Label currentReservationSeatType;
    @FXML
    private Label currentReservationLibName;
    @FXML
    private Label currentReservationFloorName;
    @FXML
    private Button cancelButton;
    @FXML
    private Button finishButton;
    private Reservation currentReservation;
    private Seat reservationSeat;
    @FXML
    private void lookOrder() {
        seatScene.setVisible(false);
        studentInfoScene.setVisible(false);
        reservationScene.setVisible(true);
        //实体类
        SeatType reservationSeatType;
        Library reservationLibrary;
        Floor reservationFloor;
        //查找当前用户进行中的预约
        Map condition=new HashMap();
        condition.put("student_id",student.getId());
        condition.put("status",1);
        List<Reservation> list = reservationService.findByCondition(condition);
        if(list.size()!=0){
            currentReservation=list.get(0);
            //用户结束了预约但还未评分 显示评分页面
            if(currentReservation.getIsFinish()==1&&currentReservation.getIsScore()==0){
                showFinishScene.setVisible(true);
                showNoReservation.setVisible(false);
                showCurrentReservation.setVisible(false);
            }else{
                showCurrentReservation.setVisible(true);
                showFinishScene.setVisible(false);
                showNoReservation.setVisible(false);
            }
            currentReservationDate.setText(TimeFormater.timeFormat("yyyy-MM-dd",currentReservation.getOrderDate()));
            currentReservationTimeFrom.setText(java.lang.String.valueOf(currentReservation.getTimeFrom()));
            currentReservationTimeTo.setText(java.lang.String.valueOf(currentReservation.getTimeTo()));
            //查找该预约对应的seat
            reservationSeat= seatService.findById(currentReservation.getSeatId());
            currentReservationImage.setImage(new Image("img/"+reservationSeat.getIcon()));
            currentReservationSeatId.setText(java.lang.String.valueOf(reservationSeat.getId()));
            //根据seat_type_id字段查找seatType
            reservationSeatType=seatService.findTypeById(reservationSeat.getSeatTypeId());
            currentReservationSeatType.setText(reservationSeatType.getName());
            //根据library_id查找Library
            reservationLibrary = libraryService.findById(reservationSeat.getLibraryId());
            currentReservationLibName.setText(reservationLibrary.getName());
            //根据floor_id查找对应floor
            reservationFloor =floorService.findById(reservationSeat.getFloorId());
            currentReservationFloorName.setText(reservationFloor.getName());
        }
        if(list.size()==0){
            showNoReservation.setVisible(true);
            showCurrentReservation.setVisible(false);
            showFinishScene.setVisible(false);
        }



    }

    @FXML
    private void studentInfo() {
        seatScene.setVisible(false);
        reservationScene.setVisible(false);
        studentInfoScene.setVisible(true);
    }

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
        //检查当前用户是否存在未结束的预约
        if(studentService.haveUnDone(student)){
            reservationTip.setText("预约失败,您当前有未结束或未评分的预约订单");
            return;
        }
        //获取用户输入的日期的时段
        LocalDate dateValue = inputDate.getValue();
        Integer timeFromChoiceValue = timeFromChoice.getValue();
        Integer timeToChoiceValue = timeToChoice.getValue();
        //用户输入为空
        if(dateValue==null||timeFromChoiceValue==null||timeToChoiceValue==null){
            reservationTip.setText("请输入预约时间");
            return;
        }
        //座位已被预约
        if(currentSeat.getStatus()==0){
            reservationTip.setText("该座位已被预约");
            return;
        }
        //输入时段错误
        if(timeFromChoiceValue>=timeToChoiceValue){
            reservationTip.setText("请输入正确时段");
            return;
        }
        //判断输入日期与提前天数是否符合
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
        //获取当前座位被设定的时段
        int currentSeatTimeFrom = currentSeat.getTimeFrom();
        int currentSeatTimeTo = currentSeat.getTimeTo();
        //判断
        if(timeFromChoiceValue>=currentSeatTimeFrom&&timeFromChoiceValue<currentSeatTimeTo&&timeToChoiceValue<=currentSeatTimeTo&&timeToChoiceValue>currentSeatTimeFrom){
            Reservation reservation = new Reservation();
            reservation.setSeatId(currentSeat.getId());
            reservation.setStudentId(student.getId());
            reservation.setOrderDate(TimeFormater.timeFormatToDate("yyyy-MM-dd",dateValue.toString()));
            reservation.setTimeFrom(timeFromChoiceValue);
            reservation.setTimeTo(timeToChoiceValue);
            reservation.setOrderTime(nowTime);
            reservationService.save(reservation);
            reservationTip.setText("预约成功！！");
            //更新椅子的预约状态
            currentSeat.setStatus(0);
            seatService.update(currentSeat);
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
