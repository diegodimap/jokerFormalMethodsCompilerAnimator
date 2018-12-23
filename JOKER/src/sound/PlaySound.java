package sound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;

/**
 * This class plays sounds with a .wav extension
 * @author Diego Henrique Oliveira de Souza
 */
public class PlaySound extends Thread {

    private File musica;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

    enum Position {
        LEFT, RIGHT, NORMAL
    };

    /**
     * This default constructor receives a File object, pointing
     * to a .wav File, and plays the sound
     * @param f
     */
    public PlaySound(File f) {
        musica = f;
        curPosition = Position.NORMAL;
    }

    /**
     * This method plays the sound.
     */
    public void run() {
        File soundFile = musica;
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + musica);
            return;
        }

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            } else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }

        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }

    }

    /**
     * This method manage .wav sound files
     */
    public void lerArquivoWav(){
        JFileChooser eleitor = new JFileChooser();
        eleitor.setDialogTitle("Open WAV File");
        eleitor.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                return (f.getName().endsWith(".wav") || f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "WAV (.wav)";
            }
        });
        eleitor.setCurrentDirectory(new File("."));
        int result = eleitor.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            musica = eleitor.getSelectedFile();
        }
    }

}