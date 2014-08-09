package com.project.maven.first;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 * java -cp target/first_project-1.0-SNAPSHOT.jar com.project.maven.first.App
 * java -cp target/first_project-1.0-SNAPSHOT-jar-with-dependencies.jar com.project.maven.first.App
 * mvn exec:java -D exec.mainClass=com.project.maven.first.App
 */
public class App 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mw = new MainWindow();
            	mw.setVisible(true);
            }
        });
    }
}
