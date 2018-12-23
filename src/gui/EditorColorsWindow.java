package gui;

import configuration.EditorColors;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

/**
 * This class gives to the user a graphical interface that
 * possibilities the change of the editor's colors.
 * @author Diego Henrique Oliveira de Souza
 */
public class EditorColorsWindow extends javax.swing.JFrame {

    private EditorColors ce = JokerWindow.getCores();
    private JColorChooser nova = new JColorChooser(Color.BLACK);

    public EditorColorsWindow() {
        initComponents();
        palavraChaveB.setForeground(ce.getPalavra_chave());
        processoB.setForeground(ce.getProcesso());
        gateB.setForeground(ce.getGate());
        identificadorB.setForeground(ce.getIdentificador());
        comentarioB.setForeground(ce.getComentario());
        operadoresB.setForeground(ce.getOperadores());
        saidaB.setForeground(ce.getSaida());
        naoReconhecidos.setForeground(ce.getNao_reconhecidos());

        //ícone do programa
        setIconImage(new ImageIcon(getClass().getResource("/img/jokerLogo.png")).getImage());

        if(ce.isIsBold_comentario()){
            comentarioN.setSelected(true);
        }
        if(ce.isIsBold_gate()){
            gateN.setSelected(true);
        }
        if(ce.isIsBold_identificador()){
            idN.setSelected(true);
        }
        if(ce.isIsBold_nao_reconhecidos()){
            nao_reconhecidoN.setSelected(true);
        }
        if(ce.isIsBold_operadores()){
            operadorN.setSelected(true);
        }
        if(ce.isIsBold_palavra_chave()){
            palavraN.setSelected(true);
        }
        if(ce.isIsBold_processo()){
            processoN.setSelected(true);
        }
        if(ce.isIsBold_saida()){
            saidaN.setSelected(true);
        }

        //italico
        if(ce.isIsItalic_comentario()){
            comentarioI.setSelected(true);
        }
        if(ce.isIsItalic_gate()){
            gateI.setSelected(true);
        }
        if(ce.isIsItalic_identificador()){
            idI.setSelected(true);
        }
        if(ce.isIsItalic_nao_reconhecidos()){
            nao_reconhecidoI.setSelected(true);
        }
        if(ce.isIsItalic_operadores()){
            operadorI.setSelected(true);
        }
        if(ce.isIsItalic_palavra_chave()){
            palavraI.setSelected(true);
        }
        if(ce.isIsItalic_processo()){
            processoI.setSelected(true);
        }
        if(ce.isIsItalic_saida()){
            saidaI.setSelected(true);
        }

        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        palavraChaveB = new javax.swing.JButton();
        processoB = new javax.swing.JButton();
        gateB = new javax.swing.JButton();
        identificadorB = new javax.swing.JButton();
        comentarioB = new javax.swing.JButton();
        operadoresB = new javax.swing.JButton();
        saidaB = new javax.swing.JButton();
        naoReconhecidos = new javax.swing.JButton();
        palavraN = new javax.swing.JToggleButton();
        palavraI = new javax.swing.JToggleButton();
        processoN = new javax.swing.JToggleButton();
        processoI = new javax.swing.JToggleButton();
        gateN = new javax.swing.JToggleButton();
        gateI = new javax.swing.JToggleButton();
        idN = new javax.swing.JToggleButton();
        idI = new javax.swing.JToggleButton();
        comentarioN = new javax.swing.JToggleButton();
        comentarioI = new javax.swing.JToggleButton();
        operadorN = new javax.swing.JToggleButton();
        operadorI = new javax.swing.JToggleButton();
        saidaN = new javax.swing.JToggleButton();
        saidaI = new javax.swing.JToggleButton();
        nao_reconhecidoN = new javax.swing.JToggleButton();
        nao_reconhecidoI = new javax.swing.JToggleButton();
        OK = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor Colors");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Colors by Itens"));

        palavraChaveB.setFont(new java.awt.Font("Tahoma", 1, 11));
        palavraChaveB.setText("Keyword");
        palavraChaveB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                palavraChaveBActionPerformed(evt);
            }
        });

        processoB.setFont(new java.awt.Font("Tahoma", 1, 11));
        processoB.setText("Symbol");
        processoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processoBActionPerformed(evt);
            }
        });

        gateB.setFont(new java.awt.Font("Tahoma", 1, 11));
        gateB.setText("Numeric");
        gateB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gateBActionPerformed(evt);
            }
        });

        identificadorB.setFont(new java.awt.Font("Tahoma", 1, 11));
        identificadorB.setText("Identificator");
        identificadorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificadorBActionPerformed(evt);
            }
        });

        comentarioB.setFont(new java.awt.Font("Tahoma", 1, 11));
        comentarioB.setText("Comments");
        comentarioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentarioBActionPerformed(evt);
            }
        });

        operadoresB.setFont(new java.awt.Font("Tahoma", 1, 11));
        operadoresB.setText("Operator");
        operadoresB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operadoresBActionPerformed(evt);
            }
        });

        saidaB.setFont(new java.awt.Font("Tahoma", 1, 11));
        saidaB.setText("Exit");
        saidaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saidaBActionPerformed(evt);
            }
        });

        naoReconhecidos.setFont(new java.awt.Font("Tahoma", 1, 11));
        naoReconhecidos.setText("Error");
        naoReconhecidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naoReconhecidosActionPerformed(evt);
            }
        });

        palavraN.setFont(new java.awt.Font("Tahoma", 1, 11));
        palavraN.setText("N");
        palavraN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                palavraNActionPerformed(evt);
            }
        });

        palavraI.setFont(new java.awt.Font("Tahoma", 2, 11));
        palavraI.setText("I");
        palavraI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                palavraIActionPerformed(evt);
            }
        });

        processoN.setFont(new java.awt.Font("Tahoma", 1, 11));
        processoN.setText("N");
        processoN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processoNActionPerformed(evt);
            }
        });

        processoI.setFont(new java.awt.Font("Tahoma", 2, 11));
        processoI.setText("I");
        processoI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processoIActionPerformed(evt);
            }
        });

        gateN.setFont(new java.awt.Font("Tahoma", 1, 11));
        gateN.setText("N");
        gateN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gateNActionPerformed(evt);
            }
        });

        gateI.setFont(new java.awt.Font("Tahoma", 2, 11));
        gateI.setText("I");
        gateI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gateIActionPerformed(evt);
            }
        });

        idN.setFont(new java.awt.Font("Tahoma", 1, 11));
        idN.setText("N");
        idN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idNActionPerformed(evt);
            }
        });

        idI.setFont(new java.awt.Font("Tahoma", 2, 11));
        idI.setText("I");
        idI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idIActionPerformed(evt);
            }
        });

        comentarioN.setFont(new java.awt.Font("Tahoma", 1, 11));
        comentarioN.setText("N");
        comentarioN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentarioNActionPerformed(evt);
            }
        });

        comentarioI.setFont(new java.awt.Font("Tahoma", 2, 11));
        comentarioI.setText("I");
        comentarioI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentarioIActionPerformed(evt);
            }
        });

        operadorN.setFont(new java.awt.Font("Tahoma", 1, 11));
        operadorN.setText("N");
        operadorN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operadorNActionPerformed(evt);
            }
        });

        operadorI.setFont(new java.awt.Font("Tahoma", 2, 11));
        operadorI.setText("I");
        operadorI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operadorIActionPerformed(evt);
            }
        });

        saidaN.setFont(new java.awt.Font("Tahoma", 1, 11));
        saidaN.setText("N");
        saidaN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saidaNActionPerformed(evt);
            }
        });

        saidaI.setFont(new java.awt.Font("Tahoma", 2, 11));
        saidaI.setText("I");
        saidaI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saidaIActionPerformed(evt);
            }
        });

        nao_reconhecidoN.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nao_reconhecidoN.setSelected(true);
        nao_reconhecidoN.setText("N");
        nao_reconhecidoN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nao_reconhecidoNActionPerformed(evt);
            }
        });

        nao_reconhecidoI.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        nao_reconhecidoI.setSelected(true);
        nao_reconhecidoI.setText("I");
        nao_reconhecidoI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nao_reconhecidoIActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(processoB, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(palavraChaveB, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(identificadorB)
                        .addComponent(gateB))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comentarioB)
                            .addComponent(operadoresB)
                            .addComponent(saidaB)
                            .addComponent(naoReconhecidos))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(palavraN)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(palavraI))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(processoN)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(processoI)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(gateN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gateI))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(idN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idI))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comentarioN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comentarioI)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(operadorN)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(operadorI))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(saidaN)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saidaI)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(nao_reconhecidoN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nao_reconhecidoI))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(OK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comentarioB, gateB, identificadorB, naoReconhecidos, operadoresB, palavraChaveB, processoB, saidaB});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(palavraChaveB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(palavraN)
                    .addComponent(palavraI))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(processoB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(processoN)
                    .addComponent(processoI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gateN)
                            .addComponent(gateI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idN)
                            .addComponent(idI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comentarioN)
                            .addComponent(comentarioI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(operadorN)
                            .addComponent(operadorI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saidaN)
                            .addComponent(saidaI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nao_reconhecidoN)
                            .addComponent(nao_reconhecidoI)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(gateB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(identificadorB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comentarioB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(operadoresB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saidaB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(naoReconhecidos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(OK)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comentarioB, comentarioI, comentarioN, gateB, gateI, gateN, idI, idN, identificadorB, naoReconhecidos, nao_reconhecidoI, nao_reconhecidoN, operadorI, operadorN, operadoresB, palavraChaveB, palavraI, palavraN, processoB, processoI, processoN, saidaB, saidaI, saidaN});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void palavraChaveBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_palavraChaveBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        palavraChaveB.setForeground(novaCor);
        ce.setPalavra_chave(novaCor);
        JokerWindow.configurarCoresEditor();
}//GEN-LAST:event_palavraChaveBActionPerformed

    private void processoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processoBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        processoB.setForeground(novaCor);
        ce.setProcesso(novaCor);
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_processoBActionPerformed

    private void gateBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gateBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        gateB.setForeground(novaCor);
        ce.setGate(novaCor);
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_gateBActionPerformed

    private void identificadorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificadorBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        identificadorB.setForeground(novaCor);
        ce.setIdentificador(novaCor);
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_identificadorBActionPerformed

    private void comentarioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentarioBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        comentarioB.setForeground(novaCor);
        ce.setComentario(novaCor);
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_comentarioBActionPerformed

    private void operadoresBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operadoresBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        operadoresB.setForeground(novaCor);
        ce.setOperadores(novaCor);
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_operadoresBActionPerformed

    private void saidaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saidaBActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        saidaB.setForeground(novaCor);
        ce.setSaida(novaCor);
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_saidaBActionPerformed

    private void naoReconhecidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naoReconhecidosActionPerformed
        Color novaCor = nova.showDialog(null, "", Color.BLACK);
        naoReconhecidos.setForeground(novaCor);
        ce.setNao_reconhecidos(novaCor);
        JokerWindow.configurarCoresEditor();
}//GEN-LAST:event_naoReconhecidosActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        dispose();
    }//GEN-LAST:event_OKActionPerformed

    private void palavraNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_palavraNActionPerformed
        if (ce.isIsBold_palavra_chave()) {
            ce.setIsBold_palavra_chave(false);
        } else {
            ce.setIsBold_palavra_chave(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_palavraNActionPerformed

    private void palavraIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_palavraIActionPerformed
        if (ce.isIsItalic_palavra_chave()) {
            ce.setIsItalic_palavra_chave(false);
        } else {
            ce.setIsItalic_palavra_chave(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_palavraIActionPerformed

    private void processoNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processoNActionPerformed
        if (ce.isIsBold_processo()) {
            ce.setIsBold_processo(false);
        } else {
            ce.setIsBold_processo(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_processoNActionPerformed

    private void processoIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processoIActionPerformed
        if (ce.isIsItalic_processo()) {
            ce.setIsItalic_processo(false);
        } else {
            ce.setIsItalic_processo(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_processoIActionPerformed

    private void gateNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gateNActionPerformed
        if (ce.isIsBold_gate()) {
            ce.setIsBold_gate(false);
        } else {
            ce.setIsBold_gate(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_gateNActionPerformed

    private void gateIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gateIActionPerformed
        if (ce.isIsItalic_gate()) {
            ce.setIsItalic_gate(false);
        } else {
            ce.setIsItalic_gate(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_gateIActionPerformed

    private void idNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idNActionPerformed
        if (ce.isIsBold_identificador()) {
            ce.setIsBold_identificador(false);
        } else {
            ce.setIsBold_identificador(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_idNActionPerformed

    private void idIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idIActionPerformed
        if (ce.isIsItalic_identificador()) {
            ce.setIsItalic_identificador(false);
        } else {
            ce.setIsItalic_identificador(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_idIActionPerformed

    private void comentarioNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentarioNActionPerformed
        if (ce.isIsBold_comentario()) {
            ce.setIsBold_comentario(false);
        } else {
            ce.setIsBold_comentario(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_comentarioNActionPerformed

    private void comentarioIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentarioIActionPerformed
        if (ce.isIsItalic_comentario()) {
            ce.setIsItalic_comentario(false);
        } else {
            ce.setIsItalic_comentario(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_comentarioIActionPerformed

    private void operadorNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operadorNActionPerformed
        if (ce.isIsBold_operadores()) {
            ce.setIsBold_operadores(false);
        } else {
            ce.setIsBold_operadores(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_operadorNActionPerformed

    private void operadorIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operadorIActionPerformed
        if (ce.isIsItalic_operadores()) {
            ce.setIsItalic_operadores(false);
        } else {
            ce.setIsItalic_operadores(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_operadorIActionPerformed

    private void saidaNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saidaNActionPerformed
        if (ce.isIsBold_saida()) {
            ce.setIsBold_saida(false);
        } else {
            ce.setIsBold_saida(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_saidaNActionPerformed

    private void saidaIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saidaIActionPerformed
        if (ce.isIsItalic_saida()) {
            ce.setIsItalic_saida(false);
        } else {
            ce.setIsItalic_saida(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_saidaIActionPerformed

    private void nao_reconhecidoNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nao_reconhecidoNActionPerformed
        if (ce.isIsBold_nao_reconhecidos()) {
            ce.setIsBold_nao_reconhecidos(false);
        } else {
            ce.setIsBold_nao_reconhecidos(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_nao_reconhecidoNActionPerformed

    private void nao_reconhecidoIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nao_reconhecidoIActionPerformed
        if (ce.isIsItalic_nao_reconhecidos()) {
            ce.setIsItalic_nao_reconhecidos(false);
        } else {
            ce.setIsItalic_nao_reconhecidos(true);
        }
        JokerWindow.configurarCoresEditor();
    }//GEN-LAST:event_nao_reconhecidoIActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        dispose();
}//GEN-LAST:event_cancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton cancel;
    private javax.swing.JButton comentarioB;
    private javax.swing.JToggleButton comentarioI;
    private javax.swing.JToggleButton comentarioN;
    private javax.swing.JButton gateB;
    private javax.swing.JToggleButton gateI;
    private javax.swing.JToggleButton gateN;
    private javax.swing.JToggleButton idI;
    private javax.swing.JToggleButton idN;
    private javax.swing.JButton identificadorB;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton naoReconhecidos;
    private javax.swing.JToggleButton nao_reconhecidoI;
    private javax.swing.JToggleButton nao_reconhecidoN;
    private javax.swing.JToggleButton operadorI;
    private javax.swing.JToggleButton operadorN;
    private javax.swing.JButton operadoresB;
    private javax.swing.JButton palavraChaveB;
    private javax.swing.JToggleButton palavraI;
    private javax.swing.JToggleButton palavraN;
    private javax.swing.JButton processoB;
    private javax.swing.JToggleButton processoI;
    private javax.swing.JToggleButton processoN;
    private javax.swing.JButton saidaB;
    private javax.swing.JToggleButton saidaI;
    private javax.swing.JToggleButton saidaN;
    // End of variables declaration//GEN-END:variables
}
