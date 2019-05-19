package permadb;

import javax.swing.JFileChooser;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Steve Rich
 */
public class GUI extends javax.swing.JFrame {

    Connection SQL;
    Core permaCore;
    ArrayList<javax.swing.JList> lists;
    ArrayList<javax.swing.JCheckBox> boxList;

    /*
     * Creates new form GUI
     */
    public GUI() {
        initComponents();

        lists = getListBoxes();
        boxList = getCheckBoxes();
        permaCore = new Core(SQL = openConnection());

        initLists();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        n2Group = new javax.swing.ButtonGroup();
        mineralGroup = new javax.swing.ButtonGroup();
        invertGroup = new javax.swing.ButtonGroup();
        groundCoverGroup = new javax.swing.ButtonGroup();
        poisonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        zoneList = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        lightList = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        moistureList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        n2NoBTN = new javax.swing.JCheckBox();
        n2YesBTN = new javax.swing.JCheckBox();
        mineralYesBTN = new javax.swing.JCheckBox();
        mineralNoBTN = new javax.swing.JCheckBox();
        invertYesBTN = new javax.swing.JCheckBox();
        invertNoBTN = new javax.swing.JCheckBox();
        groundCoverYesBTN = new javax.swing.JCheckBox();
        groundCoverNoBTN = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        poisonYesBTN = new javax.swing.JCheckBox();
        poisonNoBTN = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        habitatList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resultList.setToolTipText("");
        jScrollPane1.setViewportView(resultList);

        zoneList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                zoneListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(zoneList);

        lightList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lightListValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(lightList);

        moistureList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                moistureListValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(moistureList);

        jLabel5.setText("Zone");

        jLabel6.setText("Light");

        jLabel7.setText("Moisture");

        jLabel1.setText("Yes");

        jLabel3.setText("No");

        jLabel4.setText("N2 Fixer");

        n2Group.add(n2NoBTN);
        n2NoBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                n2NoBTNItemStateChanged(evt);
            }
        });

        n2Group.add(n2YesBTN);
        n2YesBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                n2YesBTNItemStateChanged(evt);
            }
        });

        mineralGroup.add(mineralYesBTN);
        mineralYesBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mineralYesBTNItemStateChanged(evt);
            }
        });

        mineralGroup.add(mineralNoBTN);
        mineralNoBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mineralNoBTNItemStateChanged(evt);
            }
        });

        invertGroup.add(invertYesBTN);
        invertYesBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                invertYesBTNItemStateChanged(evt);
            }
        });

        invertGroup.add(invertNoBTN);
        invertNoBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                invertNoBTNItemStateChanged(evt);
            }
        });

        groundCoverGroup.add(groundCoverYesBTN);
        groundCoverYesBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groundCoverYesBTNItemStateChanged(evt);
            }
        });

        groundCoverGroup.add(groundCoverNoBTN);
        groundCoverNoBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groundCoverNoBTNItemStateChanged(evt);
            }
        });

        jLabel8.setText("Mineral Accum");

        jLabel9.setText("Invert Shelter");

        jLabel10.setText("Ground Cover");

        poisonGroup.add(poisonYesBTN);
        poisonYesBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                poisonYesBTNItemStateChanged(evt);
            }
        });

        poisonGroup.add(poisonNoBTN);
        poisonNoBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                poisonNoBTNItemStateChanged(evt);
            }
        });

        jLabel11.setText("Poison");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(n2YesBTN))
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(n2NoBTN)
                                    .addComponent(jLabel3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mineralYesBTN)
                                .addGap(18, 18, 18)
                                .addComponent(mineralNoBTN))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invertYesBTN)
                                .addGap(18, 18, 18)
                                .addComponent(invertNoBTN)))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(poisonYesBTN)
                                .addGap(18, 18, 18)
                                .addComponent(poisonNoBTN))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(groundCoverYesBTN)
                                .addGap(18, 18, 18)
                                .addComponent(groundCoverNoBTN)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(n2YesBTN)
                            .addComponent(n2NoBTN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mineralYesBTN)
                            .addComponent(mineralNoBTN)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invertYesBTN)
                            .addComponent(invertNoBTN)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(groundCoverYesBTN)
                            .addComponent(groundCoverNoBTN)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(poisonYesBTN)
                            .addComponent(poisonNoBTN)
                            .addComponent(jLabel11))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Zone, Light, Moisture", jPanel2);

        habitatList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                habitatListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(habitatList);

        jLabel2.setText("Habitat");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(417, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Habitat, Region", jPanel3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("more more params", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void habitatListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_habitatListValueChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_habitatListValueChanged

    private void moistureListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_moistureListValueChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_moistureListValueChanged

    private void lightListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lightListValueChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_lightListValueChanged

    private void zoneListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_zoneListValueChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_zoneListValueChanged

    private void n2YesBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_n2YesBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_n2YesBTNItemStateChanged

    private void n2NoBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_n2NoBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_n2NoBTNItemStateChanged

    private void mineralYesBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mineralYesBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_mineralYesBTNItemStateChanged

    private void mineralNoBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mineralNoBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_mineralNoBTNItemStateChanged

    private void invertYesBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_invertYesBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_invertYesBTNItemStateChanged

    private void invertNoBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_invertNoBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_invertNoBTNItemStateChanged

    private void groundCoverYesBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groundCoverYesBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_groundCoverYesBTNItemStateChanged

    private void groundCoverNoBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groundCoverNoBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_groundCoverNoBTNItemStateChanged

    private void poisonYesBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_poisonYesBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_poisonYesBTNItemStateChanged

    private void poisonNoBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_poisonNoBTNItemStateChanged
        permaCore.updateResults(lists, boxList);
    }//GEN-LAST:event_poisonNoBTNItemStateChanged

    // Sets default values for all list boxes
    void initLists() {
        try {
            zoneList.setListData(permaCore.getListValues("zone"));
            lightList.setListData(permaCore.getListValues("light"));
            moistureList.setListData(permaCore.getListValues("moisture"));
            habitatList.setListData(permaCore.getListValues("habitat"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Returns list of all listboxes to pass to core
    ArrayList<javax.swing.JList> getListBoxes() {
        ArrayList<javax.swing.JList> output = new ArrayList<javax.swing.JList>();
        output.add(lightList);
        output.add(zoneList);
        output.add(moistureList);
        output.add(habitatList);
        output.add(resultList);
        return output;
    }

    //  Returns list of all checkboxes to pass to core
    ArrayList<javax.swing.JCheckBox> getCheckBoxes() {
        ArrayList<javax.swing.JCheckBox> boxList = new ArrayList<javax.swing.JCheckBox>();
        boxList.add(n2YesBTN);
        boxList.add(n2NoBTN);
        boxList.add(mineralYesBTN);
        boxList.add(mineralNoBTN);
        boxList.add(invertYesBTN);
        boxList.add(invertNoBTN);
        boxList.add(groundCoverYesBTN);
        boxList.add(groundCoverNoBTN);
        boxList.add(poisonYesBTN);
        boxList.add(poisonNoBTN);
        return boxList;
    }

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    Connection openConnection(String database) throws SQLException {

        String url = "jdbc:sqlite:" + database;
        return DriverManager.getConnection(url);

    }

    // Returns embedded DB, handles catch internally
    Connection openConnection() {
        Connection SQL = null;
        try {
            String url = "jdbc:sqlite::resource:permaDB.db";
            SQL = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return SQL;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup groundCoverGroup;
    private javax.swing.JCheckBox groundCoverNoBTN;
    private javax.swing.JCheckBox groundCoverYesBTN;
    private javax.swing.JList<String> habitatList;
    private javax.swing.ButtonGroup invertGroup;
    private javax.swing.JCheckBox invertNoBTN;
    private javax.swing.JCheckBox invertYesBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> lightList;
    private javax.swing.ButtonGroup mineralGroup;
    private javax.swing.JCheckBox mineralNoBTN;
    private javax.swing.JCheckBox mineralYesBTN;
    private javax.swing.JList<String> moistureList;
    private javax.swing.ButtonGroup n2Group;
    private javax.swing.JCheckBox n2NoBTN;
    private javax.swing.JCheckBox n2YesBTN;
    private javax.swing.ButtonGroup poisonGroup;
    private javax.swing.JCheckBox poisonNoBTN;
    private javax.swing.JCheckBox poisonYesBTN;
    private javax.swing.JList<String> resultList;
    private javax.swing.JList<String> zoneList;
    // End of variables declaration//GEN-END:variables
}
