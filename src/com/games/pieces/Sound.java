package com.games.pieces;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;

/*The sound class is used to implement experience allowing the user to hear sound when
the starship collides with a foreign object*/
public class Sound {
    private JPanel panel;
    private JButton button;


    // Control sound volume using a JPanel slider that has been passed from the SoundControl class
    private Clip audioClip;
    private String backgroundSound = "StarshipBGM16.wav"; // all sound files should be put in the sound folder
    private String crashSound = "beep-07.wav";
    SoundControl slider = new SoundControl();

    public Sound() {
        panel = getSoundSliderPanel();
        /*button = new JButton();
        button.setText("Mute Background Music");
        panel.add(button, BorderLayout.LINE_END);
        button.addActionListener(e -> setBackgroundSound(""));*/
    }

    public void playSound(String filepath) throws FileNotFoundException, LineUnavailableException, FileNotFoundException {
        try {
            File inputFile = new File(String.valueOf(Path.of("Sound", filepath)));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            setVolume(slider, audioClip);
            //audioClip.start();
        } catch (UnsupportedAudioFileException | IOException e) {
            //e.printStackTrace();
        }
    }

    private void setVolume(SoundControl slider, Clip audioClip) throws IOException, LineUnavailableException {
        //audioClip.open(stream);
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(slider.getVolumeLevel() - 50);
        audioClip.start();

    }

    public SoundControl getSlider() {
        return slider;
    }

    public JPanel getSoundSliderPanel() {
        return slider.getSliderPanel();
    }

    public String getBackgroundSound() {
        return backgroundSound;
    }

    public String setBackgroundSound(String backgroundSound) {
        return this.backgroundSound = backgroundSound;
    }

    public String getCrashSound() {
        return crashSound;
    }

    public void setCrashSound(String crashSound) {
        this.crashSound = crashSound;
    }
}