package org.example.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CredentialsHandler {
    private Scanner fileReader;
    private String login;
    private String password;

    {
        try {
            fileReader = new Scanner(new File("C:\\Users\\grzes\\Desktop\\pass.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CredentialsHandler() {
        List<String> credentials = new ArrayList<>();
        while(fileReader.hasNextLine()){
            credentials.add(fileReader.nextLine());
        }
        login = credentials.get(0);
        password = credentials.get(1);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
