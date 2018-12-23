package gui;

import configuration.Options;
import io.ReadFile;
import io.WriteFile;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This class gives a window to the user, allowing he to change
 * all graphics options, including, size, images, and types.
 * @author Diego Henrique Oliveira de Souza
 */
public class GraphicsWindow extends javax.swing.JFrame {

    private Options op = JokerWindow.getOptions();
    public static String graphType = "";

    public static String getGraphHeight() {
        return graphHeight;
    }

    public static String getGraphImg() {
        return graphImg;
    }

    public static String getGraphType() {
        return graphType;
    }

    public static String getGraphWidth() {
        return graphWidth;
    }
    public static String graphImg = "";
    public static String graphWidth = "";
    public static String graphHeight = "";

    public GraphicsWindow() {

        try {
            File testFile = new File("configuration.joker");
            if (!testFile.exists()) {
                testFile.createNewFile();
            } else {
                initComponents();
                this.setLocationRelativeTo(null);
                String config = ReadFile.leia("./configuration.joker");
                if (!config.isEmpty()) {
                    JokerWindow.logIt("\nCONFIGURATION GRAPH: \n" + config);

                    String partes[] = config.split(";");

                    graphType = partes[2].substring(partes[2].indexOf("=") + 1);
                    graphImg = partes[3].substring(partes[3].indexOf("=") + 1);
                    graphWidth = partes[4].substring(partes[4].indexOf("=") + 1);
                    graphHeight = partes[5].substring(partes[5].indexOf("=") + 1);

                    graphTypeCombo.setSelectedItem(graphType);
                    nodeImageCombo.setSelectedItem(graphImg);
                    nodeWidthCombo.setSelectedItem(graphWidth);
                    nodeHeightCombo.setSelectedItem(graphHeight.trim());

//                    System.out.println("[2] = " + graphLibrary);
//                    System.out.println("[3] = " + nodeBody);
//                    System.out.println("[4] = " + graphWidth);
//                    System.out.println("[5] = " + nodeSize);
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
        jPanel4 = new javax.swing.JPanel();
        OK = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        graphTypeCombo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nodeWidthCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        nodeImageCombo = new javax.swing.JComboBox();
        nodeHeightCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Graphics"));

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
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(OK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        graphTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Boxes", "Ballons", "Images" }));

        jLabel1.setText("Graph Type:");

        jLabel2.setText("Graph Width:");

        nodeWidthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "300", "400", "500", "600", "700", "800" }));
        nodeWidthCombo.setSelectedIndex(2);

        jLabel3.setText("Node Image:");

        nodeImageCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tux", "cog", "ok", "bomb", "flag", "world", "clock", "bug" }));

        nodeHeightCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "300", "400", "500", "600", "700", "800" }));
        nodeHeightCombo.setSelectedIndex(2);

        jLabel4.setText("Graph Height:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nodeImageCombo, 0, 115, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(graphTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nodeHeightCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, 115, Short.MAX_VALUE)
                    .addComponent(nodeWidthCombo, 0, 115, Short.MAX_VALUE)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {graphTypeCombo, nodeImageCombo, nodeWidthCombo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(graphTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nodeImageCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nodeWidthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nodeHeightCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

            graphType = graphTypeCombo.getSelectedItem().toString();
            graphImg = nodeImageCombo.getSelectedItem().toString();
            graphWidth = nodeWidthCombo.getSelectedItem().toString();
            graphHeight = nodeHeightCombo.getSelectedItem().toString();

            WriteFile.escreva("./configuration.joker",
                    partes[0] + ";"
                    + partes[1] + ";\n"
                    + "graphType="   + graphType  + ";\n"
                    + "graphImg="    + graphImg   + ";\n"
                    + "graphWidth="  + graphWidth + ";\n"
                    + "graphHeight=" + graphHeight);
            this.dispose();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "FILE ERROR", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_OKActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
}//GEN-LAST:event_cancelActionPerformed
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
    private javax.swing.JComboBox graphTypeCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox nodeHeightCombo;
    private javax.swing.JComboBox nodeImageCombo;
    private javax.swing.JComboBox nodeWidthCombo;
    // End of variables declaration//GEN-END:variables
}
