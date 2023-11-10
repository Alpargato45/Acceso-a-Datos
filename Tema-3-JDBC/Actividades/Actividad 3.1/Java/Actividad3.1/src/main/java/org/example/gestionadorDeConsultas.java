package org.example;

import java.sql.*;

public class gestionadorDeConsultas {

    private metodosPostgre metodos = new metodosPostgre();

    public void Ejercicio1() {
        metodos.BBDDQuery("SELECT apellido1 FROM empleado","\n-----------------\n\tLISTAR EL PRIMER APELLIDO DE TODOS LOS EMPLEADOS\n-----------------");
    }
    public void Ejercicio2() {
        metodos.BBDDQuery("SELECT DISTINCT apellido1 FROM empleado","\n-----------------\n\tLISTAR EL PRIMER APELLIDO SIN REPETIR DE TODOS LOS EMPLEADOS\n-----------------");
    }
    public void Ejercicio3() {
        metodos.BBDDQuery("SELECT nombre, gastos FROM departamento ORDER BY gastos LIMIT 2","\n-----------------\n\tLISTAR NOMBRE Y GASTO DE LOS 2 DEPARTAMENTOS QUE TIENEN MENOR GASTO\n-----------------");
    }
    public void Ejercicio4() {
        metodos.BBDDQuery("SELECT nombre, presupuesto FROM departamento WHERE presupuesto >= 150000","\n-----------------\n\tLISTAR NOMBRE DE LOS DEPARTAMENTOS Y PRESUPUESTOS MAYORES A 150000 EUROS\n-----------------");
    }
    public void Ejercicio5() {
        metodos.BBDDQuery("SELECT empleado.*, departamento.nombre AS nombre_departamento\n" + "FROM empleado\n" + "JOIN departamento ON empleado.id_departamento = departamento.id;\n","\n-----------------\n\tLISTAR EMPLEADOS Y DATOS DE DONDE TRABAJA CADA UNO\n-----------------");
    }
    public void Ejercicio6() {
        metodos.BBDDQuery("SELECT empleado.*, departamento.nombre AS nombre_departamento\n" + "FROM empleado\n" + "JOIN departamento ON empleado.id_departamento = departamento.id\n" + "ORDER BY nombre_departamento, apellido1, nombre\n","\n-----------------\n\tLISTAR TODOS LOS EMPLEADOS Y DATOS DE DEPARTAMENTO ORDENADO\n-----------------");
    }
    public void Ejercicio7() {
        metodos.BBDDQuery("SELECT departamento.id, departamento.nombre\n" + "FROM departamento\n" + "WHERE departamento.id IN (SELECT DISTINCT id_departamento FROM empleado)","\n-----------------\n\tLISTAR IDENTIFICADOR Y NOMBRE DE DEPARTAMENTO CON EMPLEADOS\n-----------------");
    }
    public void Ejercicio8() {
        metodos.BBDDQuery("SELECT departamento.nombre\n" + "FROM departamento\n" + "JOIN empleado ON departamento.id = empleado.id_departamento\n" + "WHERE empleado.nif = '38382980M'","\n-----------------\n\tLISTAR NOMBRE DEL DEPARTAMENTO DONDE TRABAJA EL NIF 38382980M\n-----------------");
    }
    public void Ejercicio9() {
        metodos.BBDDQuery("SELECT SUM(presupuesto) FROM departamento","\n-----------------\n\tLISTAR LA SUMA DEL PRESUPUESTO DE TODOS LOS DEPARTAMENTOS\n-----------------");
    }
}