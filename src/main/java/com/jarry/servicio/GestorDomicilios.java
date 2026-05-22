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
}