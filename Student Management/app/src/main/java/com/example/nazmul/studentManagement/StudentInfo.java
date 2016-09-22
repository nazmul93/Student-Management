package com.example.nazmul.studentManagement;

/**
 * Created by db on 7/17/2016.
 */
public class StudentInfo {
    private int id;
    private String studentName;
    private String fathersName;
    private String mothersName;
    private String address;
    private String contactNumber;
    private String schoolsName;
    private String className;
    private String dateOfBirth;
    private String gender;

    public StudentInfo(int id, String studentName, String fathersName, String mothersName, String address, String contactNumber, String schoolsName, String className, String dateOfBirth, String gender) {
        this.id = id;
        this.studentName = studentName;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.schoolsName = schoolsName;
        this.className = className;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public StudentInfo(String studentName, String fathersName, String mothersName, String address, String contactNumber, String schoolsName, String className, String dateOfBirth, String gender) {
        this.studentName = studentName;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.schoolsName = schoolsName;
        this.className = className;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public StudentInfo() {
        //empty constructor
    }

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSchoolsName() {
        return schoolsName;
    }

    public void setSchoolsName(String schoolsName) {
        this.schoolsName = schoolsName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
