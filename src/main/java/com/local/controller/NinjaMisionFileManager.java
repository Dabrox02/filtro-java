package com.local.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.local.model.Mision;

public abstract class NinjaMisionFileManager {

    public static final String FILENAME = "misionesCompletadas.dat";

    /**
     * Retorna la lista de misiones completadas almacenadas en el archivo
     * 
     * @return List<Mision>
     */
    public static List<Mision> leerMisionesCompletadas() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Mision> misiones = (List<Mision>) ois.readObject();
                return misiones;
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error al leer misiones completadas: " + e.getMessage());
            }
        }
        return null;
    }

    /**
     * Guarda una lista de misiones en el archivo y retorna un booleano
     * 
     * @param List<Mision> misiones
     * 
     * @return boolean
     */
    public static boolean escribirMisionesCompletadas(List<Mision> misiones) {
        File file = new File(FILENAME);

        if (file.exists()) {
            if (misiones != null && !misiones.isEmpty()) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(misiones);
                    System.out.println("Misiones almacenadas");
                    return true;
                } catch (Exception e) {
                    System.out.println("Ha ocurrido un error al escribir misiones completadas: " + e.getMessage());
                }
            }
        }
        return false;
    }

}
