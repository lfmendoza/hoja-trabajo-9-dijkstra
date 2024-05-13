package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer>> edges;

    public Graph(String filename) {
        this.edges = new HashMap<>();
        loadRoutes(filename);
    }

    private void loadRoutes(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String source = parts[0].trim();
                String destination = parts[1].trim();
                int cost = Integer.parseInt(parts[2].trim());
                addEdge(source, destination, cost);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addEdge(String source, String destination, int cost) {
        edges.computeIfAbsent(source, k -> new HashMap<>()).put(destination, cost);
        edges.computeIfAbsent(destination, k -> new HashMap<>()).put(source, cost);
    }

    public Map<String, Integer> findCheapestCost(String source) {
        Map<String, Integer> destinationsAndCosts = new HashMap<>();

        Set<String> visited = new HashSet<>();
        Map<String, Integer> costs = new HashMap<>();

        for (String city : edges.getOrDefault(source, new HashMap<>()).keySet()) {
            costs.put(city, Integer.MAX_VALUE);
        }
        costs.put(source, 0);

        while (true) {
            String currentCity = null;
            int minCost = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> entry : costs.entrySet()) {
                if (!visited.contains(entry.getKey()) && entry.getValue() < minCost) {
                    currentCity = entry.getKey();
                    minCost = entry.getValue();
                }
            }

            if (currentCity == null) {
                break;
            }

            visited.add(currentCity);

            for (Map.Entry<String, Integer> entry : edges.getOrDefault(currentCity, new HashMap<>()).entrySet()) {
                int newCost = costs.get(currentCity) + entry.getValue();
                if (newCost < costs.getOrDefault(entry.getKey(), Integer.MAX_VALUE)) {
                    costs.put(entry.getKey(), newCost);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            if (!entry.getKey().equals(source)) {
                destinationsAndCosts.put(entry.getKey(), entry.getValue());
            }
        }

        return destinationsAndCosts;
    }
}
