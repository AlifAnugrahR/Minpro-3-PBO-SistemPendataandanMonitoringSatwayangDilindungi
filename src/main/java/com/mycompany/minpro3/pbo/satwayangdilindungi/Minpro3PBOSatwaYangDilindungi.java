package com.mycompany.minpro3.pbo.satwayangdilindungi;

import Controller.SatwaCRUD;
import View.Menu;
import java.util.Scanner;

public class Minpro3PBOSatwaYangDilindungi {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        SatwaCRUD crud = new SatwaCRUD();

        Menu.jalankan(input, crud);

        input.close();
    }
} 