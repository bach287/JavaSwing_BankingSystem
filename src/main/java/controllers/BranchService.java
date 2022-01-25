/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import frames.BranchFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Branch;

/**
 *
 * @author Admin
 */
public class BranchService {
    private final BranchFrame branchFrame;
    private static final List<Branch> branchList = new ArrayList<>();
    private JTable branchTable;
    private static final DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Location"}, 0);

    public BranchService(BranchFrame branchFrame) {
        this.branchFrame = branchFrame;
    }
    
    //update or create button action call
    public void updateCreateBtn(){
        int size = branchList.size();
        String branchNameTxt = branchFrame.getBranchNameField().getText(); //get text from fields
        String branchLocationTxt = branchFrame.getLocationField().getText();
        if(branchLocationTxt.equals("") || branchNameTxt.equals("")){
            JOptionPane.showMessageDialog(branchFrame,"Please enter both name and location or select a branch to update!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //create if no item in array
        if(size == 0){
            createNewBranch(branchNameTxt,branchLocationTxt);
        }else{
            for(int i=0; i<size; i++){ //if there are no existed branch, create new
                String curBranchName = branchList.get(i).getName().toLowerCase();
                //check weather this branch is already exist
                if(curBranchName.equals(branchNameTxt.toLowerCase())){
                    updateBranch(i,branchLocationTxt);
                    return;
                }
            }
            createNewBranch(branchNameTxt,branchLocationTxt);
        }
    }
    
    public void updateBranch(int index, String location){
        int choice = JOptionPane.showConfirmDialog(null,"Do you want to update this branch?","Confirmation",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            branchList.get(index).setLocation(location);
            JOptionPane.showMessageDialog(branchFrame, "Updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void createNewBranch(String name, String location){
        Branch newBranch = new Branch(name, location);
        branchList.add(newBranch);
        System.out.println(branchList.size());
        JOptionPane.showMessageDialog(branchFrame,"Create new branch successfully!","Confirmation",JOptionPane.CLOSED_OPTION);
    }
    
    public void initTable(){
        int size = branchList.size();
        branchTable = branchFrame.getBranchTable();
        model.setRowCount(0);
        if(size != 0){
            for(int i=0; i<size; i++){
                model.addRow(branchList.get(i).convertToArray());
            }
        }
        branchTable.setModel(model);
    }
    
    public void getSelectedRowData() {
        int selectedRow = branchTable.getSelectedRow();
        if(selectedRow >= 0){
            String name = branchTable.getValueAt(selectedRow, 0).toString();
            String location = branchTable.getValueAt(selectedRow, 1).toString();
            branchFrame.getBranchNameField().setText(name);
            branchFrame.getLocationField().setText(location);
        }
    }
    
    public void deleteSelectedRow(){
        int selectedRow = branchTable.getSelectedRow();
        int choice = JOptionPane.showConfirmDialog(null,"Do you want to detlete this branch?","Confirmation",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
        if(selectedRow >= 0 && choice == JOptionPane.YES_OPTION){
            String name = branchTable.getValueAt(selectedRow, 0).toString();
            branchList.remove(selectedRow);
        }
    }
    
    public void backFromBranch(){
        App.mainFrame.setVisible(true);
        branchFrame.setVisible(false);
    }
}
