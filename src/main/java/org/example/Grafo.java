package org.example;

import java.io.*;
import java.util.*;

/**
 * Esta clase maneja la creación y carga de datos del grafo.
 */
public class Grafo {
    private Map<String, Map<String, Double>> grafo = new HashMap<>();

    public Grafo() {
        cargarRutas();
    }

    private void cargarRutas() {
        try (Scanner scanner = new Scanner(new File(getClass().getClassLoader().getResource("rutas.txt").getFile()))) {
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().replace("\"", "").split(",");
                String origen = partes[0].trim();
                String destino = partes[1].trim();
                double peso = Double.parseDouble(partes[2].trim());

                grafo.putIfAbsent(origen, new HashMap<>());
                grafo.get(origen).put(destino, peso);
                grafo.putIfAbsent(destino, new HashMap<>());
                grafo.get(destino).put(origen, peso); // Asegura simetría
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, Double>> getGrafo() {
        return grafo;
    }
}
