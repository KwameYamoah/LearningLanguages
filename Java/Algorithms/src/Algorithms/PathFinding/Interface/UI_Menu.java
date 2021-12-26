package Algorithms.PathFinding.Interface;


import Algorithms.PathFinding.Constants;

import javax.swing.*;

import java.awt.*;


public class UI_Menu extends JPanel {
    public JSlider valueSlider;
    public ButtonGroup bg;
    public int tempSliderValue = 0;

    UI_Menu(UI_Frame ui_frame) {

        JRadioButton r1 = new JRadioButton("A* Algorithm");
        r1.setActionCommand("A*");
        r1.setSelected(true);
        JLabel sortType  = new JLabel("Select sort type");
        JRadioButton r2 = new JRadioButton("Dijkstra Algorithm");
        r2.setActionCommand("Dijkstra");
        JRadioButton r3 = new JRadioButton("Sample Algorithm");
        r3.setActionCommand("Sample");


        r1.setBounds(75, 50, 100, 30);
        r2.setBounds(75, 100, 100, 30);
        r3.setBounds(75,150,100,30);

        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        JButton start = new JButton("Start");

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,(int)(Constants.FRAME_HEIGHT*0.3)));
        northPanel.add(sortType);
        northPanel.add(r1);
        northPanel.add(r2);
        northPanel.add(r3);
        add(northPanel,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        add(centerPanel,BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,(int)(Constants.FRAME_HEIGHT*0.6)));

        JPanel southPanel = new JPanel();
        southPanel.add(start);
        southPanel.setPreferredSize(new Dimension(Constants.FRAME_WIDTH,(int)(Constants.FRAME_HEIGHT*0.1)));
        add(southPanel,BorderLayout.SOUTH);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    }
}