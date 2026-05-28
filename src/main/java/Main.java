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

            System.out.println("""
                    
                   
                     SISTEMA DE GESTION DE DOMICILIOS
                    
                    1. Registrar domicilio
                    2. Ver todos los domicilios
                    3. Ver domicilios pendientes
                    4. Procesar siguiente domicilio
                    5. Ver historial
                    6. Buscar por numero de orden
                    7. Buscar por cliente
                    8. Filtrar por estado
                    9. Ordenar por cliente
                    10. Ver estadisticas
                    11. Ver agrupamientos
                    12. Cancelar domicilio
                    13. Deshacer ultimo procesamiento
                    14. Ver cantidades
                    15. Salir
                    
                    """);

            System.out.print("Seleccione una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            try {

                switch (opcion) {

                    case 1: {

                        System.out.print("Numero de orden: ");
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
                    }

                    case 2: {

                        gestor.verTodosLosDomicilios();

                        break;
                    }

                    case 3: {

                        gestor.verPendientes();

                        break;
                    }

                    case 4: {

                        gestor.procesarSiguienteDomicilio();

                        break;
                    }

                    case 5: {

                        gestor.verHistorial();

                        break;
                    }

                    case 6: {

                        System.out.print("Ingrese numero de orden: ");

                        String buscarOrden = scanner.nextLine();

                        Domicilio encontrado =
                                gestor.buscarPorNumeroOrden(buscarOrden);

                        System.out.println(encontrado);

                        break;
                    }

                    case 7: {

                        System.out.print("Ingrese nombre cliente: ");

                        String buscarCliente = scanner.nextLine();

                        Optional<Domicilio> resultado =
                                gestor.buscarPorCliente(buscarCliente);

                        if (resultado.isPresent()) {

                            System.out.println(resultado.get());

                        } else {

                            System.out.println("No encontrado.");
                        }

                        break;
                    }

                    case 8: {

                        System.out.print("Ingrese estado: ");

                        String estado = scanner.nextLine();

                        List<Domicilio> filtrados =
                                gestor.filtrarPorEstado(estado);

                        filtrados.forEach(System.out::println);

                        break;
                    }

                    case 9: {

                        List<Domicilio> ordenados =
                                gestor.ordenarPorCliente();

                        ordenados.forEach(System.out::println);

                        break;
                    }

                    case 10: {

                        Map<String, Long> estadisticas =
                                gestor.estadisticasPorEstado();

                        estadisticas.forEach((clave, valor) ->
                                System.out.println(clave + ": " + valor));

                        break;
                    }

                    case 11: {

                        Map<String, List<Domicilio>> agrupados =
                                gestor.agruparPorCategoria();

                        agrupados.forEach((nombreCategoria, listaDomicilios) -> {

                            System.out.println("\nCategoria: " + nombreCategoria);

                            listaDomicilios.forEach(System.out::println);
                        });

                        break;
                    }

                    case 12: {

                        System.out.print("Ingrese numero de orden: ");

                        String cancelarOrden = scanner.nextLine();

                        gestor.cancelarDomicilio(cancelarOrden);

                        break;
                    }

                    case 13: {

                        gestor.deshacerUltimoProcesamiento();

                        break;
                    }

                    case 14: {

                        gestor.mostrarCantidadElementos();

                        break;
                    }

                    case 15: {

                        System.out.println("Saliendo del sistema...");

                        break;
                    }

                    default: {

                        System.out.println("Opcion invalida.");
                    }
                }

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 15);

        scanner.close();
    }
}