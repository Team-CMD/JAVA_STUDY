package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int A = sc.nextInt();
        final int B = sc.nextInt();

        if(A>B)
            System.out.println(">");
        else if(A==B)
            System.out.println("=");
        else
            System.out.println("==");
    }

}
