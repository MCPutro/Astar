/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.*;

/**
 *
 * @author Mu'ti C Putro
 */
public class Node implements Serializable{
    private String asal;
    private String tujuan;
    private int jarak;

    public Node(String asal, String tujuan, int jarak) {
        this.asal = asal;
        this.tujuan = tujuan;
        this.jarak = jarak;
    }

    public String getAsal() {
        return asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public int getJarak() {
        return jarak;
    }

//    public void display(){
//        System.out.println(this.getAsal()+" "+this.getTujuan()+" "+this.getJarak()+" Km");
//    }
}
