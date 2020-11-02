package com.games.pieces;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;


    public class BackgroundMusic {
        public static File musicPath = new File( "./src/sound/StarshipBGM16.wav");
        public static Clip clip;
        public static AudioInputStream audioIn;

        static {
            if (musicPath.exists()) {
                try {
                    audioIn = AudioSystem.getAudioInputStream(musicPath); //Locally Rename any passed in file in musicPath to audoIN
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void stopBGM() {
            clip.stop();
        }
        public static void playBGM() throws IOException, LineUnavailableException {  //CURRENTLY STATIC (no object necessary to play music)
            // InputStream BGM;
            try {
//                File musicPath = new File(filepath);  //TO BE DYNAMICALLY SET BY CURRENT PLANET

                    clip.open(audioIn); //Open and Load passed in file
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    //float volumeNum = slider.getVolumeLevel();
                    //gainControl.setValue(slider.getVolumeInt()/10-2); //slider.getVolumeLevel()
                // -50  -80-6.026
                    clip.start(); //Play file from beginning
                    clip.loop(Clip.LOOP_CONTINUOUSLY);  //Loop until "OK" click

                    //TODO PLAY PAUSE VOLUME

//
//                JOptionPane.showMessageDialog(null, "Press OK to pause");
//                long clipTimePosition = clip.getMicrosecondPosition();
//                clip.stop(); //Stop clip and save TimePosition
//
//                JOptionPane.showMessageDialog(null, "Press OK to resume");
//                clip.setMicrosecondPosition(clipTimePosition);
//                clip.start(); //Resume clip from Time Position
//                clip.loop(Clip.LOOP_CONTINUOUSLY); //Resume Loop
//
//                JOptionPane.showMessageDialog(null, "Press OK to stop music"); //INTERRUPTS AUTOMATIC CLIP THREAD DEATH and allows user to control Sound Stop

                }

            catch(Exception ex) {
                ex.printStackTrace();
            }


        }


//    //TEMP PROTOTYPE TEST CODE BELOW
//    public static void main(String[] args) {
//        String filepath = "./Sound/StarshipBGM16.wav";  //USED TO PASS IN DYNAMICALLY SET PLANET CONDITIONAL MUSIC TRACK
//
//        //BackgroundMusic StarshipBGM = new BackgroundMusic();  NON STATIC1 (playBGM) play in an object
//        //StarshipBGM.playBGM(filepath);       NON STATIC2
//
//        BackgroundMusic.playBGM(filepath);      //STATIC (playBGM) just play from the shared space

//    String filepath = "./Sound/StarshipBGM16.wav"; //LOAD DEFAULT SOUND  Game@323
//        BackgroundMusic.playBGM(filepath); //CALL BGM WITH DEFAULT
//
//
//    }

}


