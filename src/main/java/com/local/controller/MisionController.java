package com.local.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.local.model.Mision;
import com.local.persistence.ConexionBD;
import com.local.persistence.Operaciones;

public abstract class MisionController implements Controller {

    /**
     * Crea y almaena en la base de datos una mision
     * 
     * @param String descripcionMision
     * @param String rangoMision
     * @param double recompensaMision
     * 
     * @return boolean
     */
    public static boolean saveMision(String descripcionMision, String rangoMision, double recompensaMision) {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "INSERT INTO mision VALUES(NULL,?,?,?);";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setString(1, descripcionMision);
            stm.setString(2, rangoMision);
            stm.setDouble(3, recompensaMision);
            int filas = Operaciones.insertarActualizarEliminar(stm);
            return filas >= 1;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return false;
    }

    /**
     * Retorna la mision seleccionada
     * 
     * @param int misionId
     * 
     * @return Mision
     */
    public static Mision getMisionById(int misionId) {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT * FROM mision WHERE misionId = ?;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setInt(1, misionId);
            ResultSet rs = Operaciones.consultar(stm);
            Mision mision = new Mision();
            while (rs.next()) {
                mision.setMisionId(rs.getInt(1));
                mision.setDescripcionMision(rs.getString(2));
                mision.setRangoMision(rs.getString(3));
                mision.setRecompensaMision(rs.getDouble(4));
            }
            return mision;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

    /**
     * Crea y almaena en la base de datos una mision
     * 
     * @param String descripcionMision
     * @param String rangoMision
     * @param double recompensaMision
     * 
     * @return boolean
     */
    public static boolean finalizarMision(int misionId, LocalDate fechaFin) {
        List<Mision> misionesSinFinalizar = NinjaMisionController.misionesSinFinalizar();
        List<Mision> misionValida = misionesSinFinalizar.stream().filter(e -> {
            return e.getMisionId() == misionId;
        }).collect(Collectors.toList());

        if (misionValida.isEmpty()) {
            System.out.println("La mision ya fue finaliada");
            return false;
        }

        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "UPDATE misionNinja SET fechaFin = ? WHERE misionId = ?;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setString(1, fechaFin.toString());
            stm.setInt(2, misionId);
            int filas = Operaciones.insertarActualizarEliminar(stm);
            return filas >= 1;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return false;
    }

}
