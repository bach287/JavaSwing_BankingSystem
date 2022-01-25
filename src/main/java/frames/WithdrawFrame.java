/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import controllers.CustomerService;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class WithdrawFrame extends javax.swing.JFrame {

    /**
     * Creates new form WithdrawFrame
     */
    
    CustomerService customerService;
    
    public WithdrawFrame(CustomerService customerService) {
        this.customerService = customerService;
        initComponents();
    }
    
    public WithdrawFrame() {
        initComponents();
    }

    public JTextField getWithdrawTxtField() {
        return withdrawTxtField;
    }

    public void setWithdrawTxtField(JTextField withdrawTxtField) {
        this.withdrawTxtField = withdrawTxtField;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        withdrawTxtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        withdrawBtn = new javax.swing.JButton();

        setResizable(false);

        withdrawTxtField.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        withdrawTxtField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        withdrawTxtField.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("VNĐ");

        withdrawBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        withdrawBtn.setText("Withdraw");
        withdrawBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(withdrawTxtField)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(withdrawBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(withdrawTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(withdrawBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void withdrawBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawBtnActionPerformed
        // TODO add your handling code here:
        customerService.withdrawBtn();
    }//GEN-LAST:event_withdrawBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton withdrawBtn;
    private javax.swing.JTextField withdrawTxtField;
    // End of variables declaration//GEN-END:variables
}
