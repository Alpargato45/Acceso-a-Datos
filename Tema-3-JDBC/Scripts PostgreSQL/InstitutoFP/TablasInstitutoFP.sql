CREATE DATABASE "InstitutoFP"
    
	Create table "Asignaturas" (
		Codigo serial PRIMARY KEY,
		Nombre varchar(50),
		Anio integer
	);
	
	Insert Into "Asignaturas" (Nombre, Anio) Values ('ACCESO A DATOS', 2);
	Insert Into "Asignaturas" (Nombre, Anio) Values ('ENTORNOS DE DESARROLLO', 1);
	Insert Into "Asignaturas" (Nombre, Anio) Values ('SISTEMAS DE GESTIÓN DE BASES DE DATOS', 1);
	Insert Into "Asignaturas" (Nombre, Anio) Values ('SERVICIOS Y PROCESOS', 1);
	
