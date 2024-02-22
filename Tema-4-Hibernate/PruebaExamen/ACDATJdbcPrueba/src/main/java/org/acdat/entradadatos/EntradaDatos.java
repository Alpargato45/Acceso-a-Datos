package org.acdat.entradadatos;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Jorge del Cid Moreno
 * 
 */
public class EntradaDatos {
        
       private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static int pedirEntero(String mensaje) {
        int num = 0;
        boolean error;

        do {
            try {
                error = false;
                System.out.println(mensaje);
                num = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Dato introducido erróneo");
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        
        return num;
    }

    public static double pedirDoble(String mensaje) {
        double num = 0;
        boolean error;

        do {
            try {
                error = false;
                System.out.println(mensaje);
                num = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Dato introducido erróneo");
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        
        return num;
    }
    
     public static float pedirFloat(String mensaje) {
        float num = 0;
        boolean error;

        do {
            try {
                error = false;
                System.out.println(mensaje);
                num = sc.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("Dato introducido erróneo");
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        
        return num;
    }
    
     public static long pedirLong(String mensaje) {
        long num = 0;
        boolean error;

        do {
            try {
                error = false;
                System.out.println(mensaje);
                num = sc.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Dato introducido erróneo");
                error = true;
            } finally {
                sc.nextLine();
            }
        } while (error);
        
        return num;
    }    
    
    public static char pedirChar(String mensaje){
        char letra=' ';
        
        System.out.println(mensaje);
        letra=sc.next().charAt(0);
        
        sc.nextLine();
        
        return letra;
    }
    
    public static String pedirCadena(String mensaje){
        String cad="";
        
        System.out.println(mensaje);
        cad=sc.nextLine();
        
        return cad;
    }

}
