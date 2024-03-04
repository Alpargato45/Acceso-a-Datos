package com.example.apiRestCliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/*
* @author Jorge del Cid Moreno
 */

public class ApiRestClienteApplication {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("""
				Que desea realizar:
				1. GET
				2. POST
				3. DELETE
				""");
		int opcion = sc.nextInt();

		switch (opcion){
			case 1 -> {
				GetRequest();
			}
			case 2 -> {
				PostRequest();
			}
			case 3 -> {
				System.out.println("¿Cuál es el codigo del registro que desea borrar?");
				String codigoBorrar = sc.nextLine();
				DeleteRequest(codigoBorrar);
			}
		}
	}

	public static void GetRequest() {
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://localhost:8080" + "api-rest/departamentos");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accepts", "application/json");

			if (conn.getResponseCode() == 200) {
				Scanner scanner = new Scanner(conn.getInputStream());
				String response = scanner.useDelimiter("\\z").next();
				scanner.close();

				JSONArray jsonArray = new JSONArray(response);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					System.out.println(jsonObject.get("depno") + " " + jsonObject.get("nombre"));
				}
			} else {
				System.out.println("Fallo en la conexion");
			}
		} catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        } finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
    }

	public static void PostRequest(){
		HttpURLConnection conn = null;
        String jsonInputString = null;
        try {
            jsonInputString = new JSONObject().put("empno", 1234)
                    .put("nombre", "Diez")
                    .put("puesto", "Dependiente")
                    .put("departamento", new JSONObject()
                            .put("depno", 20)
                            .put("nombre", "Marketing")
                            .put("ubicacion", "Barcelona")
                            .toString()).toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
			URL url = new URL("http://localhost:8080/api-rest/empleados");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			if (conn.getResponseCode() == 200) {
				System.out.println("Empleado insertado");
			} else {
				System.out.println("Fallo en la conexion");

				Scanner scanner = new Scanner(conn.getErrorStream());
				String response = scanner.useDelimiter("\\z").next();
				scanner.close();

				JSONObject jsonObject = new JSONObject(response).getJSONArray("errors").getJSONObject(0);
				System.out.println(jsonObject.get("defaultMessage"));
			}
		} catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        } finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
    }

	public static void DeleteRequest (String codeToDelete) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://localhost:8080/" + "api-rest/empleados/" + codeToDelete);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");

			if (conn.getResponseCode() == 200) {
				System.out.println("Empleado borrado");
			} else {
				System.out.println("Fallo en la conexion");
			}
		} catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
    }

	public static void PutRequest(String codeToUpdate){
		HttpURLConnection conn = null;
		String jsonInputString = null;
		try {
			jsonInputString = new JSONObject().put("empno", codeToUpdate)
					.put("nombre", "Diez")
					.put("puesto", "Dependiente")
					.put("departamento", new JSONObject()
							.put("depno", 20)
							.put("nombre", "Marketing")
							.put("ubicacion", "Barcelona")
							.toString()).toString();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		try {
			URL url = new URL("http://localhost:8080/api-rest/empleados");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			if (conn.getResponseCode() == 200) {
				System.out.println("Empleado actualizado");
			} else {
				System.out.println("Fallo en la conexion");

				Scanner scanner = new Scanner(conn.getErrorStream());
				String response = scanner.useDelimiter("\\z").next();
				scanner.close();

				JSONObject jsonObject = new JSONObject(response).getJSONArray("errors").getJSONObject(0);
				System.out.println(jsonObject.get("defaultMessage"));
			}
		} catch (IOException | JSONException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
}