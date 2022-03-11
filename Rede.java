/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip;

import java.util.Arrays;

/**
 *
 * @author apazpere
 */
public class Rede {

    public static void main(String[] args) {
        // Declaración de variables y constantes
        String ip = "192.168.0.9.";
        validarIP(ip);
        // Entrada de datos
        // Proceso
        // Salida
    }

    public static boolean validarIP(String ip) {

        String cerosIzq = "00";
        boolean esValida = true;
        int i = 0;
        final int NUM_PUNTOS = 3;
        int cuentaPuntos = 0;

        //Si la ip no tiene nada pues no es válida
        if (ip.length() == 0) {
            esValida = false;
        } else {

            do {
                //Contamos los puntos
                if (ip.charAt(i) == '.') {
                    cuentaPuntos++;
                }
                //Con esto verificamos si hay alguna letra
                if (Character.isLetter(ip.charAt(i))) {
                    esValida = false;
                }
                i++;
            } while (i < ip.length() && esValida);

            //Si no hay 3 puntos mal
            if (cuentaPuntos != NUM_PUNTOS) {
                esValida = false;
            }
            if (esValida) {
                //Convertimos la ip a array para comparar
                ip = ip.replace(".", " ");
                String octetos[];
                octetos = ip.split(" ");

                //Si el array no tiene 4 octetos, tampoco es válida
                if (octetos.length != 4) {
                    esValida = false;
                } else {
                    //Recorremos array para comprobar ceros
                    int octeto;
                    for (int j = 0; j < octetos.length; j++) {
                        if (octetos[j].equals("")) {
                            esValida = false;
                            break;

                        } else {
                            //Si hay ceros a la izquierda es falso
                            if (octetos[j].length() > 1 && octetos[j].charAt(0) == '0') {
                                esValida = false;
                            } else {
                                //Si no está en el rango de valores es falso
                                octeto = Integer.parseInt(octetos[j]);
                                if (octeto < 0 || octeto > 255) {
                                    esValida = false;
                                    break;
                                }
                            }

                        }

                    }
                }
            }

        }

        return esValida;
    }

    public static boolean validarMascara(String mascara) {
        //Primero comprobamos con la función validar ip si no tiene letras, 0 a la izquierda o puntos mal colocados
        boolean esValida = validarIP(mascara);
        boolean ok;
        if (esValida) {
            String[] valores = {"0","128","192","224", "248", "252", "254", "255"};
            mascara = mascara.replace(".", " ");
            String octetos[];
            octetos = mascara.split(" ");
            
            //Si está el primer octeto, todo bien
            if (octetos[0].equals("255")) {
                //Comparamos si el resto de octetos tienen alguno de los valores permitidos
                for (int i = 1; i < octetos[i].length(); i++) {
                    ok = false;
                    //Recorremos el array de valores
                    for (int j = 0; j < valores.length; j++) {
                        //Si coincide, subimos bandera
                        if (octetos[i].equals(valores[j])) {
                            ok = true;
                        } 
                    }
                    //Si no ha coincidido, significa que la máscara no es válida
                    if (!ok) {
                        esValida = false;
                    }
                }
            } else {
                esValida = false;
            }

        }
        return esValida;
    }

    public static int buscarIP(String ip, String[] ips) {

        int pos;
        try {
            for (int i = 0; i < ips.length; i++) {
                if (ips[i] == ip) {
                    pos = i;
                    return pos;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

}
