package gui;

import configuration.Options;
import io.ReadFile;
import io.SaveFile;
import io.WriteFile;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This class gives a window to the user, allowing he to change
 * all the dependencies options to personalize Joker to its locations.
 * @author Diego Henrique Oliveira de Souza
 */
public class OptionsWindow extends javax.swing.JFrame {

    private Options op = JokerWindow.getOptions();

    public OptionsWindow() {

        try {

            File testFile = new File("configuration.joker");
            if (!testFile.exists()) {
                testFile.createNewFile();
            } else {
                initComponents();
                this.setLocationRelativeTo(null);
                String config = ReadFile.leia("./configuration.joker");
                if (!config.isEmpty()) {
                    JokerWindow.logIt("\n\nCONFIGURATION GUI: \n" + config);
                    String partes[] = config.split(";");

                    String cspmFull = partes[0];
                    String probcliFull = partes[1];

                    String cspm = cspmFull.substring(cspmFull.indexOf("=") + 1);
                    String probcli = probcliFull.substring(probcliFull.indexOf("=") + 1);

                    this.cspmFolder.setText(cspm);
                    this.probcliFolder.setText(probcli);
                }
            }

            setIconImage(new ImageIcon(getClass().getResource("/img/jokerLogo.png")).getImage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "FILE ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        colorAtOpening = new javax.swing.JCheckBox();
        enableCopyPaste = new javax.swing.JCheckBox();
        enableUndo = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cspmFolder = new javax.swing.JTextField();
        probcliFolder = new javax.swing.JTextField();
        locateCspmFolder = new javax.swing.JButton();
        locateProbcliFolder = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        enableUndo1 = new javax.swing.JCheckBox();
        enableCopyPaste1 = new javax.swing.JCheckBox();
        enableCopyPaste2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        colorAtOpening.setSelected(true);
        colorAtOpening.setText("Colored Specification");
        colorAtOpening.setEnabled(false);

        enableCopyPaste.setSelected(true);
        enableCopyPaste.setText("Enable Ctrl+C");
        enableCopyPaste.setEnabled(false);

        enableUndo.setSelected(true);
        enableUndo.setText("Enable Ctrl+X");
        enableUndo.setEnabled(false);

        jLabel1.setText("CSPM Folder:");

        jLabel2.setText("ProbCli Folder: ");

        cspmFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cspmFolderActionPerformed(evt);
            }
        });

        locateCspmFolder.setText("Locate");
        locateCspmFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locateCspmFolderActionPerformed(evt);
            }
        });

        locateProbcliFolder.setText("Locate");
        locateProbcliFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locateProbcliFolderActionPerformed(evt);
            }
        });

        OK.setText("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(362, Short.MAX_VALUE)
                .addComponent(OK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancel)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        enableUndo1.setSelected(true);
        enableUndo1.setText("Generate XML for LTS");
        enableUndo1.setEnabled(false);

        enableCopyPaste1.setSelected(true);
        enableCopyPaste1.setText("Enable Ctrl+V");
        enableCopyPaste1.setEnabled(false);

        enableCopyPaste2.setSelected(true);
        enableCopyPaste2.setText("Enable Ctrl+Z");
        enableCopyPaste2.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(probcliFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locateProbcliFolder))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cspmFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locateCspmFolder))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colorAtOpening)
                            .addComponent(enableUndo1)
                            .addComponent(enableCopyPaste2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enableCopyPaste1)
                            .addComponent(enableCopyPaste)
                            .addComponent(enableUndo))))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cspmFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locateCspmFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(probcliFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locateProbcliFolder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorAtOpening)
                    .addComponent(enableCopyPaste))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enableUndo1)
                    .addComponent(enableCopyPaste1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enableUndo)
                    .addComponent(enableCopyPaste2))
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        try {

            File testFile = new File("configuration.joker");
            if (!testFile.exists()) {
                testFile.createNewFile();
            }

            String config = ReadFile.leia("./configuration.joker");
            String partes[] = config.split(";");

            WriteFile.escreva("./configuration.joker",
                    "cspm=" + cspmFolder.getText() +
                    ";\nprobcli=" + probcliFolder.getText() + ";" +
                    partes[2] + ";" +
                    partes[3] + ";" +
                    partes[4] + ";" +
                    partes[5]);
            this.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "FILE ERROR", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_OKActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
}//GEN-LAST:event_cancelActionPerformed

    private void locateCspmFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locateCspmFolderActionPerformed
        String cspm = SaveFile.locateFile();
        //cspm = cspm.replaceAll("\\" , "/");
        cspmFolder.setText(cspm);
    }//GEN-LAST:event_locateCspmFolderActionPerformed

    private void locateProbcliFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locateProbcliFolderActionPerformed
        String probcli = SaveFile.locateFile();
        //cspm = cspm.replaceAll("\\" , "/");
        probcliFolder.setText(probcli);
    }//GEN-LAST:event_locateProbcliFolderActionPerformed

    private void cspmFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cspmFolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cspmFolderActionPerformed
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Configuracoes().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton cancel;
    private javax.swing.JCheckBox colorAtOpening;
    private javax.swing.JTextField cspmFolder;
    private javax.swing.JCheckBox enableCopyPaste;
    private javax.swing.JCheckBox enableCopyPaste1;
    private javax.swing.JCheckBox enableCopyPaste2;
    private javax.swing.JCheckBox enableUndo;
    private javax.swing.JCheckBox enableUndo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton locateCspmFolder;
    private javax.swing.JButton locateProbcliFolder;
    private javax.swing.JTextField probcliFolder;
    // End of variables declaration//GEN-END:variables
}
