package org.example;

import java.util.*;

/**
 * Esta clase se encarga de ejecutar el algoritmo de Dijkstra.
 */
public class Dijkstra {
    private Map<String, Map<String, Double>> grafo;

    public Dijkstra(Map<String, Map<String, Double>> grafo) {
        this.grafo = grafo;
    }

    public Map<String, Double> calcularRutasDesde(String origen) {
        Map<String, Double> distancias = new HashMap<>();
        PriorityQueue<Map.Entry<String, Double>> cola = new PriorityQueue<>(Map.Entry.comparingByValue());

        // Inicializa las distancias
        for (String v : grafo.keySet()) {
            if (v.equals(origen)) {
                distancias.put(v, 0.0);
            } else {
                distancias.put(v, Double.POSITIVE_INFINITY);
            }
            cola.offer(new AbstractMap.SimpleEntry<>(v, distancias.get(v)));
        }

        while (!cola.isEmpty()) {
            String u = cola.poll().getKey();

            // Considerar nodos ya "visitados"
            if (distancias.get(u) == Double.POSITIVE_INFINITY) {
                break;
            }

            // Relajar aristas
            for (Map.Entry<String, Double> a : grafo.getOrDefault(u, Collections.emptyMap()).entrySet()) {
                String v = a.getKey();
                double peso = a.getValue();
                double distanciaPorU = distancias.get(u) + peso;
                if (distanciaPorU < distancias.get(v)) {
                    distancias.put(v, distanciaPorU);
                    cola.offer(new AbstractMap.SimpleEntry<>(v, distancias.get(v)));
                }
            }
        }

        return distancias;
    }
}
