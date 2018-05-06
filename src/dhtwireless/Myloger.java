/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhtwireless;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Myloger {
    
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static public void setup() throws IOException {

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

       

        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("Logging.txt");
        
        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        
    }
    
}
