package com.test.components;

import java.awt.*;

class Point extends Rectangle {
    int x, y;

    Point(int x, int y) {
        this.width = 10;
        this.height = 10;
        this.x = x - this.width / 2;
        this.y = y - this.height / 2;
    }

    void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, width, height);
    }

    boolean isClicked(int x, int y) {
        if (x > this.x && x < this.x + height)
            if (y > this.y && y < this.y + width)
                return true;
        return false;
    }
}
