package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread{
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Random random = new Random();
            int numero = random.nextInt(100);
            int tentativo = 1;
            do{
                String stringaRicevuta = in.readLine();

                if(stringaRicevuta.equals("!")){
                    System.out.println("il client vuole chiudere");
                    s.close();
                    break;
                }
                System.out.println("Numero ricevuta sul thread" + Thread.currentThread().getName() + " : " + stringaRicevuta);
                int valore = Integer.parseInt(stringaRicevuta);
                if(numero == valore){
                  
                    out.writeBytes( "=" + "\n");  
                   out.writeByte(tentativo);
                   System.out.println("il server sta chiudendo");
                   s.close();
                }
                else if(numero < valore){out.writeBytes("<" + "\n");
            tentativo++;}
                else if(numero > valore){out.writeBytes(">" + "\n");
            tentativo++;}
            System.out.println(tentativo);     
            }while(true);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
