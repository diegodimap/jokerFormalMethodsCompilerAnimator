package tools;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;


/**
 * This class is responsible for writing logs for Joker.
 * Currently it is not used because Joker is showing
 * the log on the screen located in the LOG tab.
 * @author Diego Henrique Oliveira de Souza
 */
public final class JokerLog extends Thread {

    /** */
    private final Logger LOG = Logger.getLogger(this.getName());
    /** */
    private InputStream in_ = null;
    /** */
    private OutputStream out_ = null;
    /** */
    private boolean autoFlush_;

    private String message = "";

    /**
     * @param in -
     * @param out -
     */
    public JokerLog(InputStream in, OutputStream out) {
        this(in, out, false, true);
    }

    /**
     * @param in -
     * @param out -
     * @param asDaemon -
     * @param autoFlush -
     */
    public JokerLog(InputStream in, OutputStream out, boolean asDaemon, boolean autoFlush) {
        setDaemon(asDaemon);
        this.in_ = in;
        this.out_ = out;
        this.autoFlush_ = autoFlush;
    }

    public JokerLog(OutputStream outputStream, PrintStream out) {
        this.out_ = outputStream;
    }

    /**
     * @see java.lang.Thread#run()
     */
    public final void run() {
        try {
            int b;
            do {
                b = this.in_.read();
                if (b != -1) {
                    //writeOutput(b);
                    message += (char) b;
                }
            } while (b != -1);
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        } finally {
            closeOutput();
        }
    }

    /**
     * @param b -
     * @throws IOException -
     */
    private void writeOutput(int b) throws IOException {
        if (this.out_ != null) {
            this.out_.write(b);
            if (this.autoFlush_) {
                out_.flush();
            }
        }
    }

    /**
     */
    private void closeOutput() {
        try {
            if (this.out_ != null) {
                this.out_.flush();
                this.out_.close();
            }
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        }
    }

    public String getMessage(){
        return this.message;
    }
}
