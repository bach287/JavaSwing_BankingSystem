/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
/**
 *
 * @author Admin
 */
public class Customer {
    private String accNumber;
    private String name;
    private String phoneNumber;
    private String dob;
    private String address;
    private long balance = 0;

    public Customer(){
        
    }
    
    public Customer(String accNumber, String name, String phoneNumber, String dob, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.address = address;
        this.accNumber = accNumber;
    }
    
    public void deposit(long amount){
        balance += amount;
    }
    
    public int withdraw(long amount){
        if(amount > balance){
            return -1;
        }
        balance -= amount;
        return 0;
    }
    
    public String[] convertToArray(){
        return new String[]{accNumber,name,phoneNumber,dob,address};
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
