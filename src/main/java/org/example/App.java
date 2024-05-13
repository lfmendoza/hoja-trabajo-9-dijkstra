package org.example;

import java.util.Scanner;
import java.util.Map;

/**
 * Esta es la clase principal que ejecuta la aplicación.
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();

        System.out.print("Ingrese la estación de salida: ");
        String estacion = scanner.nextLine();

        Dijkstra dijkstra = new Dijkstra(grafo.getGrafo());
        Map<String, Double> resultados = dijkstra.calcularRutasDesde(estacion);

        resultados.forEach((destino, costo) -> System.out.println("Costo a " + destino + ": " + costo));

        scanner.close();
    }
}