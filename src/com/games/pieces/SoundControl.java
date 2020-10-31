package com.games.pieces;

import com.games.game.Game;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoundControl {

    // make fields private
    private JSlider volSlider;
    private JLabel label;
    private int volumeLevel = 30;
    private JPanel panel;
    //private JButton button;

    /*  // main for testing purposes
        public static void main(String[] args) {
        SoundControl sliderGen = new SoundControl();
        sliderGen.sliderGen();
    }*/

    public SoundControl(){
        sliderGen();
    };

    //JFrame with horizontal slider panel
    public void sliderGen() {
        //Create JPanel components
        volSlider = new JSlider();
        volSlider.setMinimum(1); //
        volSlider.setMaximum(74);
        volSlider.setValue(30); // default volume
        volSlider.addChangeListener(new VolumeChangeAction());
        label = new JLabel("Volume");

        panel = new JPanel(); // Dynamically create and add components to JPanel
        panel.add(label);
        panel.add(volSlider);
        //panel.add(button);


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

    public void mute(){

    }
    public float getVolumeLevel() {
        return (float) Math.pow(10f, volumeLevel / 50f); //
    }

    public float getBGMVolumeLevel() {
        return (float) Math.pow(10f, volumeLevel / 20f); //
    }

    public float getVolumeInt() {
        return volumeLevel;
    }

    public JPanel getSliderPanel() {
        return panel;
    }
}