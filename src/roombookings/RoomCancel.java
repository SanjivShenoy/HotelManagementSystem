
package roombookings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manish
 */
public class RoomCancel extends javax.swing.JFrame {

    DefaultTableModel model;
    Connection c;
    Statement st;
    ResultSet rs;
    Date inDate;
    /**
     * Creates new form RoomCancel
     */
    public RoomCancel() {
        initComponents();
        buildTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        homeBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        modifyBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking Id", "Hotel Name", "In Date", "Out Date", "Room Type", "Number of Rooms", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setRowHeight(40);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Cancel / Modify Room Booking");

        homeBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        homeBtn.setText("Home");
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancelBtn.setText("Cancel Booking");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        modifyBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        modifyBtn.setText("Modify Dates");
        modifyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257)
                .addComponent(modifyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
       try{
        rs.close();
        st.close();
        c.close();
       } catch(Exception e) {
           System.out.println("Closing Failure!");
       }
        System.exit(0);
    }//GEN-LAST:event_homeBtnActionPerformed

    private void modifyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyBtnActionPerformed

        Date cur = new Date();
        SimpleDateFormat fd = new SimpleDateFormat("d MMM, yyyy");
        
        int selRow = table.getSelectedRow();
        if(selRow>= 0) {
            String d = table.getValueAt(selRow, 2).toString();
            try{
                inDate = fd.parse(d);
            } catch (ParseException e) {
                System.out.println("Parsing error");
            }
            long t = inDate.getTime() - cur.getTime();
            if(t>=86400*1000*2) {
                if(selRow >= 0) {
                    String s = table.getValueAt(selRow,0).toString();
                        System.out.println(s);
                        new ModifyPage(s).setVisible(true);
                        setVisible(false);
                        try{
                        rs.close();
                        st.close();
                        c.close();
                        } catch(Exception e) {
                            System.out.println("Closing Failure!");
                         }
                }
                else {
                    System.out.println("Select a Row");
                    JOptionPane.showMessageDialog(null, "Select a Row", "WARNING!", JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Too late to Modify Dates. Please try Cancel Booking option.", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
         else {
            System.out.println("Select a Row");
            JOptionPane.showMessageDialog(null, "Select a Row", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_modifyBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        int selRow = table.getSelectedRow();
        //System.out.println(selRow);
        if(selRow >= 0) {
            try {
                String s = table.getValueAt(selRow,0).toString();
                System.out.println(s);
                new CancelPage(s).setVisible(true);
                setVisible(false);
                try{
                rs.close();
                st.close();
                c.close();
               } catch(Exception e) {
                   System.out.println("Closing Failure!");
               }
                    } catch(Exception e) {
                        System.out.println("Delete Failed");
                    }
            //model.removeRow(selRow);
        }
        else {
            System.out.println("Select a Row");
            JOptionPane.showMessageDialog(null, "Select a Row", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }//GEN-LAST:event_cancelBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoomCancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomCancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomCancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomCancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomCancel().setVisible(true);
            }
        });
    }
    
    public void buildTable () {
      c = null;
      st = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "manish");
     
         c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        st = c.createStatement();
        
        rs = st.executeQuery("SELECT * FROM roombookings;");
        
        while(rs.next()) {
            int b_id = rs.getInt("booking_id");
            String hotel = rs.getString("hotel_name");
            String in_date = rs.getString("in_date");
            String out_date = rs.getString("out_date");
            int roomtype = rs.getInt("room_type");
            int no_rooms = rs.getInt("number_of_rooms");
            int price = rs.getInt("price");

            Object row[] = {b_id,hotel,in_date,out_date,roomtype,no_rooms,price};
            model = (DefaultTableModel) table.getModel();  
            model.addRow(row);
        }
   } catch (Exception e) {
       e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         //System.exit(0);
    }
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyBtn;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
