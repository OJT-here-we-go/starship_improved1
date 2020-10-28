package com.games.pieces;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;  //
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;


    public class BackgroundMusic {

        public static void playBGM(String musicSource) {  //CURRENTLY STATIC (no object necessary to play music)
            // InputStream BGM;
            try {
                File musicPath = new File(musicSource);  //TO BE DYNAMICALLY SET BY CURRENT PLANET
                if(musicPath.exists()){
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicPath); //Locally Rename any passed in file in musicPath to audoIN
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn); //Open and Load passed in file
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

                } else {
                    System.out.printf("\nERROR!! Music file \"%s\" not found\n", musicPath);
                }
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


