package com.test.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

class State {
    private ArrayList<Point> points = new ArrayList<>();
    private HashSet<Line> lines = new HashSet<>();
    Point clickedPoint = null;
    boolean active;
    private Random prng = new Random();

    State(boolean active) {
        this.active = active;
    }

    void addPoint(Point point) {
        points.add(point);
    }

    void addLine() {
        Point a, b;
        do {
            a = points.get(prng.nextInt(Integer.SIZE - 1) % points.size());
            do {
                b = points.get(prng.nextInt(Integer.SIZE - 1) % points.size());
            } while (a == b);
        }
        while(!lines.add(new Line(a, b)));
    }

    boolean haveLine(Line line) {
        return lines.contains(line);
    }

    int numLines() {
        return lines.size();
    }

    void clear() {
        points.clear();
        lines.clear();
    }

    Point clicked(int x, int y) {
        if (!active)
            return null;
        for (Point point : points)
            if (point.isClicked(x, y)) {
                return point;
            }
        return null;
    }

    boolean dragged(int x, int y, boolean add) {
        if (clickedPoint == null)
            return false;
        Point nextPoint = clicked(x, y);
        if (nextPoint != null && nextPoint != clickedPoint) {
            if (add)
                lines.add(new Line(clickedPoint, nextPoint));
            else
                lines.remove(new Line(clickedPoint, nextPoint));
            clickedPoint = nextPoint;
            return true;
        }
        return false;
    }

    void paintState(Graphics g) {
        for (Point point : points) {
            point.paintComponent(g);
        }
        for (Line line : lines) {
            line.paintComponent(g);
        }
        System.out.println("Drawn " + points.size() + " points and " + lines.size() + " lines");
    }

    @Override
    public boolean equals(Object obj) {
        if (!State.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        State rhs = (State) obj;
        for (Line line : lines) {
            if (!rhs.haveLine(line))
                return false; // TODO change color for matching lines?
        }
        if (lines.size() != rhs.numLines()) {
            return false;
        }
        return true;
    }
}
