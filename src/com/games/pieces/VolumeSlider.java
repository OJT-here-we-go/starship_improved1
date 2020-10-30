package com.games.pieces;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VolumeSlider {

    // make fields private
    private JSlider volSlider;
    private JLabel label;
    private int volumeLevel = 30;
    private JPanel panel;

    /*  // main for testing purposes
        public static void main(String[] args) {
        VolumeSlider sliderGen = new VolumeSlider();
        sliderGen.sliderGen();
    }*/

    public VolumeSlider(){
        sliderGen();
    };

    //JFrame with horizontal slider panel
    public void sliderGen() {
        //JFrame sliderDisplay = new JFrame();
        volSlider = new JSlider();
        volSlider.setMinimum(1); //
        volSlider.setMaximum(74);
        volSlider.setValue(30); // default volume
        volSlider.addChangeListener(new VolumeChangeAction());
        label = new JLabel("Volume");

        panel = new JPanel();
        panel.add(label);
        panel.add(volSlider);

//        sliderDisplay.add(panel, BorderLayout.CENTER);
//        sliderDisplay.setSize(300, 60);
//        sliderDisplay.setVisible(true);
//        sliderDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public class VolumeChangeAction implements ChangeListener {
        public void stateChanged(ChangeEvent event) {
            volumeLevel = volSlider.getValue();
            String stringValue = Integer.toString(volumeLevel);
            label.setText(stringValue);
        }
    }

    public float getVolumeLevel() {
        return (float) Math.pow(10f, volumeLevel / 50f); //
    }

    public float getVolumeInt() {
        return volumeLevel;
    }

    public JPanel getSliderPanel() {
        return panel;
    }
}