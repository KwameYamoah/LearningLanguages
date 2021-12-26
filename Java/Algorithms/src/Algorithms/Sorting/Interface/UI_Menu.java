package Algorithms.Sorting.Interface;

import Algorithms.Sorting.Constants;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class UI_Menu extends JPanel {
    public JSlider valueSlider;
    public ButtonGroup bg;
    public int tempSliderValue = 0;

    UI_Menu(UI_Frame ui_frame) {

        JRadioButton r1 = new JRadioButton("Selection Sort");
        r1.setActionCommand("Selection");
        r1.setSelected(true);
        JLabel sortType  = new JLabel("Select sort type");
        JRadioButton r2 = new JRadioButton("Insertion Sort");
        r2.setActionCommand("Insertion");
        JRadioButton r3 = new JRadioButton("Bubble Sort");
        r3.setActionCommand("Bubble");


        r1.setBounds(75, 50, 100, 30);
        r2.setBounds(75, 100, 100, 30);
        r3.setBounds(75,150,100,30);

        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);



        valueSlider = new JSlider(JSlider.HORIZONTAL,
                Constants.SLIDER_MIN_NUMBER_OF_VALUES,
                Constants.SLIDER_MAX_NUMBER_OF_VALUES,
                Constants.SLIDER_INIT_NUMBER_OF_VALUES
        );

        valueSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (!valueSlider.getValueIsAdjusting()) {
                    if (tempSliderValue != valueSlider.getValue()) {
                        tempSliderValue = valueSlider.getValue();

                        int rounded = ((valueSlider.getValue() + 25) / 50) * 50;
                        valueSlider.setValue(rounded);
                        Random rand = new Random();
                        List<Integer> values = new ArrayList<>();
                        for (int i = 0; i < valueSlider.getValue(); i++) {
                            values.add(rand.nextInt(Constants.RANGE_OF_VALUES) + 1);
                        }
                        ui_frame.sortPanel.changeValues(values);
                    }
                }
            }
        });
        valueSlider.setPreferredSize(new Dimension(460,50));
        Hashtable labelTable = new Hashtable();
        labelTable.put( 10 , new JLabel("10") );
        labelTable.put( Constants.SLIDER_MAX_NUMBER_OF_VALUES/4, new JLabel("50") );
        labelTable.put( Constants.SLIDER_MAX_NUMBER_OF_VALUES/2, new JLabel("100") );
        labelTable.put( (Constants.SLIDER_MAX_NUMBER_OF_VALUES*3)/4, new JLabel("150") );
        labelTable.put( Constants.SLIDER_MAX_NUMBER_OF_VALUES , new JLabel("200") );
        valueSlider.setLabelTable(labelTable);
        valueSlider.setPaintLabels(true);
        JLabel value = new JLabel("Number of values");

        JButton start = new JButton("Start");
        start.addActionListener(new ButtonHandler(this, ui_frame));

        JPanel northPanel = new JPanel();
        northPanel.add(sortType);
        northPanel.add(r1);
        northPanel.add(r2);
        northPanel.add(r3);
        add(northPanel,BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(value);
        centerPanel.add(valueSlider);
        add(centerPanel,BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(start);
        add(southPanel,BorderLayout.SOUTH);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT/4);
    }
}

class ButtonHandler implements ActionListener{
    private UI_Menu panel;
    private UI_Frame frame;
    ButtonHandler(UI_Menu panel, UI_Frame frame){
        this.panel = panel;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = panel.bg.getSelection().getActionCommand();
        frame.sortPanel.startSort(selection);
    }
}
