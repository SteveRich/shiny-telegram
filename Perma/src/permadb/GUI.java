/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permadb;

import javax.swing.JFileChooser;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Firma
 */
public class GUI extends javax.swing.JFrame {

    Connection SQL;
    boolean first = true; // ugh, figure a way aroud this global
    String query = "select * from plants"
            + " inner join plantzones on plants.plantid = plantzones.plantid ";

    /*
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
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

        jLabel1 = new javax.swing.JLabel();
        dbText = new javax.swing.JTextField();
        dbBrowseBTN = new javax.swing.JButton();
        openBTN = new javax.swing.JButton();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        habitatList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Database");

        dbBrowseBTN.setText("Browse");
        dbBrowseBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbBrowseBTNActionPerformed(evt);
            }
        });

        openBTN.setText("Open");
        openBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBTNActionPerformed(evt);
            }
        });

        resultList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
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
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
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
                .addContainerGap(473, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Habitat, Region", jPanel3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("more more params", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dbText)
                        .addGap(18, 18, 18)
                        .addComponent(dbBrowseBTN)
                        .addGap(18, 18, 18)
                        .addComponent(openBTN))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dbText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbBrowseBTN)
                    .addComponent(openBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dbBrowseBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbBrowseBTNActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("Database: "
                    + chooser.getSelectedFile().getName());
            dbText.setText(chooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_dbBrowseBTNActionPerformed

    private void openBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBTNActionPerformed
        try {
            SQL = openConnection(dbText.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_openBTNActionPerformed

    private void habitatListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_habitatListValueChanged
        if (evt.getValueIsAdjusting()) {
            try {
                updateResults();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_habitatListValueChanged

    private void moistureListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_moistureListValueChanged
        if (evt.getValueIsAdjusting()) {
            try {
                updateResults();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_moistureListValueChanged

    private void lightListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lightListValueChanged
        if (evt.getValueIsAdjusting()) {
            try {
                updateResults();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_lightListValueChanged

    private void zoneListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_zoneListValueChanged
        if (evt.getValueIsAdjusting()) {
            try {
                updateResults();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_zoneListValueChanged

    void initLists() {
        zoneList.setListData(new String[]{"Zone 2", "Zone 3", "Zone 4", "Zone 5", "Zone 6", "Zone 7", "Zone 8"});
        lightList.setListData(new String[]{"Full Sun", "Partial Shade", "Shade"});
        moistureList.setListData(new String[]{"Xeric", "Mesic", "Hydric"});
        habitatList.setListData(new String[]{"Prairies", "Gaps/Clearings", "Open Woods", "Forest", "Disturbed", "Meadows", "Old Fields", "Edges", "Conifer Forests", "Thickets"});
    }

    void updateResults() throws SQLException {
        List<String> output = new ArrayList<>();
        String query = getParams();
        PreparedStatement SQLquery = SQL.prepareStatement(query);
        ResultSet results = SQLquery.executeQuery();
        while (results.next()) {
            output.add(results.getString("genus") + " " + results.getString("species"));
        }
        String[] listData = new String[]{};
        listData = output.toArray(listData);
        resultList.setListData(listData);

    }

    StringBuilder appendParams(StringBuilder input, String[] inputArray) {
        for (String value : inputArray) {
            if (first) {
                input.append(" WHERE ").append(value);
                first = false;
            } else {
                input.append(" AND ").append(value);
            }
        }
        return input;
    }

    // Struct: input[0] inner join alias, input[1] where conditionals
    StringBuilder[] appendParamsArr(StringBuilder input[], String[] inputArray, String table) {
        int counter = 0;
        for (String join : inputArray) {
            input[0].append(" INNER JOIN " + table + " " + table + counter + " on plants.plantid = " + table + counter + ".plantid");
            if (first) {
                input[1].append(" WHERE " + table + counter).append(join);
                first = false;
            } else {
                input[1].append(" AND "+ table + counter).append(join);
            }
            counter++;
        }
        return input;
    }

    String[] getParamArray() {
        String[] params = new String[2];

        return params;
    }

    String getParams() {
        first = true; // reset global, determines where/or in query
        String nameType = "genus, species ";
        StringBuilder query = new StringBuilder("Select " + nameType + " from plants ");
       //         + " inner join plantzones on plants.plantid = plantzones.plantid "
       //         + " inner join light on plants.plantid = light.plantid "
       //         + " inner join moisture on plants.plantid = moisture.plantid"
       //         + " inner join habitat on plants.plantid = habitat.plantid");

        StringBuilder[] queryArr = new StringBuilder[2];
        queryArr[0] = query;
        queryArr[1] = new StringBuilder();
        
        queryArr = appendParamsArr(queryArr,getZoneParams(),"plantzones");
        queryArr = appendParamsArr(queryArr,getLightParams(),"light");
        queryArr = appendParamsArr(queryArr,getMoistureParams(),"moisture");
        queryArr = appendParamsArr(queryArr,getHabitatParams(),"habitat");
        /*
        query = appendParams(query, getZoneParams());
        query = appendParams(query, getLightParams());
        query = appendParams(query, getMoistureParams());
        query = appendParams(query, getHabitatParams());

        query.append(" order by plants.plantid");
       
        System.out.println(query.toString());
        return query.toString();
         */
        System.out.println(queryArr[0].toString() + " " + queryArr[1].toString());
        return queryArr[0].toString() + " " + queryArr[1].toString();
    }

    String[] getMoistureParams() {
        String[] moisture = {"Xeric", "Mesic", "Hydric"};
        int[] moistureIndex = moistureList.getSelectedIndices();
        String[] output = new String[moistureIndex.length];
        for (int i = 0; i < moistureIndex.length; i++) {
            output[i] = ".moistureType = '" + moisture[moistureIndex[i]] + "'";
        }
        return output;
    }

    String[] getZoneParams() {
        int[] zones = {2, 3, 4, 5, 6, 7, 8};
        int[] zoneIndex = zoneList.getSelectedIndices();
        String[] output = new String[zoneIndex.length];
        for (int i = 0; i < zoneIndex.length; i++) {
            output[i] = ".zone = " + zones[zoneIndex[i]];
        }
        return output;
    }

    String[] getLightParams() {
        String[] light = {"F", "P", "S"};
        int[] lightIndex = lightList.getSelectedIndices();
        String[] output = new String[lightIndex.length];
        for (int i = 0; i < lightIndex.length; i++) {
            output[i] = ".lightType = '" + light[lightIndex[i]] + "'";
        }
        return output;
    }

    String[] getHabitatParams() {
        String[] habitat = {"Prairies", "Gaps/Clearings", "Open Woods", "Forest", "Disturbed", "Meadows", "Old Fields", "Edges", "Conifer Forests", "Thickets"};
        int[] habitatIndex = habitatList.getSelectedIndices();
        String[] output = new String[habitatIndex.length];
        for (int i = 0; i < habitatIndex.length; i++) {
            output[i] = ".habitat = '" + habitat[habitatIndex[i]] + "'";
        }
        return output;
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dbBrowseBTN;
    private javax.swing.JTextField dbText;
    private javax.swing.JList<String> habitatList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JList<String> moistureList;
    private javax.swing.JButton openBTN;
    private javax.swing.JList<String> resultList;
    private javax.swing.JList<String> zoneList;
    // End of variables declaration//GEN-END:variables
}
