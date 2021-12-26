package Algorithms.PathFinding.Interface;

import Algorithms.Sorting.Constants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UI_Frame extends JFrame {
    UI_Frame() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new UI_Menu(this));
        pack();
        setLocationRelativeTo(null);
        setTitle("Sorting Algorithm Visualization");
    }

    public static void main(String[] args) {
        new UI_Frame();
    }


}