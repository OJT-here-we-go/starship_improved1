package com.games.pieces;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.nio.file.Path;

/*The sound class is used to implement experience allowing the user to hear sound when
the starship collides with a foreign object*/
public class Sound {
    // Control sound volume using a JPanel slider that has been passed from the VolumeSlider class
    Clip audioClip;
    VolumeSlider slider = new VolumeSlider();
    public void playSound() throws FileNotFoundException, LineUnavailableException {
        try {
            File inputFile = new File(String.valueOf(Path.of("Sound","beep-07.wav")));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioInputStream);
            setVolume(slider, audioClip);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setVolume(VolumeSlider slider, Clip audioClip) throws IOException, LineUnavailableException {
        //audioClip.open(stream);
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(slider.getVolumeLevel()-50);
        audioClip.start();
    }

    /*public void setVolume(VolumeSlider slider, Clip audioClip) throws IOException, LineUnavailableException {
        if (slider.getVolumeLevel() < 1f || slider.getVolumeLevel() > 100f)
            throw new IllegalArgumentException("Volume not valid: " + slider.getVolumeLevel());
        FloatControl gainControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(slider.getVolumeLevel()));
    }*/
}