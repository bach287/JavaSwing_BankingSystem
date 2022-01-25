/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import frames.CustomerFrame;
import frames.DepositFrame;
import frames.WithdrawFrame;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Customer;

/**
 *
 * @author Admin
 */
public class CustomerService {
    public CustomerFrame customerFrame;
    public static List<Customer> customerList = new ArrayList<>();;
    private JTable customerTable;
    private static final DefaultTableModel model = new DefaultTableModel(new String[]{"Account Number","Name", "Phone Number", "Date of birth", "Address"}, 0);
    private static Customer selectedCustomer;
    private DepositFrame depositFrame = new DepositFrame(this);
    private WithdrawFrame withdrawFrame = new WithdrawFrame(this);
    

    public CustomerService(CustomerFrame customerFrame) {
        this.customerFrame = customerFrame;
    }

    public void updateOrCreateBtn(){
        int size = customerList.size();
        
        String accNumber = customerFrame.getCustomerAccNumber().getText(); //get text from fields
        String customerName = customerFrame.getCustomerNameField().getText();
        String customerDob = customerFrame.getCustomerDobField().getText();
        String customerAddress = customerFrame.getCustomerAddressField().getText();
        String customerPhoneNumber = customerFrame.getCustomerPhoneField().getText();
        if(accNumber.equals("") || customerName.equals("") || customerDob.equals("") || customerAddress.equals("") || customerPhoneNumber.equals("")){
            JOptionPane.showMessageDialog(customerFrame,"Please fill all fields or select a customer to update!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        for(int i=0; i<size; i++){
            String curAccNumber = customerList.get(i).getAccNumber().toLowerCase();
            //check weather this Customer is already exist
            if(curAccNumber.equals(accNumber.toLowerCase())){
                updateCustomer(i,customerName,customerDob,customerAddress,customerPhoneNumber);
                return;
            }
        }
        createCustomer(accNumber, customerName, customerDob, customerAddress, customerPhoneNumber);
    }
    
    public void showDepositWindow(){
        if(CustomerService.selectedCustomer == null){
            JOptionPane.showMessageDialog(customerFrame,"Please select a customer!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        depositFrame.getDepositTxtField().setText("");
        depositFrame.setVisible(true);
    }
    
    public void showWithdrawWindow(){
        if(CustomerService.selectedCustomer == null){
            JOptionPane.showMessageDialog(customerFrame,"Please select a customer!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        withdrawFrame.getWithdrawTxtField().setText("");
        withdrawFrame.setVisible(true);
    }
    
    
    public void withdrawBtn(){
        long amount;
        try{
            amount = Long.parseLong(withdrawFrame.getWithdrawTxtField().getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(customerFrame,"Invalid amount of money!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(selectedCustomer.withdraw(amount) == -1){
            JOptionPane.showMessageDialog(customerFrame,"Not enough money!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(customerFrame, "Withdraw Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        withdrawFrame.setVisible(false);
        customerFrame.getBalanceField().setText(String.valueOf(selectedCustomer.getBalance()));
    }
    
    public void depositBtn(){
        long amount;
        try{
            amount = Long.parseLong(depositFrame.getDepositTxtField().getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(customerFrame,"Invalid amount of money!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        selectedCustomer.deposit(amount);
        JOptionPane.showMessageDialog(customerFrame, "Deposit Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        depositFrame.setVisible(false);
        customerFrame.getBalanceField().setText(String.valueOf(selectedCustomer.getBalance()));
    }
    
    public void createCustomer(String accNumber, String accName, String accDob, String accAddress, String accPhone){
        int choice = JOptionPane.showConfirmDialog(null,"Do you want to create this customer?","Confirmation",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            String dateOfBirth = isDateValid(accDob);
            if(dateOfBirth == null){
                JOptionPane.showMessageDialog(customerFrame,"The input date is not valid, try again!","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                Customer customer = new Customer(accNumber, accName, accNumber, dateOfBirth, accAddress);
                customerList.add(customer);
                JOptionPane.showMessageDialog(customerFrame, "New account created!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    
    public void updateCustomer(int index, String name, String dob, String address, String phone){
        int choice = JOptionPane.showConfirmDialog(null,"Do you want to update this customer?","Confirmation",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            String dateOfBirth = isDateValid(dob);
            if(dateOfBirth == null){
                JOptionPane.showMessageDialog(customerFrame,"The input date is not valid, try again!","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                Customer customer = customerList.get(index);
                customer.setName(name);
                customer.setAddress(address);
                customer.setDob(dateOfBirth);
                customer.setPhoneNumber(phone);
                JOptionPane.showMessageDialog(customerFrame, "Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void deleteBtn(){
        if(CustomerService.selectedCustomer != null){
            int choice = JOptionPane.showConfirmDialog(null,"Do you want to delete this customer?","Confirmation",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                String accNumber = customerFrame.getCustomerAccNumber().getText();
                Customer c = findCustomer(accNumber);
                customerList.remove(c);
            }
        }else{
            JOptionPane.showMessageDialog(customerFrame,"Please select an account!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String isDateValid(String date){
        Date output;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        System.out.println(date);
        try {
            output = df.parse(date);
            System.out.println("in!" + output.toString());
        } catch (Exception e) {
            return null;
        }
        return df.format(output);
    }
    
        public void getSelectedRowData() {
        int selectedRow = customerTable.getSelectedRow();
        if(selectedRow >= 0){
            String slNumber = customerTable.getValueAt(selectedRow, 0).toString();
            String slName = customerTable.getValueAt(selectedRow, 1).toString();
            String slDob = customerTable.getValueAt(selectedRow, 3).toString();
            String slAddress = customerTable.getValueAt(selectedRow, 4).toString();
            String slPhone = customerTable.getValueAt(selectedRow, 2).toString();
            customerFrame.getCustomerAccNumber().setText(slNumber);
            customerFrame.getCustomerNameField().setText(slName);
            customerFrame.getCustomerDobField().setText(slDob);
            customerFrame.getCustomerAddressField().setText(slAddress);
            customerFrame.getCustomerPhoneField().setText(slPhone);
            Customer c = findCustomer(slNumber);
            CustomerService.selectedCustomer = c;
            customerFrame.getBalanceField().setText(String.valueOf(c.getBalance()));
        }
    }
        
    private Customer findCustomer(String accNumber){
        for(int i=0; i<customerList.size(); i++){
            Customer c = customerList.get(i);
            if(accNumber.equals(c.getAccNumber())){
                return customerList.get(i);
            }
        }
        return null;
    }
    
    public void backFromCustomer(){
        App.mainFrame.setVisible(true);
        customerFrame.setVisible(false);
    }
    

    public void initTable() {
        int size = customerList.size();
        customerTable = customerFrame.getCustomerTable();
        model.setRowCount(0);
        if(size != 0){
            for(int i=0; i<size; i++){
                model.addRow(customerList.get(i).convertToArray());
            }
        }
            customerTable.setModel(model);
        }
}
