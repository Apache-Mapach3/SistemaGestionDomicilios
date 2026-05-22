package com.jarry;
import modelo.Domicilio;
import servicio.GestorDomicilios;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GestorDomicilios gestor = new GestorDomicilios();

        int opcion;

        do {

            System.out.println("\n SISTEMA DE GESTION DE DOMICILIOS ");

            System.out.println("1. Registrar domicilio");
            System.out.println("2. Ver todos los domicilios");
            System.out.println("3. Ver domicilios pendientes");
            System.out.println("4. Procesar siguiente domicilio");
            System.out.println("5. Ver historial");
            System.out.println("6. Buscar por numero de orden");
            System.out.println("7. Buscar por cliente");
            System.out.println("8. Filtrar por estado");
            System.out.println("9. Ordenar por cliente");
            System.out.println("10. Ver estadisticas");
            System.out.println("11. Ver agrupamientos");
            System.out.println("12. Cancelar domicilio");
            System.out.println("13. Deshacer ultimo procesamiento");
            System.out.println("14. Ver cantidades");
            System.out.println("15. Salir");

            System.out.print("Seleccione una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcion) {

                    case 1: System.out.print("Numero de orden: ");
                        String numeroOrden = scanner.nextLine();

                        System.out.print("Nombre cliente: ");
                        String cliente = scanner.nextLine();

                        System.out.print("Direccion: ");
                        String direccion = scanner.nextLine();

                        System.out.print("Telefono: ");
                        String telefono = scanner.nextLine();

                        System.out.print("Descripcion pedido: ");
                        String descripcion = scanner.nextLine();

                        System.out.print("Categoria: ");
                        String categoria = scanner.nextLine();

                        System.out.print("Costo: ");
                        double costo = Double.parseDouble(scanner.nextLine());

                        Domicilio domicilio = new Domicilio(
                                numeroOrden,
                                cliente,
                                direccion,
                                telefono,
                                descripcion,
                                categoria,
                                costo,
                                "PENDIENTE"
                        );

                        gestor.registrarDomicilio(domicilio);

                        break;

                        break;

                    case 2: gestor.verTodosLosDomicilios();
                        break;

                        break;

                    case 3: gestor.verPendientes();
                        break;

                        break;

                    case 4: gestor.procesarSiguienteDomicilio();
                        break;

                        break;

                    case 5:

                        break;

                    case 6:

                        break;

                    case 7:

                        break;

                    case 8:

                        break;

                    case 9:

                        break;

                    case 10:

                        break;

                    case 11:

                        break;

                    case 12:

                        break;

                    case 13:

                        break;

                    case 14:

                        break;

                    case 15:

                        System.out.println("Saliendo del sistema...");
                        break;

                    default:

                        System.out.println("Opcion invalida.");
                }

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 15);

        scanner.close();
    }
}