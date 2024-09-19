package org.BatiCuisine;


import org.BatiCuisine.consoleUi.MainUI;

import java.sql.SQLException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainUI mainUI = new MainUI();
        mainUI.run();
    }
}