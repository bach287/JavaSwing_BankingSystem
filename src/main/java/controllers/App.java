/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import frames.BranchFrame;
import frames.CustomerFrame;
import frames.DepositFrame;
import frames.ExBankingFrame;

/**
 *
 * @author Admin
 */
public class App {
    //Frame declare
    public static ExBankingFrame mainFrame;
    public static BranchFrame branchFrame = new BranchFrame();
    public static CustomerFrame customerFrame = new CustomerFrame();

    //Arrays of Objects
    //Branch's Components
    
    public static void main(String[] args) {
        mainFrame = new ExBankingFrame();
        mainFrame.setVisible(true);
    }
    
    public void showBranchFrame(){
        branchFrame.setVisible(true);
        mainFrame.setVisible(false);
    }
    
    public void showCustomerFrame(){
        customerFrame.setVisible(true);
        mainFrame.setVisible(false);
    }
}
