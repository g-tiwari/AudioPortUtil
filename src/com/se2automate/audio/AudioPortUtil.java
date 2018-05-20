package com.se2automate.audio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;

public class AudioPortUtil {
    private static final Logger LOG = LoggerFactory.getLogger(AudioPortUtil.class);

    /**
     * Prints the information of all the audio formats of all the lines that a mixer supports
     * @param mixer - the mixer to print out the info
     */
    private static void printMixerInfo(final Mixer mixer) {
        Line.Info[] sourceLineInfo = mixer.getSourceLineInfo();
        Line.Info[] targetLineInfo = mixer.getTargetLineInfo();

        System.out.println(String.format("\tSupported output line audio formats:%n"));
        for (int j = 0; j < sourceLineInfo.length; j++) {
            printLineInfo(sourceLineInfo[j]);
        }

        System.out.println(String.format("\tSupported input line audio formats:%n"));
        for (int j = 0; j < targetLineInfo.length; j++) {
            printLineInfo(targetLineInfo[j]);
        }
    }

    /**
     * Prints the information of the audio format that a specific line supports
     * @param line - the line to print ouf the info
     */
    private static void printLineInfo(final Line.Info line) {
        System.out.println(String.format("\t\t* %s%n", line));
        try {
            AudioFormat[] audioFormats = ((DataLine.Info) line).getFormats();
            for (int k = 0; k < audioFormats.length; k++) {
                System.out.println(String.format("\t\t\t- %s%n", audioFormats[k]));
            }
        } catch (ClassCastException e) {
            System.out.println(String.format("\t\t\tNo supported audio formats%n"));
        }
    }

    /**
     * Utility static method that lists out the audio mixers information
     */
    public static void printAudioInfo() {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println(String.format("=======Mixer Information=======%n"));

        for (int i = 0; i < mixerInfos.length; i++) {
            System.out.println(String.format("Mixer Number: %d : %s%n", i, mixerInfos[i].getName()));
            Mixer mixer = AudioSystem.getMixer(mixerInfos[i]);

            printMixerInfo(mixer);

            System.out.println(String.format("=====================%n"));
        }
    }
    /**
     * @param args - jvm args
     */
    public static void main(final String[] args) {
        printAudioInfo();
    }
}
