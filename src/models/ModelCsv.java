/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import views.ViewCsv;
/**
 * creacion geeters y setters para la lectura en controller
 * @author Edgar
 */
public class ModelCsv {
    private String nombre = "";
    private String email = "";
    private String path = "C:\\archivos\\base.csv";
    boolean condicion = true;
    ViewCsv viewCsv;
    private int fila = 0;
    private int posicion = 0;
    ArrayList <String> agenda = new ArrayList <String>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    public ViewCsv getViewCsv() {
        return viewCsv;
    }

    public void setViewCsv(ViewCsv viewCsv) {
        this.viewCsv = viewCsv;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public ArrayList<String> getAgenda() {
        return agenda;
    }

    public void setAgenda(ArrayList<String> agenda) {
        this.agenda = agenda;
    }
    
    /**
     * permite la lectura de un archivo 
     */
    public void readFile(){
        try{
            String row; 
            try (FileReader file = new FileReader(path); BufferedReader bufferedReader = new BufferedReader(file)){
                while ((row = bufferedReader.readLine()) != null){    
                    agenda.add(row);
                }
                bufferedReader.close();
            } 
        }catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(viewCsv,"File not found: " + ex.getMessage());
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(viewCsv,"Error en I/O operation" + ex.getMessage());
        }
    }
    /**
     * permite la escritura en el archivo
     */
    public void writeFile(){
        try{
            File file = new File(path);
                FileWriter fileWriter = new FileWriter (file,condicion);
                try (PrintWriter printWriter  = new PrintWriter(fileWriter)){
                        printWriter.println(nombre + "," + email);
                        printWriter.close();
                        JOptionPane.showMessageDialog(viewCsv,"Se modificó correctamente");
            }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(viewCsv,"Error en I/O operation" + ex.getMessage());
                }
            }
    public void primero(){
            String lista = agenda.get(fila);
            String datos [] = lista.split(",");
            posicion = fila;
            nombre = datos[0];
            email = datos[1];
    }
    public void anterior(){
        if (posicion > 0){
            posicion = posicion -1;
            String lista = agenda.get(posicion);
            String datos [] = lista.split(",");
            nombre = datos[0];
            email = datos[1];
        } 
    }
    public void siguiente(){
        if (posicion < (agenda.size()-1)){
            posicion = posicion + 1;
            String lista = agenda.get(posicion);
            String datos [] = lista.split(",");
            nombre = datos[0];
            email = datos[1];
        }
    }
    public void ultimo(){
        int ultimo = (agenda.size()-1);
        String lista = agenda.get(ultimo);
        String datos [] = lista.split(",");
        posicion = ultimo;
        nombre = datos[0];
        email = datos[1];
    }
}
