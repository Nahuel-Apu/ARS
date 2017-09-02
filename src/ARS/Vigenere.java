/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARS;

/**
 *
 * @author Nahuel
 */
public class Vigenere {

    public String cifradoVigenere(String frase, String clave) {
        
        String cifrada = "";
        int posClv = 0;

        for (int x = 0; x < frase.length(); x++) {

            if (frase.codePointAt(x) != 32) {

                String letra = "" + frase.charAt(x);
                String letraClave = "" + clave.charAt(posClv);

                cifrada = cifrada + pasajeVig(letra, letraClave);

                if (posClv == (clave.length() - 1)) {
                    posClv = 0;

                } else {
                    posClv++;
                }

            } else {
                cifrada = cifrada + " ";
            }

        }

        return cifrada;
    }

    public String pasajeVig(String letra, String letraClave) {

        int numeroAscii = letra.charAt(0) - 97;

        int numeroLetraClv = letraClave.charAt(0) - 97;

        String result = new Character((char) (((numeroAscii + numeroLetraClv) % 26) + 97)).toString();

        return result;
    }

    public String descifradoVigenere(String cifrado, String clave) {
        String cifrada = "";
        int posClv = 0;

        for (int x = 0; x < cifrado.length(); x++) {

            if (cifrado.codePointAt(x) != 32) {

                String letra = "" + cifrado.charAt(x);
                String letraClave = "" + clave.charAt(posClv);

                cifrada = cifrada + despasajeVig(letra, letraClave);

                if (posClv == (clave.length() - 1)) {
                    posClv = 0;

                } else {
                    posClv++;
                }

            } else {
                cifrada = cifrada + " ";
            }

        }

        return cifrada;
    }

    public String despasajeVig(String letra, String letraClave) {

        int numeroAscii = letra.charAt(0) - 97;

        int numeroLetraClv = letraClave.charAt(0) - 97;

        String result = new Character((char) (((( numeroAscii - numeroLetraClv) + 26) % 26) + 97)).toString();

        return result;
    }

}
