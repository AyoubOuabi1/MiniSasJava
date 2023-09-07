package com.library.management;

import com.library.management.services.LivreController;
import com.library.management.helpers.PrintMessage;
import com.library.management.model.Livre;
import com.library.management.model.User;
import com.library.management.services.LivreEmprunte;
import com.library.management.services.LivrePerdu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        checkLogin();

    }
    static  void checkLogin() throws InterruptedException {
        User user = PrintMessage.printLogin();
        User user1 =new User(user.getEmail(), user.getPassword()).checkLogin();
        if (user1 != null) {
            printFirstMessage(user1);
        }else{
            System.out.println("Something wrong ");

            checkLogin();
        }

    }
    static boolean checkInput(int input){
        int[] numbers = {1, 2, 3, 4, 5, 6,7};

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == input) {
                return true;
            }
        }
        return false;
    }
    static void printFirstMessage(User user){
        int choose = PrintMessage.printMenu();
        if (checkInput(choose)){
            switch (choose) {
                case 1 :
                    newBook(user);
                    break;
                case 2 :
                    PrintMessage.printLivre(LivreController.getLivreDisponible());
                   booksOperation(user);
                    break;
                case 3 :
                    PrintMessage.printLivre(LivreEmprunte.getAllBookEmp());
                    System.out.println("");


                    break;
                case 4 :
                    PrintMessage.printLivre(LivrePerdu.getLostBook());
                    System.out.println("");
                    if(PrintMessage.printReturnOption()==1){
                        int check=LivrePerdu.returnBookIntoLibrarry(PrintMessage.getBookId());
                        if(check>0){
                            System.out.println("sf lktab rja3");
                            backToMenu(user);
                        }else{
                            System.out.println("system tayh");
                        }

                    }else {
                        backToMenu(user);
                    }

                    break;
                case 5 :
                    PrintMessage.printLivre(LivreController.getLivreBySearch(PrintMessage.printSearchAlert()));
                    booksOperation(user);
                    break;
                case 6 :
                    PrintMessage.printLivre(LivreEmprunte.getLivresEmpByMe(user));
                    if(PrintMessage.printReturnOption()==1){
                        int check=LivreEmprunte.returnLivre(PrintMessage.getBookId(),user);
                        if(check>0){
                            System.out.println("3la slamtk lktab mab9ash mahsob 3lik");
                            backToMenu(user);
                        }else{
                            System.out.println("system tayh");
                        }

                    }else {
                        backToMenu(user);
                    }

                    break;
                case 7 :
                    System.out.println("mzl khasha  lkhadma");
                    break;
            }
        }else {
            System.out.println("please choose number from menu");
            printFirstMessage(user);
        }

    }

    private static void newBook(User user) {
        Livre livre = PrintMessage.readLivreData();
        LivreController controller =new LivreController(livre);
        if (controller.addLivre()){
            System.out.println("YOUR BOOK HAS BEEN ADDED");
            List<Livre> livres=new ArrayList< >();
            livres.add(livre);
            System.out.println("");
            PrintMessage.printLivre(livres);
            backToMenu(user);


        }
    }

    static void booksOperation(User user) {

        switch (PrintMessage.printOption()){
            case 2 :
                int id1 = PrintMessage.getBookId();
                Livre livre2=new Livre();
                livre2.setId(id1);

                System.out.println("are you sure you want to delete this book y/n");
                Scanner scanner=new Scanner(System.in);
                String str=scanner.nextLine();
                LivreController controller2 =new LivreController(livre2);
                if(str.equalsIgnoreCase("y")){
                    System.out.println(controller2.deleteLivre());
                    backToMenu(user);
                }else {
                    backToMenu(user);
                }
                break;
            case 3 :
                int id = PrintMessage.getBookId();
                Livre livre1 = PrintMessage.readUpdatedLivreData();
                livre1.setId(id);
                LivreController controller1 =new LivreController(livre1);
                System.out.println(controller1.updateLivre());
                List<Livre> livres=new ArrayList< >();
                livres.add(livre1);
                PrintMessage.printLivre(livres);
                System.out.println("");
                backToMenu(user);
                break;
            case 4 :
                int id3 = PrintMessage.getBookId();
                Livre livre3=new Livre();
                livre3.setId(id3);
                System.out.println("are you sure you want to borrow this book y/n");
                Scanner scanner1=new Scanner(System.in);
                String str1=scanner1.nextLine();
                if(str1.equalsIgnoreCase("y")){
                    if (LivreEmprunte.emprunteLivre(user,livre3)){
                        System.out.println("this book are borrow by you");
                        backToMenu(user);

                    }else{
                        System.out.println("Something  wrong");
                        backToMenu(user);
                    }
                }else {
                    backToMenu(user);
                }
                break;
            case 5 :
                int id4 = PrintMessage.getBookId();
                Livre livre4=new Livre();
                livre4.setId(id4);
                System.out.println("are you sure you want to mark this book as lost y/n");
                Scanner scanner2=new Scanner(System.in);
                String str2=scanner2.nextLine();
                if(str2.equalsIgnoreCase("y")){
                    if (LivrePerdu.markBookAsLost(livre4)){
                        System.out.println("this book are marked as lost now");
                        backToMenu(user);

                    }else{
                        System.out.println("Something  wrong");
                        backToMenu(user);
                    }
                }else {
                    backToMenu(user);
                }
                break;

            default:
                printFirstMessage(user);
                break;

        }
    }

    static void backToMenu(User user) {
        System.out.println("To Back To menu press Entry");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        printFirstMessage(user);
    }


}