package com.local.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.local.model.Habilidad;
import com.local.model.Mision;
import com.local.model.Ninja;
import com.local.model.RangoEnum;
import com.local.persistence.ConexionBD;
import com.local.persistence.Operaciones;

public abstract class NinjaController implements Controller {

    /**
     * Retorna la lista completa de ninjas de la base de datos
     * 
     * @return List<Ninja>
     */
    public static List<Ninja> listarNinjas() {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT * FROM ninja;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            ResultSet rs = Operaciones.consultar(stm);
            List<Ninja> listaNinjas = new ArrayList<>();
            while (rs.next()) {
                listaNinjas.add(
                        new Ninja(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            // Agrega Habilidades a cada ninja
            listaNinjas.stream().forEach((ninja) -> {
                List<Habilidad> habilidades = HabilidadController.getHabilidadesNinja(ninja.getNinjaId());
                ninja.setHabilidades(habilidades);
            });
            return listaNinjas;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna una lista de misiones disponibles para el ninja seleccionado
     * 
     * @param int ninjaId
     * 
     * @return List<Mision>
     */
    public static List<Mision> misionesDisponiblesNinja(int ninjaId) {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT mi.* FROM mision mi LEFT JOIN misionNinja mn ON mi.misionId = mn.misionId WHERE mn.misionId IS NULL AND mi.rangoMision = (SELECT rangoNinja FROM ninja WHERE ninjaId = ?);";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setInt(1, ninjaId);
            ResultSet rs = Operaciones.consultar(stm);
            List<Mision> listaMisiones = new ArrayList<>();
            while (rs.next()) {
                listaMisiones.add(new Mision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
            return listaMisiones;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna una lista de misiones completadas por el ninja seleccionado
     * 
     * @param int ninjaId
     * 
     * @return List<Mision>
     */
    public static List<Mision> misionesCompletadasNinja(int ninjaId) {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT mi.* FROM mision mi LEFT JOIN misionNinja mn ON mi.misionId = mn.misionId WHERE mn.fechaFin IS NOT NULL AND mn.ninjaId = ?;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setInt(1, ninjaId);
            ResultSet rs = Operaciones.consultar(stm);
            List<Mision> listaMisiones = new ArrayList<>();
            while (rs.next()) {
                listaMisiones.add(new Mision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
            return listaMisiones;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

    /**
     * Retorna un ninja buscado por medio del Id
     * 
     * @param int ninjaId
     * 
     * @return Ninja
     */
    public static Ninja getNinjaById(int ninjaId) {
        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "SELECT * FROM ninja WHERE ninjaId = ?;";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setInt(1, ninjaId);
            ResultSet rs = Operaciones.consultar(stm);
            Ninja ninja = new Ninja();
            while (rs.next()) {
                ninja.setNinjaId(rs.getInt(1));
                ninja.setNombreNinja(rs.getString(2));
                ninja.setRangoNinja(rs.getString(3));
                ninja.setAldeaNinja(rs.getString(4));
            }
            return ninja;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return null;
    }

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
    public static boolean saveNinja(String nombreNinja, String rangoNinja, String aldeaNinja,
            List<Habilidad> habilidades) {

        if (RangoEnum.getRangoEnum(rangoNinja) == null)
            return false;

        Operaciones.setConnection(ConexionBD.MYSQLConexion());
        String sqlStm = "INSERT INTO ninja VALUES(NULL, ?, ?, ?);";
        try (PreparedStatement stm = Operaciones.getConnection().prepareStatement(sqlStm)) {
            stm.setString(1, nombreNinja);
            stm.setString(2, rangoNinja);
            stm.setString(3, aldeaNinja);
            int filas = Operaciones.insertarActualizarEliminar(stm);
            if (filas >= 1) {
                if (habilidades != null) {
                    habilidades.stream().forEach((hab) -> {
                        HabilidadController.saveHabilidad(hab.getNinjaId(), hab.getNombreHabilidad(),
                                hab.getDescripcionHabilidad());
                    });
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al listar " + e.getMessage());
        }
        return false;
    }
}
