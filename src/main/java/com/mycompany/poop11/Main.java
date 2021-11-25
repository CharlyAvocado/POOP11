/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poop11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;



/**
 *
 * @author Hyperion
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("################File########################");
        File archivo=new File("archivo.csv");
        System.out.println(archivo.exists());
        if(!archivo.exists()){
            try {
                boolean seCreo=archivo.createNewFile();
                System.out.println(seCreo);
                System.out.println(archivo.exists());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("################FileOutputStream########################");
        FileOutputStream fos =null;
        byte[] buffer= new byte[81];
        int nBytes;
        
        try {
            System.out.println("Escribir el texto a guardar en el archivo: ");
            nBytes=System.in.read(buffer);
            fos=new FileOutputStream("fos.csv");
            fos.write(buffer,0,nBytes);                                   
        } catch (IOException ex) {
            ex.getMessage();
        }finally{
            try {
                if(fos!=null)                
                    fos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        System.out.println("################FileInputStream########################");
        FileInputStream fis=null;
        try {
            fis=new FileInputStream("fos.csv");
            nBytes=fis.read(buffer,0,81);
            String texto=new String(buffer,0,nBytes);
            System.out.println(texto);
        } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }finally{
            try{
                if(fis!=null)
                    fis.close();
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }            
        }
        System.out.println("################FileWriter########################");
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe el texto para guardar en el archivo");
            String texto2=br.readLine();
            FileWriter fw=new FileWriter("fw.csv");
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter salida=new PrintWriter(bw);
            salida.println(texto2);
            for (int i = 0; i < 10; i++) {
                salida.println(i+ ",Linea del for,");
            }
            for (int i = 0; i < 5; i++) {
                salida.println("Martínez,Miranda,Juan,Carlos,318145042,20%");
            }
            salida.close();
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
        System.out.println("################FileReader########################");
        try{
            BufferedReader br;
            FileReader fr=new FileReader("fw.csv");
            br=new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea=br.readLine();
            while(linea!=null){
                System.out.println(linea);
                linea=br.readLine();
            }
        }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
        System.out.println("################FileReaderTokenizer########################");
        try{
            BufferedReader br;
            FileReader fr=new FileReader("fw.csv");
            br=new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea=br.readLine();
            String[] aux=new String[200];
            int cont=0;
            while(linea!=null){
                aux[cont]=linea;
                StringTokenizer tokenizer =new StringTokenizer(aux[cont],",");
                while(tokenizer.hasMoreTokens()){
                    System.out.println(tokenizer.nextToken());
                }
                linea=br.readLine();
                cont++;
            }
            
        }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
                    System.out.println("______________________________________________________");

        System.out.println("##################StringTokenizer######################");
        String linea="Ramiro,Juarez,Perez,319145542,21,22%";
        StringTokenizer tokenizer =new StringTokenizer(linea,",");
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        System.out.println("################Serialización########################");
        Date date=new Date();
        System.out.println(date);
        
        try {
            FileOutputStream f= new FileOutputStream("fecha.ser");
            ObjectOutputStream oos= new ObjectOutputStream(f);
            oos.writeObject(date);
            oos.close();
        } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
        System.out.println("################Deserialización########################");
        
        try {
            TimeUnit.SECONDS.sleep(10);
            Date date2=new Date();
            System.out.println("La fecha actuializada es: "+date2);
            FileInputStream f = new FileInputStream("fecha.ser");
            ObjectInputStream ois = new ObjectInputStream(f);
            Date date3 = (Date) ois.readObject();
            ois.close();
            System.out.println("Objeto deserializado: "+date3);
        } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
        }
    }
}
