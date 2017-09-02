/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;

/**
 *
 * @author Nahuel
 */
public class ClavesHash {

    private static final String salt= "LongStringForExtraSecurity@#$!%^&*(*)1234567890";
    
    /**
     * Funcion encargada de Registrar un Usuario en el archivo
     * @param usuario
     * @param clave
     * @throws IOException 
     */    
    public void registrarUsuario(String usuario, String clave) throws IOException {

        String claveCifrada = cifrado(clave);

        System.out.println(usuario + " " + clave);
        System.out.println(clave + claveCifrada);

        String registro = usuario + " " + claveCifrada;
        registrarEnArchivo(registro);

    }

    /**
     * Funcion encargada de hacer el cifrado de la Clave
     * @param clave
     * @return 
     */
    public String cifrado(String clave) {
        MessageDigest md;
        String out = "";
        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update((clave + salt).getBytes());
            byte[] mb = md.digest();

            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return out;
    }

    /**
     * Funcion encargada de registrar Usuario y Contraseña en una fila de un archivo.
     * @param registro
     * @throws IOException 
     */
    public void registrarEnArchivo(String registro) throws IOException {
        String ruta = "claves.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo, true));
            bw.write(registro + "\n");
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(registro + "\n");
        }
        bw.close();
    }

    /**
     * Funcion encargada de verificar si existe un usuario en el archivo.
     * @param usuario
     * @param clave
     * @return 
     */
    public String ingresarUsuario(String usuario, String clave) {

        String claveCifrada = cifrado(clave);
        String resultado = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            
            archivo = new File("claves.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            //Comprobacion de Usuario y contraseña por linea
            while ((linea = br.readLine()) != null) {

                String[] bar = linea.split(" ");
                //Comprobacion de Usuario y contraseña
                if (bar[0].equals(usuario)&& bar[1].equals(claveCifrada)) {
                    System.out.println("Usuario Encontrado");
                    resultado = "ENCONTRADO";
                }
                else if (bar[0].equals(usuario)&& bar[1]!= claveCifrada) {
                    System.out.println("Contraseña ERRONEA");
                    resultado = "CONTRASEÑA ERRONEA";
                }
                else{
                    System.out.println("USUARIO NO ENCONTRADO");
                    resultado = "USUARIO NO ENCONTRADO";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return resultado;
    }
}
