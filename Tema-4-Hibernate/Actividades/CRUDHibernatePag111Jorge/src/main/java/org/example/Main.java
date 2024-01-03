package org.example;

import org.example.entradadatos.EntradaDatos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;

public class Main {

    public static void crearEmpleado(Session session) {
        boolean existe = false;
        int numEmp;
        do {
            numEmp = EntradaDatos.pedirEntero("Número del Empleado: ");

            EmpleadosClass empleadoExistente = session.get(EmpleadosClass.class, numEmp);
            if (empleadoExistente != null) {
                System.out.println("Ya existe un empleado con el número " + numEmp + ". No se puede añadir.");
                existe = true;
            }else {
                existe = false;
            }
        }while(existe);

        String nombre = EntradaDatos.pedirCadena("Nombre del Empleado: ");
        String puesto = EntradaDatos.pedirCadena("Puesto del Empleado: ");
        int numDep = EntradaDatos.pedirEntero("Número del Departamento: ");
        Transaction transaction = session.beginTransaction();
        EmpleadosClass empleado = new EmpleadosClass();
        empleado.setEmpno(numEmp);
        empleado.setNombre(nombre);
        empleado.setPuesto(puesto);
        empleado.setDepno(numDep);
        session.save(empleado);
        transaction.commit();

    }

    public static void mostrarEmpleado(Session session) {
        Query<EmpleadosClass> miQuery = session.createQuery("from org.example.EmpleadosClass");
        List<EmpleadosClass> listaEmpleados = miQuery.list();
        for(Object obj : listaEmpleados) {
            EmpleadosClass empleado = (EmpleadosClass) obj;
            System.out.println("Número: " + empleado.getEmpno() + " Nombre: " + empleado.getNombre() +
                    " Puesto: " + empleado.getPuesto() + " Departamento: " + empleado.getDepno());
        }
    }

    public static void mostrarDepartamento(Session session) {
        Query<DepartamentosClass> miQuery = session.createQuery("from org.example.DepartamentosClass");
        List<DepartamentosClass> listaDepartamentos = miQuery.list();
        for(Object obj : listaDepartamentos) {
            DepartamentosClass departamento = (DepartamentosClass) obj;
            System.out.println("Nombre: " + departamento.getNombre());
            Collection<EmpleadosClass> listaEmpleados = departamento.getEmpleadosByDepno();
            for(EmpleadosClass empleado : listaEmpleados) {
                System.out.println("\tNúmero: " + empleado.getEmpno() + " Nombre: " + empleado.getNombre());
            }
        }
    }

    public static boolean actualizarEmpleado(Session session) {
        int numEmp;
            numEmp = EntradaDatos.pedirEntero("Número del Empleado a cambiar: ");

            EmpleadosClass empleadoExistente = session.get(EmpleadosClass.class, numEmp);
            if (empleadoExistente == null) {
                System.out.println("No existe ningún empleado con el número " + numEmp + ". No se puede modificar.");
                return false;
            }else {
                String nombre = EntradaDatos.pedirCadena("Nombre del Empleado: ");
                String puesto = EntradaDatos.pedirCadena("Puesto del Empleado: ");
                int numDep = EntradaDatos.pedirEntero("Número del Departamento: ");
                Transaction transaction = session.beginTransaction();
                empleadoExistente.setNombre(nombre);
                empleadoExistente.setPuesto(puesto);
                empleadoExistente.setDepno(numDep);
                session.update(empleadoExistente);
                transaction.commit();
            }
            return true;
        }

    public static boolean borrarEmpleados(Session session) {
        int numEmp;
        numEmp = EntradaDatos.pedirEntero("Número del Empleado a borrar: ");

        EmpleadosClass empleadoExistente = session.get(EmpleadosClass.class, numEmp);
        if (empleadoExistente == null) {
            System.out.println("No existe ningún empleado con el número " + numEmp + ". No se puede borrar.");
            return false;
        } else {
            Transaction transaction = session.beginTransaction();
            session.delete(empleadoExistente);
            transaction.commit();
        }
        return true;
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        if (session != null) {
            System.out.println("Se Inicio la sesión");
        }else {
            System.out.println("Error abriendo la sesión");
        }

        int menu = 0;


        do {
            System.out.println("\tMENU");
            System.out.println("1. Crear Empleado");
            System.out.println("2. Mostrar Empleado");
            System.out.println("3. Mostrar Departamento");
            System.out.println("4. Actualizar Empleado");
            System.out.println("5. Borrar Empleado");
            System.out.println("6. Salir");
            do {
                try {
                    menu = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("caracter introducido no válido");
                }
            } while (menu < 1 || menu > 6);

            switch (menu) {
                case 1 -> {
                    crearEmpleado(session);
                    System.out.println("Empleado añadido con exito");
                }
                case 2 -> {
                    mostrarEmpleado(session);
                }
                case 3 -> {
                    mostrarDepartamento(session);
                }
                case 4 -> {
                    if(actualizarEmpleado(session)) {
                        System.out.println("Empleado modificado con éxito");
                    }
                }
                case 5 -> {
                    if(borrarEmpleados(session)) {
                        System.out.println("Empleado eliminado con éxito");
                    }
                }
            }

        }
        while (menu != 6);
    }
}