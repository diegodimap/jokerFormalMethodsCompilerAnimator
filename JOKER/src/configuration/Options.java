package configuration;

import io.ReadFile;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains the Joker's configuration. All the fields
 * represents a default configuration or personalized one, done by the user.
 * @author Diego Henrique Oliveira de Souza
 */
public class Options {

    //configurações
    private static String cspmFolder = "";
    private static String probcliFolder = "";
    private static String graphType = "";
    private static String graphImg = "";
    private static String graphWidth = "";
    private static String graphHeight = "";

    public static String getCspmFolder() {
        cspmFolder = getData(0);
        return cspmFolder;
    }

    public static String getProbcliFolder() {
        probcliFolder = getData(1);
        return probcliFolder;
    }

    public static String getGraphType() {
        graphType = getData(2);
        return graphType;
    }

    public static String getGraphImg() {
        graphImg = getData(3);
        return graphImg;
    }

    public static String getGraphWidth() {
        graphWidth = getData(4);
        return graphWidth;
    }

    public static String getGraphHeight() {
        graphHeight = getData(5);
        return graphHeight;
    }

    private static String getData(int i) {
        String data = "";
        try {
            String config = ReadFile.leia("./configuration.joker");
            //JokerWindow.logIt("CONFIGURATION OPT: " + config);
            String[] partes = config.split(";");
            data = partes[i].substring(partes[i].indexOf("=") + 1);
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
}
