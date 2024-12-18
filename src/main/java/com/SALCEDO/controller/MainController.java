package com.SALCEDO.controller;



import com.SALCEDO.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class MainController {


    @FXML
    private TextField FirstName;
    @FXML
    private TextField MiddleName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField Address;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Email;
    @FXML
    private RadioButton Female;
    @FXML
    private RadioButton Male;
    @FXML
    public ToggleGroup gender;
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> colFN;
    @FXML
    private TableColumn<Student, String> colMN;
    @FXML
    private TableColumn<Student, String> colLN;
    @FXML
    private TableColumn<Student, String> colA;
    @FXML
    private TableColumn<Student, String> colPN;
    @FXML
    private TableColumn<Student, String> colE;
    @FXML
    private TableColumn<Student, String> colG;


    private boolean isEditing = false;
    private int studentId =  0;




    private DatabaseConnection db;


    private ObservableList<Student> studentList = FXCollections.observableArrayList();




    public void initialize() throws SQLException {
        db = new DatabaseConnection();


        //load and populate our table
        //bind each column  to the Student class properties and set value factories
        colFN.setCellValueFactory(new PropertyValueFactory<Student, String>("FirstName"));
        colMN.setCellValueFactory(new PropertyValueFactory<Student, String>("MiddleName"));
        colLN.setCellValueFactory(new PropertyValueFactory<Student, String>("LastName"));
        colA.setCellValueFactory(new PropertyValueFactory<Student, String>("Address"));
        colPN.setCellValueFactory(new PropertyValueFactory<Student, String>("PhoneNumber"));
        colE.setCellValueFactory(new PropertyValueFactory<Student, String>("Email"));
        colG.setCellValueFactory(new PropertyValueFactory<Student, String>("Gender"));


        loadStudents();


    }


    public void loadStudents() throws SQLException {
        studentList.clear();
        String sql = "SELECT * from students";


        Statement stmt = db.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(sql);


        while(result.next()){
            Student student = new Student(result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("middle_name"),
                    result.getString("last_name"),
                    result.getString("address"),
                    result.getString("phone_number"),
                    result.getString("email"),
                    result.getString("gender"));
            studentList.add(student);
        }


        table.setItems(studentList);
    }


    @FXML
    private void save() throws SQLException {
        if (!isEditing) {
            String sql = "INSERT INTO students(first_name, middle_name, last_name, address, phone_number,email, gender) VALUES (?, ?, ?,?, ?, ?, ?)";
            PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
            pstmt.setString(1, FirstName.getText());
            pstmt.setString(2, MiddleName.getText());
            pstmt.setString(3, LastName.getText());
            pstmt.setString(4, Address.getText());
            pstmt.setString(5, PhoneNumber.getText());
            pstmt.setString(6, Email.getText());


            if (Male.isSelected()) {
                pstmt.setString(7, "Male");
            } else if (Female.isSelected()) {
                pstmt.setString(7, "Female");
            }
            if (pstmt.executeUpdate() == 1) {
                FirstName.clear();
                MiddleName.clear();
                LastName.clear();
                Address.clear();
                PhoneNumber.clear();
                Email.clear();
                loadStudents();


            }
        }else{
            String sql = "UPDATE students SET first_name = ?, middle_name = ?, last_name = ?, address = ?, phone_number = ?,email = ?, gender = ? WHERE id = ? ";
            try{
                PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
                pstmt.setString(1, FirstName.getText());
                pstmt.setString(2, MiddleName.getText());
                pstmt.setString(3, LastName.getText());
                pstmt.setString(4, Address.getText());
                pstmt.setString(5, PhoneNumber.getText());
                pstmt.setString(6, Email.getText());
                if (Male.isSelected()){
                    pstmt.setString(7, "Male");
                }
                else if(Female.isSelected()){
                    pstmt .setString(7,"Female");
                }else{
                    pstmt.setString(7, "");
                }
                pstmt.setInt(8, studentId);


                if (pstmt.executeUpdate() == 1){
                    FirstName.clear();
                    MiddleName.clear();
                    LastName.clear();
                    Address.clear();
                    PhoneNumber.clear();
                    Email.clear();
                    loadStudents();
                    isEditing = false;
                    studentId = 0;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }


    }
    @FXML
    private void delete(){
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if(selectedStudent != null){
            String sql = "DELETE from students WHERE id = ? ";
            try{
                PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
                pstmt.setInt(1, selectedStudent.getId());
                pstmt.executeUpdate();


                studentList.remove(selectedStudent);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void edit(){
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if(selectedStudent != null){
            FirstName.setText(selectedStudent.getFirstName());
            MiddleName.setText(selectedStudent.getMiddleName());
            LastName.setText(selectedStudent.getLastName());
            Address.setText(selectedStudent.getAddress());
            PhoneNumber.setText(selectedStudent.getPhoneNumber());
            Email.setText(selectedStudent.getEmail());
            isEditing = true;
            studentId = selectedStudent.getId();


        }
    }
}


