/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Nahuel
 */
public class Cesar {

    /**
     * Funcion encargada de recibir la frase a Cifrar
     *
     * @param frase frase ha aplicar el algoritmo
     * @param desp numero de desplazamientos del algoritmo
     * @return frase cifrada
     */
    public String cifradoCesar(String frase, int desp) {

        String cifrada = "";

        for (int x = 0; x < frase.length(); x++) {

            //Verifico que el caracter de la frase no sea un espacio.
            if (frase.codePointAt(x) != 32) {
                String letra = "" + frase.charAt(x);
                cifrada = cifrada + pasajeCesar(letra, desp);
            } else {
                cifrada = cifrada + " ";
            }

        }

        return cifrada;
    }

    /**
     * Funcion encargada de realizar el desplazamiento de cada caracter de una
     * frase
     *
     * @param letra caracter a realizar corrimiento
     * @param desp cantidad de desplazamientos del caracter
     * @return caracter resultante del desplazamiento
     */
    public String pasajeCesar(String letra, int desp) {

        int numeroAscii = letra.charAt(0);

        //Compruebo que no se pase del abacedario antes de cifrar el caracter
        int comp = numeroAscii + desp;
        
        if (comp > 122){
            numeroAscii = numeroAscii - 26;
        }
                
        //Al codigo ASCII le sumo el desplazamiento y retorno el caracter obtenido
        String result = new Character((char) (numeroAscii + desp)).toString();

        return result;
    }
    
    /**
     * Funcion encargada de recibir la frase a Descifrar
     *
     * @param frase frase ha aplicar el algoritmo
     * @param desp numero de desplazamientos del algoritmo
     * @return frase cifrada
     */
    public String descifradoCesar(String frase, int desp) {

        String cifrada = "";

        for (int x = 0; x < frase.length(); x++) {

            //Verifico que el caracter de la frase no sea un espacio.
            if (frase.codePointAt(x) != 32) {
                String letra = "" + frase.charAt(x);
                cifrada = cifrada + depasajeCesar(letra, desp);
            } else {
                cifrada = cifrada + " ";
            }

        }

        return cifrada;
    }

    /**
     * Funcion encargada de realizar el desplazamiento inverso de cada caracter de una
     * frase
     *
     * @param letra caracter a realizar corrimiento
     * @param desp cantidad de desplazamientos del caracter
     * @return caracter resultante del desplazamiento
     */
    public String depasajeCesar(String letra, int desp) {

        int numeroAscii = letra.charAt(0);

        //Compruebo que no se pase del abacedario antes de cifrar el caracter
        int comp = numeroAscii - desp;
        
        if (comp < 97){
            
           numeroAscii = numeroAscii + 26; 
        }
                        
        //Al codigo ASCII le sumo el desplazamiento y retorno el caracter obtenido
        String result = new Character((char) (numeroAscii - desp)).toString();

        return result;
    }
    
    public void fuerzaBruta(String frase) throws IOException{
        String resultado;
        String ruta = "archivo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        
            bw = new BufferedWriter(new FileWriter(archivo));
                     
                
        for(int i =1; i<27; i++){
            resultado = descifradoCesar(frase, i);
            bw.write(i +" - " +resultado+ "\n");
            
            //System.out.println(i +" - " +resultado);
            
        }
        bw.close();
    }
}
