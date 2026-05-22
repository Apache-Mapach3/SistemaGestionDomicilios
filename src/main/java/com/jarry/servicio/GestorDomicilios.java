package servicio;
import modelo.Domicilio;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

public class GestorDomicilios {

    // LIST -> registro general
    private List<Domicilio> domicilios;

    // QUEUE -> pendientes
    private Queue<Domicilio> pendientes;

    // DEQUE -> historial
    private Deque<Domicilio> historial;

    // MAP -> busqueda rápida
    private Map<String, Domicilio> indicePorOrden;

    public GestorDomicilios() {

        domicilios = new ArrayList<>();

        pendientes = new LinkedList<>();

        historial = new ArrayDeque<>();

        indicePorOrden = new HashMap<>();
    }

    // Registrar domicilio
    public void registrarDomicilio(Domicilio domicilio) {

        // Validar duplicados
        if (indicePorOrden.containsKey(domicilio.getNumeroOrden())) {
            throw new IllegalArgumentException(
                    "Ya existe un domicilio con ese numero de orden."
            );
        }

        domicilio.setEstado("PENDIENTE");

        domicilios.add(domicilio);

        pendientes.offer(domicilio);

        indicePorOrden.put(
                domicilio.getNumeroOrden(),
                domicilio
        );

        System.out.println("Domicilio registrado correctamente.");
    }

    // Procesar siguiente domicilio
    public void procesarSiguienteDomicilio() {

        Domicilio procesado = pendientes.poll();

        if (procesado == null) {
            throw new IllegalStateException(
                    "No hay domicilios pendientes."
            );
        }

        procesado.setEstado("PROCESADO");

        historial.push(procesado);

        System.out.println("Domicilio procesado correctamente.");
    }

    public void verPendientes() {

        if (pendientes.isEmpty()) {
            System.out.println("No hay domicilios pendientes.");
            return;
        }

        pendientes.forEach(System.out::println);

        System.out.println("Cantidad pendientes: " + pendientes.size());

        System.out.println("Siguiente pendiente: " + pendientes.peek());
    }

    public void verHistorial() {

        if (historial.isEmpty()) {
            System.out.println("No hay historial.");
            return;
        }

        historial.forEach(System.out::println);

        System.out.println("Cantidad procesados: " + historial.size());

        System.out.println("Ultimo procesado: " + historial.peek());
    }

    // Buscar domicilio por numero de orden usando Map
    public Domicilio buscarPorNumeroOrden(String numeroOrden) {

        if (!indicePorOrden.containsKey(numeroOrden)) {
            throw new IllegalArgumentException(
                    "No existe un domicilio con ese numero de orden."
            );
        }

        return indicePorOrden.get(numeroOrden);
    }

    // Buscar domicilio por nombre usando Stream
    public Optional<Domicilio> buscarPorCliente(String nombreCliente) {

        return domicilios.stream()
                .filter(d -> d.getNombreCliente()
                        .equalsIgnoreCase(nombreCliente))
                .findFirst();
    }

    // Buscar domicilio por nombre usando Stream
    public Optional<Domicilio> buscarPorCliente(String nombreCliente) {

        return domicilios.stream()
                .filter(d -> d.getNombreCliente()
                        .equalsIgnoreCase(nombreCliente))
                .findFirst();
    }

    public void mostrarCantidadElementos() {

        System.out.println("Total registrados: " + domicilios.size());

        System.out.println("Pendientes: " + pendientes.size());

        System.out.println("Procesados: " + historial.size());

        System.out.println("Total indice Map: " + indicePorOrden.size());
    }

    // Filtrar domicilios por estado
    public List<Domicilio> filtrarPorEstado(String estado) {

        return domicilios.stream()
                .filter(d -> d.getEstado()
                        .equalsIgnoreCase(estado))
                .toList();
    }

    // Ordenar por nombre del cliente
    public List<Domicilio> ordenarPorCliente() {

        return domicilios.stream()
                .sorted(Comparator.comparing(
                        Domicilio::getNombreCliente
                ))
                .toList();
    }

    // Ordenar por numero de orden descendente
    public List<Domicilio> ordenarPorNumeroDesc() {

        return domicilios.stream()
                .sorted(
                        Comparator.comparing(
                                Domicilio::getNumeroOrden
                        ).reversed()
                )
                .toList();
    }

    // Estadisticas por estado
    public Map<String, Long> estadisticasPorEstado() {

        return domicilios.stream()
                .collect(
                        Collectors.groupingBy(
                                Domicilio::getEstado,
                                Collectors.counting()
                        )
                );
    }

    // Agrupar domicilios por categoria
    public Map<String, List<Domicilio>> agruparPorCategoria() {

        return domicilios.stream()
                .collect(
                        Collectors.groupingBy(
                                Domicilio::getCategoria
                        )
                );
    }

    // Verificar si existen pendientes
    public boolean existenPendientes() {

        return domicilios.stream()
                .anyMatch(d ->
                        d.getEstado()
                                .equalsIgnoreCase("PENDIENTE"));
    }

    // Verificar que todos tengan numero de orden
    public boolean todosTienenNumeroOrden() {

        return domicilios.stream()
                .allMatch(d ->
                        d.getNumeroOrden() != null
                                && !d.getNumeroOrden().isBlank());
    }

    // Verificar que no existan cancelados
    public boolean noHayCancelados() {

        return domicilios.stream()
                .noneMatch(d ->
                        d.getEstado()
                                .equalsIgnoreCase("CANCELADO"));
    }

    // Cancelar domicilio pendiente
    public void cancelarDomicilio(String numeroOrden) {

        Domicilio domicilio = indicePorOrden.get(numeroOrden);

        if (domicilio == null) {
            throw new IllegalArgumentException(
                    "No existe un domicilio con ese numero de orden."
            );
        }

        if (!domicilio.getEstado()
                .equalsIgnoreCase("PENDIENTE")) {

            throw new IllegalStateException(
                    "Solo se pueden cancelar domicilios pendientes."
            );
        }

        domicilio.setEstado("CANCELADO");

        pendientes.removeIf(d ->
                d.getNumeroOrden()
                        .equalsIgnoreCase(numeroOrden));

        System.out.println("Domicilio cancelado correctamente.");
    }

    // Deshacer ultimo procesamiento
    public void deshacerUltimoProcesamiento() {

        if (historial.isEmpty()) {

            throw new IllegalStateException(
                    "No hay procesamientos para deshacer."
            );
        }

        Domicilio ultimo = historial.pop();

        ultimo.setEstado("PENDIENTE");

        pendientes.offer(ultimo);

        System.out.println(
                "Ultimo procesamiento deshecho correctamente."
        );
    }


}