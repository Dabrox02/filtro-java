package com.local.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.local.model.Mision;
import com.local.model.Ninja;
import com.local.persistence.ConexionBD;
import com.local.persistence.Operaciones;

public abstract class NinjaMisionController implements Controller {

    public static boolean asignarMision(int ninjaId, int misionId, LocalDate fechaInicio) {
        Ninja ninja = NinjaController.getNinjaById(ninjaId);
        Mision mision = MisionController.getMisionById(misionId);

        if (ninja != null && mision != null) {
            Operaciones.setConnection(ConexionBD.MYSQLConexion());
            String sqlStm = "INSERT INTO misionNinja VALUES (?,?,?,NULL);";
            try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
                stm.setInt(1, ninjaId);
                stm.setInt(2, misionId);
                stm.setString(3, fechaInicio.toString());
                int filas = Operaciones.insertarActualizarEliminar(stm);
                return filas >= 1;
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error al listar " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Retorna misiones finalizadas
     * 
     * @return List<Mision>
     */
    public static List<Mision> misionesFinalizadas() {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT DISTINCT mi.* FROM mision mi LEFT JOIN misionNinja mn ON mi.misionId = mn.misionId WHERE mn.fechaFin IS NOT NULL;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            ResultSet rs = Operaciones.consultar(stm);
            List<Mision> misiones = new ArrayList<>();
            while (rs.next()) {
                Mision mision = new Mision();
                mision.setMisionId(rs.getInt(1));
                mision.setDescripcionMision(rs.getString(2));
                mision.setRangoMision(rs.getString(3));
                mision.setRecompensaMision(rs.getDouble(4));
                misiones.add(mision);
            }
            return misiones;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna misiones sin finalizar
     * 
     * @return List<Mision>
     */
    public static List<Mision> misionesSinFinalizar() {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT DISTINCT mi.* FROM mision mi LEFT JOIN misionNinja mn ON mi.misionId = mn.misionId WHERE mn.fechaFin IS NULL AND mn.ninjaId IS NOT NULL;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            ResultSet rs = Operaciones.consultar(stm);
            List<Mision> misiones = new ArrayList<>();
            while (rs.next()) {
                Mision mision = new Mision();
                mision.setMisionId(rs.getInt(1));
                mision.setDescripcionMision(rs.getString(2));
                mision.setRangoMision(rs.getString(3));
                mision.setRecompensaMision(rs.getDouble(4));
                misiones.add(mision);
            }
            return misiones;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza archivo misiones completadas
     * 
     * @return boolean
     */
    public static boolean actualizarMisionesCompletadas() {
        List<Mision> misionesCompletadas = NinjaMisionController.misionesFinalizadas();
        return NinjaMisionFileManager.escribirMisionesCompletadas(misionesCompletadas);
    }

}
