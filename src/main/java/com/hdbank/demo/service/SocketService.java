package com.hdbank.demo.service;

import com.hdbank.demo.utils.JsonService;
import com.hdbank.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


@Service
public class SocketService {

    @Autowired
    private JsonService jsonService;

    public String sendSocket(String body) {
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String responseLine = "";
        String messageIn = body + "<EOF>";
        try {
            clientSocket = new Socket("10.0.113.204", 54545);// local
            clientSocket.setSoTimeout(10000);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(messageIn);
            System.out.println("Request: " + messageIn);
            responseLine = in.readLine();
            System.out.println("Response: " + responseLine);
        } catch (Exception e) {
            System.err.println("Send error " + e.getMessage());
        } finally {
            Utils.close(in);
            Utils.close(out);
            Utils.close(clientSocket);
        }
        return responseLine;
    }
}