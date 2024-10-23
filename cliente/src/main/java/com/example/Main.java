package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    /**
     * @param args
     * @throws UnknownHostException
     * @throws IOException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Cliente avviato!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Client connesso!");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        do{
            Scanner scan = new Scanner(System.in);

            do{
            try{
            System.out.println("digita un numero ");
            String stringaDigitata = scan.nextLine();
            int numero = Integer.parseInt(stringaDigitata);
            if(numero >= 0 && numero<=100)
            {
                String testo = Integer.toString(numero);
                out.writeBytes( testo + "\n");
                break;
            }
            else{ System.out.println("hai inserito un numero che non è compreso tra 0 e 100");}
        }catch(Exception e){
            System.out.println("non hai inserito un numero");}
            }while (true);

           String stringaRicevuta = in.readLine(); 
             if(stringaRicevuta.equals("="))
             {int a = in.read();
              System.out.println("hai vinto con " + a + " tentativo");}
                

             else if (stringaRicevuta.equals(">")) {System.out.println("numero troppo piccolo");}
             else if(stringaRicevuta.equals("<")){ System.out.println("numero troppo grande");}

       }while(true);
    }
}