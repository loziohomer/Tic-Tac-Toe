package com.example.tris.model;

public class Player {
    private String name;
    private int color;
    private double points;

    public Player(String name, int color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", points=" + points +
                '}';
    }

    public void winner() {
        points += 1;
    }

    public void drawer() {
        points += 0.5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
