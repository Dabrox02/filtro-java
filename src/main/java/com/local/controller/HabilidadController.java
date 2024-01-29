package com.local.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.local.model.Habilidad;
import com.local.model.Ninja;
import com.local.persistence.ConexionBD;
import com.local.persistence.Operaciones;

public abstract class HabilidadController implements Controller{

    /**
     * Agrega un ninja a la base de datos
     * 
     * @param String          nombreNinja
     * @param String          rangoNinja
     * @param String          aldeaNinja
     * @param List<Habilidad> habilidades
     * 
     * @return boolean
     */
    public static boolean saveHabilidad(int ninjaId, String nombreHabilidad, String descripcionHabilidad) {
        Ninja ninja = NinjaController.getNinjaById(ninjaId);
        if (ninja == null)
            return false;

        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "INSERT INTO habilidad VALUES(?,?,?);";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setInt(1, ninjaId);
            stm.setString(2, nombreHabilidad);
            stm.setString(3, descripcionHabilidad);
            int filas = Operaciones.insertarActualizarEliminar(stm);
            return filas >= 1;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return false;
    }

    /**
     * Retorna una lista de habilidades de acuerdo al ninja seleccionado
     * 
     * @param int ninjaId
     * 
     * @return List<Habilidad>
     */
    public static List<Habilidad> getHabilidadesNinja(int ninjaId) {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT * FROM habilidad WHERE ninjaId = ?;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setInt(1, ninjaId);
            ResultSet rs = Operaciones.consultar(stm);
            List<Habilidad> listaHabilidades = new ArrayList<>();
            while (rs.next()) {
                listaHabilidades.add(
                        new Habilidad(rs.getString(2), rs.getString(3)));
            }
            return listaHabilidades;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

}
