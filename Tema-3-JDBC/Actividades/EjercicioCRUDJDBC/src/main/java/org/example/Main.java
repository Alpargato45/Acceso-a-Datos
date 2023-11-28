package org.example;

import entradadatos.EntradaDatos;

import java.util.*;

public class Main {

    public static void añadirEmpleado(metodosPostgre metodos) {
        String nombre = "";
        String puesto = "";
        int empno;
        do {
            empno = EntradaDatos.pedirEntero("Número del Empleado");
            if (existeEmpleado(metodos,empno)) {
                System.out.println("¡Error! Ya existe un empleado con ese número. Introduce otro.");
            }
        } while (existeEmpleado(metodos,empno));
        do {
            nombre = EntradaDatos.pedirCadena("Nombre del Empleado");
        }while(nombre.length() >= 10);
        do {
            puesto = EntradaDatos.pedirCadena("Puesto del Empleado");
        }while(puesto.length() >= 15);
        int depno = EntradaDatos.pedirEntero("Número del departamento");
        metodos.BBDDStatement("INSERT INTO empleados (empno,nombre,puesto,depno) VALUES ('" + empno + "','" + nombre + "','" + puesto + "','" + depno + "');");
    }

    public static boolean existeEmpleado(metodosPostgre metodos, int empno) {
        int num = metodos.BBDDCount("SELECT COUNT(*) FROM empleados WHERE empno = " + empno + ";");
        return num > 0;
    }

    public static void mostrarEmpleados(metodosPostgre metodos) {
        metodos.BBDDQuery("SELECT * FROM empleados","MOSTRAR TODOS LOS EMPLEADOS");
    }

    public static void modificarEmpleados(metodosPostgre metodos,int numEmp) {
        String nombre = "";
        String puesto = "";
        do {
            nombre = EntradaDatos.pedirCadena("Nombre del Empleado");
        }while(nombre.length() >= 10);
        do {
            puesto = EntradaDatos.pedirCadena("Puesto del Empleado");
        }while(puesto.length() >= 15);
        int depno = EntradaDatos.pedirEntero("Número del departamento");
        metodos.BBDDStatement("UPDATE empleados SET nombre = '" + nombre + "', puesto = '" + puesto + "', depno = '" + depno + "' WHERE empno = " + numEmp + ";" );
    }

    public static void borrarEmpleados(metodosPostgre metodos,int numEmp) {
        metodos.BBDDStatement("DELETE FROM empleados WHERE empno = " + numEmp + ";");
    }

    public static void main(String[] args) {
        int menu = 0;
        metodosPostgre metodos = new metodosPostgre();
        do {
            System.out.println("\tMENU");
            System.out.println("1. Añadir a la BBDD");
            System.out.println("2. Mostrar Todos los Empleados");
            System.out.println("3. Modificar Empleado");
            System.out.println("4. Borrar Empleado");
            System.out.println("5. Salir");
            do {
                try {
                    menu = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("caracter introducido no válido");
                }
            } while (menu < 1 || menu > 5);

            switch (menu) {
                case 1 -> {
                    añadirEmpleado(metodos);
                    System.out.println("Empleado añadido con éxito");
                }
                case 2 -> {
                    mostrarEmpleados(metodos);
                }
                case 3 -> {
                    int empno;
                    empno = EntradaDatos.pedirEntero("Número del Empleado que deseas Modificar");
                    if(existeEmpleado(metodos,empno)) {
                        modificarEmpleados(metodos,empno);
                        System.out.println("Empleado número: " + empno + " Modificado con éxito");
                    }else {
                        System.out.println("No existe un empleado con número: " + empno);
                    }
                }
                case 4 -> {
                    int empno;
                    empno = EntradaDatos.pedirEntero("Número del Empleado que deseas Borrar");
                    if(existeEmpleado(metodos,empno)) {
                        borrarEmpleados(metodos,empno);
                        System.out.println("Empleado número: " + empno + " Borrado con éxito");
                    }else {
                        System.out.println("No existe un empleado con número: " + empno);
                    }
                }
            }

        } while (menu != 5);
    }
}