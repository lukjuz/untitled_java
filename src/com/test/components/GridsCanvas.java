package com.test.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GridsCanvas extends JPanel {

    private State initState = new State(true);
    private State userState = new State(false);

    GridsCanvas() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                userState.clickedPoint = userState.clicked(e.getX(), e.getY());
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                userState.clickedPoint = null;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(userState.dragged(e.getX(), e.getY(), SwingUtilities.isLeftMouseButton(e)))
                    repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (initState.active)
            initState.paintState(g);
        else
            userState.paintState(g);
    }

    void generate(int cols, int rows, int lines) {
        int vertex = cols * rows;
        if (vertex < 2)
            throw new IndexOutOfBoundsException("Number of columns multiplied by rows should be greater than 2");
        if (vertex * (vertex - 1) / 2 < lines) // lower than number of edges in a complete graph
            throw new IndexOutOfBoundsException(String.format("Number of columns multiplied by rows is not enough to draw %s lines", lines));
        initState.clear();
        userState.clear();

        // Generate points
        int heightSpace = getSize().height / ++rows;
        int widthSpace = getSize().width / ++cols;
        for (int i = 1; i != cols; i++) {
            for (int j = 1; j != rows; j++) {
                initState.addPoint(new Point(i * widthSpace, j * heightSpace));
                userState.addPoint(new Point(i * widthSpace, j * heightSpace));
            }
        }

        // Generate random lines
        for(int i=0; i<lines; i++) {
            initState.addLine();
        }
        repaint();
    }

    boolean checkSolution() {
        return initState.equals(userState);
    }

    void toggleState() {
        if (initState.active) {
            initState.active = false;
            userState.active = true;
        }
        else {
            initState.active = true;
            userState.active = false;
        }
        repaint();
    }
}