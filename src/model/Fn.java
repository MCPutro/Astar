/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.*;

/**
 *
 * @author Mu'ti C Putro
 */
public class Fn {
    private double fnValue;
    private double jarak=0;
    private Stack<String> nodeYangDilalui = new Stack<String>();
    private String status;

    
    public Fn(double jarak,String[] l, String n) {
        //this.jarak = this.getJarak()+jarak;
        this.jarak = jarak;
        switch (n) {
            case "Ravenna":
                this.fnValue = 0 + this.getJarak();
                break;
            case "Rimini":
                this.fnValue = (0.5 + this.getJarak());
                break;
            case "Ferrara":
                this.fnValue = 5 + this.getJarak();
                break;
            case "Forli":
                this.fnValue = 2 + this.getJarak();
                break;
            case "Cesena":
                this.fnValue = (4.5+ this.getJarak());
                break;
            case "Faenza":
                this.fnValue = 4 + this.getJarak();
                break;
            case "Imola":
                this.fnValue = (5 + this.getJarak());
                break;
            case "Emilia":
                this.fnValue = 6 + this.getJarak();
                break;
            case "Terme":
                this.fnValue = 7 + this.getJarak();
                break;
            case "Carpi":
                this.fnValue = 8 + this.getJarak();
                break;
            case "Piacenza":
                this.fnValue = (10 + this.getJarak());
                break;
            case "Bobbia":
                this.fnValue = 10.5 + this.getJarak();
                break;

//          contoh lain
            case "s":this.fnValue = 80 + this.getJarak();
            break;
            case "a":this.fnValue = 80 + this.getJarak();
            break;
            case "b":this.fnValue = 60 + this.getJarak();
            break;
            case "c":this.fnValue = 70 + this.getJarak();
            break;
            case "d":this.fnValue = 85 + this.getJarak();
            break;
            case "e":this.fnValue = 74 + this.getJarak();
            break;
            case "f":this.fnValue = 70 + this.getJarak();
            break;
            case "h":this.fnValue = 40 + this.getJarak();
            break;
            case "j":this.fnValue = 100 + this.getJarak();
            break;
            case "k":this.fnValue = 30 + this.getJarak();
            break;
            case "l":this.fnValue = 20 + this.getJarak();
            break;
            case "m":this.fnValue = 70 + this.getJarak();
            break;
            case "g":this.fnValue = 0 + this.getJarak();
            break;
        }
        
        
        //mengingat node yang dilalui
        List<String> list = Arrays.asList(l);
        this.nodeYangDilalui.addAll(list);
        //menambahkan node saat ini
        this.nodeYangDilalui.push(n);
        //set status 
        this.status = "open";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFnValue() {
        return fnValue;
    }

    public double getJarak() {
        return jarak;
    }

    public Stack<String> getNodes() {
        return nodeYangDilalui;
    }

    public String getNodesLast() {
        return nodeYangDilalui.lastElement();
    }
    
    public void display(){
        //System.out.println(this.nodeYangDilalui+"->"+this.jarak);
        System.out.println(this.getNodes()+" -> F(n) : "+this.getFnValue()+" "+getStatus());
    }
    
    public String getDisplay(){
        
        return this.getNodes()+" -- "+this.getJarak()+" --> F(n) : "+this.getFnValue()+" "+getStatus();
        
    }
    
    
    
}
