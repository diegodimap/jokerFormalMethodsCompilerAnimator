package gui;

/**
 * This class is the first called when the user gives a double click on
 * the Joker.jar file. It calls the splash screen, closes it and calls the
 * Joker's main window.
 * @author Diego Henrique Oliveira de Souza
 */
public class StartJoker {
    public static void main(String[] args) {
        final SplashWindow sp = new SplashWindow();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sp.setVisible(true);
            }
        });
        //Espera um tempo para que a imagem de splash seja exibida
    	Runnable runner = new Runnable() {
    		public void run() {
            try {
            	Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

         }
    	};
    	runner.run();

        sp.dispose();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JokerWindow("").setVisible(true);
            }
        });

    }
}
