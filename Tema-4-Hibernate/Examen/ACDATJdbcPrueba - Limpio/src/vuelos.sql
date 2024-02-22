-- Crear tabla de vuelos
CREATE TABLE vuelos (
                        vuelo_id SERIAL PRIMARY KEY,
                        origen VARCHAR(50),
                        destino VARCHAR(50),
                        fecha_salida DATE,
                        fecha_llegada DATE,
                        costo NUMERIC(10, 2)
);