package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int menu = 0;
        metodosPostgre metodo = new metodosPostgre();

        do {
            System.out.println("\tMENU");
            System.out.println("1. Enumerar empleados que son vendedores");
            System.out.println("2. Empleados pertenecientes a marketing");
            System.out.println("3. Empleados cuyo nombre empieza por p");
            System.out.println("4. Salir");
            do {
                try {
                    menu = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("caracter introducido no válido");
                }
            } while (menu < 1 || menu > 4);

            switch (menu) {
                case 1 -> {
                    metodo.BBDDQuery("SELECT * FROM empleados WHERE puesto = 'Vendedor'","\n-----------------\n\tENUMERAR TODOS LOS EMPLEADOS QUE SON VENDEDORES\n-----------------");
                }
                case 2 -> {
                    metodo.BBDDQuery("SELECT * FROM empleados e\n" + "JOIN departamentos d ON e.depno = d.depno\n" + "WHERE d.nombre = 'Marketing'","\n-----------------\n\tEMPLEADOS PERTENECIENTES A MARKETING\n-----------------");
                }
                case 3 -> {
                    metodo.BBDDQuery("SELECT * FROM empleados WHERE nombre LIKE 'P%'","\n-----------------\n\tEMPLEADOS CUYO NOMBRE EMPIEZA POR P\n-----------------");
                }
            }

        } while (menu != 4);
    }
}