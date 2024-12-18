package model;


public class Student {


    private int id;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String Address;
    private String PhoneNumber;
    private String Email;
    private String Gender;


    /**
     * Student constructor
     * @param id
     * @param FirstName
     * @param MiddleName
     * @param LastName
     * @param Address
     * @param PhoneNumber
     * @param Email
     * @param Gender
     */


    public Student(int id, String FirstName, String MiddleName, String LastName, String Address, String PhoneNumber, String Email, String Gender) {
        this.id = id;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Gender = Gender;
    }


    public Student(){


    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return FirstName;
    }


    public void setFirstName(String firstName) {
        FirstName = firstName;
    }


    public String getMiddleName() {
        return MiddleName;
    }


    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }


    public String getLastName() {
        return LastName;
    }


    public void setLastName(String lastName) {
        LastName = lastName;
    }


    public String getAddress() {
        return Address;
    }


    public void setAddress(String address) {
        Address = address;
    }


    public String getPhoneNumber() {
        return PhoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


    public String getEmail() {
        return Email;
    }


    public void setEmail(String email) {
        Email = email;
    }


    public String getGender() {
        return Gender;
    }


    public void setGender(String gender) {
        Gender = gender;
    }
}
