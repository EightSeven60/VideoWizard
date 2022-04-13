package com.example.vproject.networking;

import android.os.AsyncTask;

import com.example.vproject.user.CurrentUser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToServerAsyncTask extends AsyncTask<String, Void, Void> {

    private Socket socket;
    private PrintWriter printWriter;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private char[] response;
    private String[] splitMessage;

    public SendToServerAsyncTask() {
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            socket = new Socket(strings[1], Integer.parseInt(strings[2]));
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.write(strings[0]);
            printWriter.flush();

            response = new char[500];
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.read(response);
            splitMessage = String.valueOf(response).split(CurrentUser.separator);
            switch (splitMessage[0]) {
                case "PROCEED":
                    boolean payment = splitMessage[4].contains("True");
                    CurrentUser.setCurrentUser(splitMessage[1],
                            splitMessage[2],
                            splitMessage[3],
                            payment);
                    break;
                case "NOTFOUND":
                    break;
                case "WRONGPASSWORD":
                    break;
                case "ALREADYEXISTS":
                    break;
                default:
                    break;
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
