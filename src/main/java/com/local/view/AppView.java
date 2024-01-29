package com.local.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.local.controller.HabilidadController;
import com.local.controller.MisionController;
import com.local.controller.NinjaController;
import com.local.controller.NinjaMisionController;
import com.local.controller.NinjaMisionFileManager;
import com.local.model.Habilidad;
import com.local.model.Mision;
import com.local.model.Ninja;

public class AppView {

    public void iniciarVista() {
        boolean sistema = true;
        Scanner entrada = new Scanner(System.in);
        while (sistema) {
            try {
                menuPrincipal();
                int opc = Integer.parseInt(entrada.nextLine());
                switch (opc) {
                    case 1:
                        vistaNinja();
                        break;
                    case 2:
                        vistaMision();
                        break;
                    case 3:
                        List<Ninja> listaNinjas = NinjaController.listarNinjas();
                        listaNinjas.stream().forEach((e) -> {
                            System.out.println("Id ninja: " + e.getNinjaId() + " Ninja: " + e.getNombreNinja());
                        });
                        System.out.println("Asignar mision, ingrese el id del ninja: ");
                        int ninjaId = Integer.parseInt(entrada.nextLine());
                        List<Mision> listaMisiones = NinjaMisionController.misionesSinFinalizar();
                        listaMisiones.stream().forEach((e) -> {
                            System.out.println(
                                    "Mision id: " + e.getMisionId() + " Descripcion: " + e.getDescripcionMision());
                        });
                        System.out.println("Asignar mision, ingrese el id de la mision: ");
                        int misionId = Integer.parseInt(entrada.nextLine());
                        System.out.println("Ingrese la fecha iniio formato YYYY-MM-DD: ");
                        LocalDate fechaInicio = LocalDate.parse(entrada.nextLine());

                        if (NinjaMisionController.asignarMision(ninjaId, misionId, fechaInicio)) {
                            System.out.println("Se asigno la mision exitosamente");
                        } else {
                            System.out.println("Ocurrio un error al asignar mision");
                        }
                        break;
                    case 4:
                        sistema = false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
        entrada.close();
    }

    public void vistaNinja() {
        boolean sistema = true;
        Scanner entrada = new Scanner(System.in);
        while (sistema) {
            try {
                List<Ninja> listaNinjas = NinjaController.listarNinjas();
                menuNinja();
                int opc = Integer.parseInt(entrada.nextLine());
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese los datos del ninja: ");
                        System.out.print("Nombre Ninja: ");
                        String nombreNinja = entrada.nextLine();
                        System.out.print("Rango Ninja: ");
                        String rangoNinja = entrada.nextLine();
                        System.out.print("Aldea Ninja: ");
                        String aldeaNinja = entrada.nextLine();

                        if (NinjaController.saveNinja(nombreNinja, rangoNinja, aldeaNinja, null)) {
                            System.out.println("Se guardo exitosamente");
                        } else {
                            System.out.println("Ocurrio un error al guardar");
                        }
                        break;
                    case 2:
                        listaNinjas.stream().forEach((e) -> {
                            System.out.println("Id ninja: " + e.getNinjaId() + " Ninja: " + e.getNombreNinja());
                        });
                        System.out.println("Ingrese el id del ninja, para agregar habilidades: ");
                        int ninjaId = Integer.parseInt(entrada.nextLine());
                        List<Habilidad> habilidades = new ArrayList<>();
                        boolean agregarHabilidad = true;
                        while (agregarHabilidad) {
                            System.out.print("Nombre Habilidad: ");
                            String nombreHabilidad = entrada.nextLine();
                            System.out.print("Descripcion Habilidad: ");
                            String descripcionHabilidad = entrada.nextLine();
                            habilidades.add(new Habilidad(nombreHabilidad, descripcionHabilidad));
                            System.out.println("Desea agregar otra habilidad? S/N");
                            String siHabilidad = entrada.nextLine();
                            agregarHabilidad = siHabilidad.equalsIgnoreCase("s");
                        }
                        habilidades.stream().forEach((hab) -> {
                            HabilidadController.saveHabilidad(ninjaId, hab.getNombreHabilidad(),
                                    hab.getDescripcionHabilidad());
                        });
                        break;
                    case 3:
                        System.out.println("==== LISTADO DE NINJAS ====");
                        listaNinjas.stream().forEach(System.out::println);
                        break;
                    case 4:
                        listaNinjas.stream().forEach((e) -> {
                            System.out.println("Id ninja: " + e.getNinjaId() + " Ninja: " + e.getNombreNinja());
                        });
                        System.out.println("Misiones disponibles para el ninja, ingrese id ninja: ");
                        ninjaId = Integer.parseInt(entrada.nextLine());
                        List<Mision> misiones1 = NinjaController.misionesDisponiblesNinja(ninjaId);
                        if (misiones1 != null) {
                            misiones1.stream().forEach(System.out::println);
                        }
                        break;
                    case 5:
                        listaNinjas.stream().forEach((e) -> {
                            System.out.println("Id ninja: " + e.getNinjaId() + " Ninja: " + e.getNombreNinja());
                        });
                        System.out.println("Misiones completadas por el ninja, ingrese id ninja: ");
                        ninjaId = Integer.parseInt(entrada.nextLine());
                        List<Mision> misiones2 = NinjaController.misionesCompletadasNinja(ninjaId);
                        if (misiones2 != null) {
                            misiones2.stream().forEach(System.out::println);
                        }
                        break;
                    case 6:
                        sistema = false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
    }

    public void vistaMision() {
        boolean sistema = true;
        Scanner entrada = new Scanner(System.in);
        while (sistema) {
            try {
                NinjaMisionController.actualizarMisionesCompletadas();
                menuMision();
                int opc = Integer.parseInt(entrada.nextLine());
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese los datos de la mision: ");
                        System.out.print("Descripcion Mision: ");
                        String descripcionMision = entrada.nextLine();
                        System.out.print("Rango Mision: ");
                        String rangoMision = entrada.nextLine();
                        System.out.print("Recompensa Mision: ");
                        Double recompensaMision = Double.valueOf(entrada.nextLine());

                        if (MisionController.saveMision(descripcionMision, rangoMision, recompensaMision)) {
                            System.out.println("Se guardo exitosamente");
                        } else {
                            System.out.println("Ocurrio un error al guardar");
                        }
                        break;
                    case 2:
                        List<Mision> misiones1 = NinjaMisionFileManager.leerMisionesCompletadas();
                        misiones1.stream().forEach(System.out::println);
                        break;
                    case 3:
                        List<Mision> misiones2 = NinjaMisionController.misionesSinFinalizar();
                        misiones2.stream().forEach(e -> {
                            System.out.println(
                                    "Mision id: " + e.getMisionId() + " Descripcion: " + e.getDescripcionMision());
                        });
                        System.out.println("Ingrese el id de la mision a finalizar: ");
                        int misionId = Integer.parseInt(entrada.nextLine());
                        System.out.println("Ingrese la fecha fin formato YYYY-MM-DD: ");
                        LocalDate fechaFin = LocalDate.parse(entrada.nextLine());
                        if (MisionController.finalizarMision(misionId, fechaFin)) {
                            System.out.println("Se finalizo la mision");
                        } else {
                            System.out.println("Ocurrio un error al finalizar");
                        }
                        break;
                    case 4:
                        sistema = false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }
    }

    private void menuPrincipal() {
        System.out.println("""
                ==== MENU PRINCIPAL ===
                1. Menu Ninja
                2. Menu Mision
                3. Asignar mision a un ninja
                4. Finalizar programa
                Ingrese una opcion""");
    }

    private void menuNinja() {
        System.out.println("""
                ==== MENU NINJA ===
                1. Crear ninja
                2. Agregar Habilidades ninja
                3. Listar ninjas y sus habilidades
                4. Misiones para ninja disponible
                5. Misiones completadas por ninja
                6. Regresar
                Ingrese una opcion""");
    }

    private void menuMision() {
        System.out.println("""
                ==== MENU MISIONES ===
                1. Crear mision
                2. Mostrar misiones completadas desde archivo
                3. Finalizar mision
                4. Regresar
                        """);
    }

}
