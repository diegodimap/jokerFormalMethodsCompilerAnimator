package gui;

import configuration.SoundPaths;
import io.SoundFile;
import javax.swing.ImageIcon;

/**
 * In this window the user can change the error sounds.
 * @author Diego Henrique Oliveira de Souza
 */
public class SoundsWindow extends javax.swing.JFrame {

    private String caminho = "";
    private SoundPaths sp = JokerWindow.getSoundPaths();

    public SoundsWindow() {
        initComponents();

        lexicalSoundPath.setText(sp.getLexicalSoundPath());
        syntacticSoundPath.setText(sp.getSyntacticSoundPath());
        semanticSoundPath.setText(sp.getSemanticSoundPath());
        xmlSoundPath.setText(sp.getXmlSoundPath());
        javaSoundPath.setText(sp.getJavaSoundPath());

        //ícone do programa
        setIconImage(new ImageIcon(getClass().getResource("/img/jokerLogo.png")).getImage());
        this.setTitle("Choose the error sounds for the fases");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lexicalSoundPath = new javax.swing.JTextField();
        semanticSoundPath = new javax.swing.JTextField();
        xmlSoundPath = new javax.swing.JTextField();
        javaSoundPath = new javax.swing.JTextField();
        syntacticSoundPath = new javax.swing.JTextField();
        lexFind = new javax.swing.JButton();
        synFind = new javax.swing.JButton();
        semFind = new javax.swing.JButton();
        xmlFind = new javax.swing.JButton();
        javFind = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Lexical");

        jLabel2.setText("Syntactic");

        jLabel3.setText("Semantic");

        jLabel4.setText("XML");

        jLabel5.setText("Java");

        lexFind.setText("Choose");
        lexFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lexFindActionPerformed(evt);
            }
        });

        synFind.setText("Choose");
        synFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                synFindActionPerformed(evt);
            }
        });

        semFind.setText("Choose");
        semFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semFindActionPerformed(evt);
            }
        });

        xmlFind.setText("Choose");
        xmlFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlFindActionPerformed(evt);
            }
        });

        javFind.setText("Choose");
        javFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                javFindActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lexicalSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lexFind))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xmlSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xmlFind))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(javaSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(javFind))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semanticSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semFind))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(syntacticSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(synFind)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(ok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {javaSoundPath, lexicalSoundPath, semanticSoundPath, syntacticSoundPath, xmlSoundPath});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancel, javFind, lexFind, ok, semFind, synFind, xmlFind});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lexicalSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lexFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(syntacticSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(synFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(semanticSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(xmlSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xmlFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(javaSoundPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(javFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(ok)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void lexFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lexFindActionPerformed
        caminho = SoundFile.lerArquivo(".");
        lexicalSoundPath.setText(caminho);
    }//GEN-LAST:event_lexFindActionPerformed

    private void synFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_synFindActionPerformed
        caminho = SoundFile.lerArquivo(".");
        syntacticSoundPath.setText(caminho);
    }//GEN-LAST:event_synFindActionPerformed

    private void semFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semFindActionPerformed
        caminho = SoundFile.lerArquivo(".");
        semanticSoundPath.setText(caminho);
    }//GEN-LAST:event_semFindActionPerformed

    private void xmlFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlFindActionPerformed
        caminho = SoundFile.lerArquivo(".");
        xmlSoundPath.setText(caminho);
    }//GEN-LAST:event_xmlFindActionPerformed

    private void javFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_javFindActionPerformed
        caminho = SoundFile.lerArquivo(".");
        javaSoundPath.setText(caminho);
    }//GEN-LAST:event_javFindActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        dispose();
        sp.setLexicalSoundPath(lexicalSoundPath.getText());
        sp.setSyntacticSoundPath(syntacticSoundPath.getText());
        sp.setSemanticSoundPath(semanticSoundPath.getText());
        sp.setJavaSoundPath(javaSoundPath.getText());
        sp.setXmlSoundPath(xmlSoundPath.getText());
    }//GEN-LAST:event_okActionPerformed

    public String getLexSoundPath() {
        return lexicalSoundPath.getText();
    }

    public String getSynSoundPath() {
        return syntacticSoundPath.getText();
    }

    public String getSemSoundPath() {
        return semanticSoundPath.getText();
    }

    public String getXmlSoundPath() {
        return xmlSoundPath.getText();
    }

    public String getJavSoundPath() {
        return javaSoundPath.getText();
    }

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SoundsWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton javFind;
    private javax.swing.JTextField javaSoundPath;
    private javax.swing.JButton lexFind;
    private javax.swing.JTextField lexicalSoundPath;
    private javax.swing.JButton ok;
    private javax.swing.JButton semFind;
    private javax.swing.JTextField semanticSoundPath;
    private javax.swing.JButton synFind;
    private javax.swing.JTextField syntacticSoundPath;
    private javax.swing.JButton xmlFind;
    private javax.swing.JTextField xmlSoundPath;
    // End of variables declaration//GEN-END:variables
}
