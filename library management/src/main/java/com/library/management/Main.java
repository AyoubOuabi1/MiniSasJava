package com.library.management;

import com.library.management.controllers.LivreController;
import com.library.management.model.Livre;
import com.library.management.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Handler;

public class Main {
    public static void main(String[] args) {
        printFirstMessage();

    }
    static boolean checkInput(int input){
        int[] numbers = {1, 2, 3, 4, 5, 6,7};

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == input) {
                return true; // Input found in the array
            }
        }

        return false;
    }
    static void printFirstMessage(){
        int choose = Helpers.printMenu();
        if (checkInput(choose)){
            switch (choose) {
                case 1 :
                    Livre livre =Helpers.readLivreData();
                    LivreController controller =new LivreController(livre);
                    if (controller.addLivre()){
                        System.out.println("YOUR BOOK HAS BEEN ADDED");
                        List<Livre> livres=new ArrayList< >();
                        livres.add(livre);
                        System.out.println("");
                        Helpers.printLivre(livres);
                        System.out.println("To Back To menu press Any key");
                        Scanner scanner = new Scanner(System.in);
                        scanner.nextLine();
                        printFirstMessage();


                    }
                    break;
                case 2 :
                    Helpers.printLivre(LivreController.getLivreDisponible());
                    switch (Helpers.printOption()){
                        case 2 :
                            System.out.println("mzl matsalat");
                            break;
                        case 3 :
                            System.out.println("hta hadi mzl mtsalat");
                            break;
                        default:
                            printFirstMessage();
                            break;

                    }
                    break;
                case 3 :
                    System.out.println("mzl khdam 3liha");

                    break;
                case 4 :
                    System.out.println("mzl mabditha");

                    break;
                case 5 :
                    Helpers.printLivre(LivreController.getLivreBySearch(Helpers.printSearchAlert()));

                    break;
                case 6 :
                    System.out.println("mzl magaditha");
                    break;
                case 7 :
                    System.out.println("mzl khasha  lkhadma");
                    break;
            }
        }else {
            System.out.println("please choose number from menu");
            printFirstMessage();
        }

    }



}