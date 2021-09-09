package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ResultScreen extends javax.swing.JFrame {

    String[][] orderingDamage2;
    String DmgOrder="";
    String RollOrder="";
    String road;
    String setMade="";
    String assumindo="";
    
    public ResultScreen(String roll, String path) {
        initComponents();
        centralizarComponente();
        txtRoll.setText(roll);
        road = path;
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jButton1.setVisible(false);
        this.setSize(526, 100);
        txtRoll.revalidate();
        
        
    }
    
    public ResultScreen(String setmade,String roll,String assuming, String[][] orderingDamage, String path) {
        initComponents();
        road = path;
        RollOrder = roll;
        setMade = setmade;
        assumindo = assuming;
        centralizarComponente();
        txtRoll.setText(setmade+assuming+roll);
        orderingDamage2 = orderingDamage;
        for(int i=0; i < orderingDamage2.length; i++){
        for(int j=1; j < (orderingDamage2.length-i); j++){
            if (Integer.parseInt(orderingDamage2[j-1][0]) < Integer.parseInt(orderingDamage2[j][0])) {
                String[] aux = orderingDamage2[j-1];
                orderingDamage2[j-1] = orderingDamage2[j];
                orderingDamage2[j] = aux;
            }
        }
    }
        for (String[] currentrow : orderingDamage2) {
            DmgOrder += currentrow[1];
        }
        txtRoll.setCaretPosition(0);
        jRadioButton2.setSelected(true);
    }
    
    private void centralizarComponente() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRoll = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Damage Order");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Rolls Order");

        jButton1.setText("<html><center>Save Roll(s)<br>to a .txt</center></html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtRoll.setColumns(20);
        txtRoll.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        txtRoll.setRows(5);
        txtRoll.setTabSize(2);
        jScrollPane1.setViewportView(txtRoll);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jRadioButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jRadioButton2)
                            .addGap(0, 400, Short.MAX_VALUE)))
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        if(jRadioButton2.isSelected()){
            txtRoll.setText(setMade+assumindo+DmgOrder);
            txtRoll.setCaretPosition(0);
        }else{
            txtRoll.setText(setMade+assumindo+RollOrder);
            txtRoll.setCaretPosition(0);
        }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String filename = File.separator + "tmp";

        JFileChooser fc;
        if(!"".equals(road)){
            fc = new JFileChooser(road);
        }else{
            fc = new JFileChooser(new File(filename));
        }
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Normal text file (*.txt)", "txt"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.setMultiSelectionEnabled(false);
        fc.showSaveDialog(this);
        String filenome = fc.getSelectedFile().getName();
        File selFile;
        if (!filenome.contains(".txt")) {
            selFile = new File(fc.getSelectedFile() + ".txt");
            road = fc.getSelectedFile().getAbsolutePath();
            for(int k = road.length()-1;k >0;k--){
                if(road.charAt(k)=='\\'){
                    road = road.substring(0, k);
                    k = 0;
                }
            }
        } else {
            selFile = fc.getSelectedFile();
            road = fc.getSelectedFile().getAbsolutePath();
            for(int k = road.length()-1;k >0;k--){
                if(road.charAt(k)=='\\'){
                    road = road.substring(0, k);
                    k = 0;
                }
            }
        }

        try {
            try (FileWriter grava = new FileWriter(selFile, false); PrintWriter escreve = new PrintWriter(grava)) {

                escreve.println(txtRoll.getText());
            }
        } catch (IOException ex) {
            Logger.getLogger(ResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ResultScreen("","").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtRoll;
    // End of variables declaration//GEN-END:variables
}
