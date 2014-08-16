package com.project.maven.first;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 * java -cp target/first_project-1.0-SNAPSHOT.jar com.project.maven.first.Main
 * java -cp target/first_project-1.0-SNAPSHOT-jar-with-dependencies.jar com.project.maven.first.Main
 * mvn exec:java -D exec.mainClass=com.project.maven.first.App
 */
public class Main 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mw = new MainWindow();            	
            }
        });
    }
}
