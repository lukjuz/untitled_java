package com.test.components;

import java.awt.*;

class Line extends Rectangle {

    private Point a, b;

    Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        g2.drawLine(a.x + a.height / 2, a.y + a.width / 2, b.x + b.height / 2, b.y + b.width / 2);
    }

    @Override
    public int hashCode() {
        return (a.x + a.y + a.width + a.height) * (b.x + b.y + b.width + b.height); // FIXME doesn't look like correct way to detect the same lines
    }
}
