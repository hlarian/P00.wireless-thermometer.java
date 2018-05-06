/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhtwireless;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TempSceneController {

    Socket skt;
    String IP;
    int Port;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @FXML
    Label status;
    @FXML
    TextField temp;

    @FXML
    TextField ipT;
    @FXML
    TextField PortT;
    @FXML
    Button connect;

    public void initialize() {

    }

    @FXML
    private void connect() {

        
        //temp.setText("start buffering...");
            try {
                skt = new Socket(IP, Port);
                DataInputStream fromServer = new DataInputStream(skt.getInputStream());      
                String s1 = fromServer.readLine();
                System.out.println("start buffering...");
                log("start buffering...");
                System.out.print("received : " + s1);
                log("received : " + s1);
                temp.setText(s1);
               // TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                System.out.println("connect :" + e.toString());
                log("connectMethod :" + e.toString());
            }        
    }

    @FXML
    private void connectHandller(ActionEvent event) {

        try {
            IP = ipT.getText();
            Port = Integer.parseInt(PortT.getText());
            status.setText("connecting...");
            log("Connecting to... :" + IP + ":" + Port);
            if (Connection(IP, Port)) {
                status.setText("Connect to: " + IP);
                log("Connect to: " + IP + ":" + Port);
            } else {
                status.setText("don't Connect to " + IP);
                log("don't Connect to" + IP + ":" + Port);
            }
        } catch (Exception ex) {
            System.out.println("ConnectionHandller : " + ex.toString());
            status.setText("don't Connect to" + IP + ":" + Port);
            log("don't Connect to" + IP + ":" + Port);
            log("ConnectionHandller Exception: " + ex.toString());
        }

    }

    private boolean Connection(String IP, int Port) {

        try {
            Socket skt = new Socket(IP, Port);
            return true;
        } catch (IOException ex) {
            System.out.println("Connection : " + ex.toString());
            log("Connection Exception: " + ex.toString());
            return false;
        }
    }

    private void log(String text) {
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            try {
                Myloger.setup();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Problems with creating the log files");
            }

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(text);
        } catch (Exception ex) {
            System.out.println("logMethod: " + ex.toString());
        }

    }

}
