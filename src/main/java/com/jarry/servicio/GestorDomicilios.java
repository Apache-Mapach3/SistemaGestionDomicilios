package servicio;

import modelo.Domicilio;

import java.util.*;

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
}