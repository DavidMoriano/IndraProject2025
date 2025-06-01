package com.proyect;

import java.io.IOException;
import java.sql.SQLException;

import com.views.MainView;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        MainView mw = new MainView();
        mw.start();
    }
} 
