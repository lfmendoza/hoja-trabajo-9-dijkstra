package org.example;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Graph graph = new Graph("src/rutas.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Por favor, ingresa la estación de salida: ");
        String source = scanner.nextLine();

        Map<String, Integer> destinationsAndCosts = graph.findCheapestCost(source);

        System.out.println("Los destinos posibles desde " + source + " y el costo más barato a cada destino son:");
        for (Map.Entry<String, Integer> entry : destinationsAndCosts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }
}