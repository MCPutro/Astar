/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mu'ti C Putro
 */
public class Frame extends javax.swing.JFrame {

    private ArrayList<Node> node;
    private ArrayList<Fn> fn;
    
    private final String start = "Bobbia";
    private final String finish = "Ravenna";
    private String pointer = "null";
    
    private int min = -1;
    
    int level = 0;
    
    
    public Frame() {
        node = new ArrayList<>();
        fn = new ArrayList<>();
        initComponents();
        setData();
        Astar();
    }

    void setData(){
        node.add(new Node("Bobbia","Piacenza",5));
        node.add(new Node("Bobbia","Terme",3));
        node.add(new Node("Bobbia","Cesena",15));
        node.add(new Node("Piacenza","Carpi",3));
        node.add(new Node("Piacenza","Terme",3));
        node.add(new Node("Terme","Emilia",2));
        node.add(new Node("Terme","Faenza",3));
        node.add(new Node("Cesena","Rimini",5));
        node.add(new Node("Carpi","Ferrara",8));
        node.add(new Node("Carpi","Emilia",2));
        node.add(new Node("Emilia","Imola",2));
        node.add(new Node("Faenza","Forli",2));
        node.add(new Node("Faenza","Cesena",6));
        node.add(new Node("Ferrara","Imola",3));
        node.add(new Node("Ferrara","Ravenna",6));
        node.add(new Node("Imola","Forli",3));
        node.add(new Node("Imola","Faenza",1));
        node.add(new Node("Forli","Ravenna",3));
        node.add(new Node("Forli","Cesena",2));
        node.add(new Node("Rimini","Ravenna",1));
    
//        node.add(new Node("s","a",10));
//        node.add(new Node("s","b",25));
//        node.add(new Node("s","c",30));
//        node.add(new Node("s","d",35));
//        node.add(new Node("s","e",10));
//        node.add(new Node("a","g",90));
//        node.add(new Node("a","b",10));
//        node.add(new Node("b","f",5));
//        node.add(new Node("b","k",50));
//        node.add(new Node("c","h",40));
//        node.add(new Node("d","h",25));
//        node.add(new Node("d","l",52));
//        node.add(new Node("e","j",20));
//        node.add(new Node("e","d",15));
//        node.add(new Node("f","k",40));
//        node.add(new Node("h","l",25));
//        node.add(new Node("j","m",40));
//        node.add(new Node("k","g",30));
//        node.add(new Node("l","g",40));
//        node.add(new Node("m","g",80));
        
        hasil.append("start : "+start+"     finish : "+this.finish+"\n");
        hasil.append("====================================================\n");
        
        
        int i=0;
        DefaultTableModel model = (DefaultTableModel) this.Table1.getModel();
        for(Node nd : this.node){
            model.addRow(new Object[]{"","",""});
            model.setValueAt(nd.getAsal(), i, 0);
            model.setValueAt(nd.getTujuan(), i, 1);
            model.setValueAt(nd.getJarak(), i, 2);
            i++;
        }
        // show h(n)
        hn.setValueAt("Ravenna ", 0, 0);hn.setValueAt("0", 0, 1);
        hn.setValueAt("Rimini", 1, 0);  hn.setValueAt("0.5", 1, 1);
        hn.setValueAt("Ferrara ", 2, 0);hn.setValueAt("5", 2, 1);
        hn.setValueAt("Forli ", 3, 0);hn.setValueAt("2", 3, 1);
        hn.setValueAt("Cesena ", 4, 0);hn.setValueAt("4.5", 4, 1);
        hn.setValueAt("Faenza ", 5, 0);hn.setValueAt("4", 5, 1);
        hn.setValueAt("Imola ", 6, 0);hn.setValueAt("5", 6, 1);
        hn.setValueAt("Emilia ", 7, 0);hn.setValueAt("6", 7, 1);
        hn.setValueAt("Terme ", 8, 0);hn.setValueAt("7", 8, 1);
        hn.setValueAt("Carpi ", 9, 0);hn.setValueAt("8", 9, 1);
        hn.setValueAt("Piacenza ", 10, 0);hn.setValueAt("10", 10, 1);
        hn.setValueAt("Bobbia ", 11, 0);hn.setValueAt("10.5", 11, 1);
        
    }
    
    
    void Astar(){
        while (!pointer.equalsIgnoreCase(this.finish)){
        //for(int u = 0; u<5;u++){
            if(pointer.equals("null")){
                this.pointer = this.start;
                fn.add(new Fn(0, new String[]{}, pointer));
            }
            else
            if(fn.isEmpty()){
                int i=0;
                while (this.pointer.equalsIgnoreCase(this.node.get(i).getAsal())) {
                    String[] h = {start};
                    this.fn.add(new Fn(this.node.get(i).getJarak(), h, this.node.get(i).getTujuan()));
                    i++;
                }
            }
            else
            {
                for(Node n : this.node){
                    if(n.getAsal().equalsIgnoreCase(pointer)){
                        double jarak = fn.get(min).getJarak()+n.getJarak();
                        String[] h = new String[fn.get(min).getNodes().size()];
                        h =  fn.get(min).getNodes().toArray(h);
                        this.fn.add(new Fn(jarak, h, n.getTujuan()));
                    }
                }
                setClose(min);
            }
            this.min = this.getFnMin();
            this.pointer = fn.get(min).getNodesLast();
            
            
            hasil.append("  iterasi : "+level++);
            int o=0;
            for(Fn ff : fn){
                hasil.append("\n    "+o+"   "+ff.getDisplay());
                o++;
            }
            hasil.append("\n\n  best nodes index "+min+"  "+fn.get(min).getNodes()+"  F(n): "+fn.get(min).getFnValue());
            hasil.append("\n   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
        }

        hasil.append("\n    rute yang di pilih :");
        hasil.append("\n    "+fn.get(min).getNodes()+" -> "+fn.get(min).getJarak()+" Km\n\n");
    }
    
    int getFnMin(){
        double min = -1;
        int i=0;
        for (; i < fn.size(); i++) {
            if(fn.get(i).getStatus().equals("open")){
                min = fn.get(i).getFnValue();
                break;
            }
        }
        int index = -1;
        for (int j = 0; j < fn.size(); j++) {
            if(min > fn.get(j).getFnValue() && fn.get(j).getStatus().equals("open")){
                min = fn.get(j).getFnValue();
                index = j;
            }
        }
        if(index == -1){index = i;}
        return index;
    }
    
    void setClose(int index){
        fn.get(index).setStatus("close");
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
        Table1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        hasil = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        hn = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Asal", "Tujuan", "Jarak"
            }
        ));
        jScrollPane1.setViewportView(Table1);

        hasil.setColumns(20);
        hasil.setRows(5);
        jScrollPane2.setViewportView(hasil);

        hn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kota", "h(n)"
            }
        ));
        jScrollPane3.setViewportView(hn);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Status : ");

        jLabel2.setText("open : belum di evaluasi");

        jLabel3.setText("close : sudah dievaluasi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table1;
    private javax.swing.JTextArea hasil;
    private javax.swing.JTable hn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
