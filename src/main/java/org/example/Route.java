package org.example;

public class Route {
    private String source;
    private String destination;
    private int cost;

    public Route(String source, String destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }
}
