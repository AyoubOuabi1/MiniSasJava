package com.library.management;

import com.library.management.services.*;
import com.library.management.helpers.PrintMessage;
import com.library.management.model.Livre;
import com.library.management.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        checkLogin();

    }
    static  void checkLogin() {
        User user = PrintMessage.printLogin();
        User user1 =new UserService(user).checkLogin();
        if (user1 != null) {
            printFirstMessage(user1);
        }else{
            System.out.println("Something wrong ");

            checkLogin();
        }

    }
    static boolean checkInput(int input){
        int[] numbers = {1, 2, 3, 4, 5, 6,7,8};

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == input) {
                return true;
            }
        }
        return false;
    }
    static void printFirstMessage(User user){
        int choose = PrintMessage.printMenu(user);
        if (checkInput(choose)){
            switch (choose) {
                case 1 :
                    if (user.getRole().equalsIgnoreCase("admin")){
                        newBook(user);
                    }else{
                        backToMenu(user);
                    }
                    break;
                case 2 :
                    PrintMessage.printLivre(LivreService.getLivreDisponible());
                   booksOperation(user);
                    break;
                case 3 :
                    if (user.getRole().equalsIgnoreCase("admin")){
                        PrintMessage.prinBorrowedBook(LivreEmprunteService.getAllBookEmp());
                        System.out.println("");
                    }else{
                        backToMenu(user);
                    }



                    break;
                case 4 :
                    if (user.getRole().equalsIgnoreCase("admin")){
                        PrintMessage.printLivre(LivrePerduService.getLostBook());
                        System.out.println("");
                        if(PrintMessage.printReturnOption()==1){
                            int check= LivrePerduService.returnBookIntoLibrarry(PrintMessage.getBookId());
                            if(check>0){
                                System.out.println("sf lktab rja3");
                                backToMenu(user);
                            }else{
                                System.out.println("system tayh");
                            }

                        }else {
                            backToMenu(user);
                        }
                    }else{
                        backToMenu(user);
                    }


                    break;
                case 5 :
                    PrintMessage.printLivre(LivreService.getLivreBySearch(PrintMessage.printSearchAlert()));
                    booksOperation(user);
                    break;
                case 6 :
                    PrintMessage.printLivre(LivreEmprunteService.getLivresEmpByMe(user));
                    if(PrintMessage.printReturnOption()==1){
                        int check= LivreEmprunteService.returnLivre(PrintMessage.getBookId(),user);
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
                    if (user.getRole().equalsIgnoreCase("admin")){
                        statiscticalOptions(user);

                    }else{
                        backToMenu(user);
                    }
                    break;
                case 8 :
                    if (user.getRole().equalsIgnoreCase("admin")){
                        UserService userService=new UserService(PrintMessage.readNewUserData());
                        if(userService.addUser()){
                            System.out.println("NEW USER HAS BEEN ADDED");
                            backToMenu(user);
                        }
                    }else{
                        backToMenu(user);
                    }

                    break;
            }
        }else {
            System.out.println("please choose number from menu");
            printFirstMessage(user);
        }

    }

    private static void newBook(User user) {
        Livre livre = PrintMessage.readLivreData();
        LivreService controller =new LivreService(livre);
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

        switch (PrintMessage.printOption(user)){
            case 2 :
                if (user.getRole().equalsIgnoreCase("admin")){
                    int id1 = PrintMessage.getBookId();
                    Livre livre2=new Livre();
                    livre2.setId(id1);

                    System.out.println("are you sure you want to delete this book y/n");
                    Scanner scanner=new Scanner(System.in);
                    String str=scanner.nextLine();
                    LivreService controller2 =new LivreService(livre2);
                    if(str.equalsIgnoreCase("y")){
                        System.out.println(controller2.deleteLivre());
                        backToMenu(user);
                    }else {
                        backToMenu(user);
                    }
                }else{
                    backToMenu(user);
                }

                break;
            case 3 :
                if (user.getRole().equalsIgnoreCase("admin")){
                    int id = PrintMessage.getBookId();
                    Livre livre1 = PrintMessage.readUpdatedLivreData();
                    livre1.setId(id);
                    LivreService controller1 =new LivreService(livre1);
                    System.out.println(controller1.updateLivre());
                    List<Livre> livres=new ArrayList< >();
                    livres.add(livre1);
                    PrintMessage.printLivre(livres);
                    System.out.println("");
                    backToMenu(user);
                }else{
                    backToMenu(user);
                }

                break;
            case 4 :
                int id3 = PrintMessage.getBookId();
                Livre livre3=new Livre();
                livre3.setId(id3);
                System.out.println("are you sure you want to borrow this book y/n");
                Scanner scanner1=new Scanner(System.in);
                String str1=scanner1.nextLine();
                if(str1.equalsIgnoreCase("y")){
                    if (LivreEmprunteService.emprunteLivre(user,livre3)){
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
                if (user.getRole().equalsIgnoreCase("admin")){
                    int id4 = PrintMessage.getBookId();
                    Livre livre4=new Livre();
                    livre4.setId(id4);
                    System.out.println("are you sure you want to mark this book as lost y/n");
                    Scanner scanner2=new Scanner(System.in);
                    String str2=scanner2.nextLine();
                    if(str2.equalsIgnoreCase("y")){
                        if (LivrePerduService.markBookAsLost(livre4)){
                            System.out.println("this book are marked as lost now");
                            backToMenu(user);

                        }else{
                            System.out.println("Something  wrong");
                            backToMenu(user);
                        }
                    }else {
                        backToMenu(user);
                    }
                }else{
                    backToMenu(user);
                }

                break;

            default:
                printFirstMessage(user);
                break;

        }
    }
    static void statiscticalOptions(User user){
        switch (PrintMessage.printStatistiqueOption()) {
            case 1:
                System.out.println("Total books available " + StatisqueService.countLivresDispo());
                System.out.println("Total Books Borrowed " + StatisqueService.countLivresEmp());
                System.out.println("Total Books lost " + StatisqueService.countLivresPerdu());
                System.out.println("");

                backToMenu(user);

                break;
            case 2 :
                 System.out.println("Total books lost today is "+LivrePerduService.getLostBookToday().size());
                System.out.println("");

                backToMenu(user);

                break;
            case 3 :
                Scanner scanner2=new Scanner(System.in);
                 System.out.println("please enter start date");
                String startDate=scanner2.nextLine();
                System.out.println("please enter end date");
                String endDate=scanner2.nextLine();
                System.out.println("Total Books lost between "+startDate +" and  " +endDate +" is " +LivrePerduService.getLostBookByTwoDate(startDate, endDate).size());
                System.out.println("");

                backToMenu(user);
                break;
            case 4 :
                System.out.println("Total books borrowed today is "+LivreEmprunteService.getBorrowedBookToday().size());
                System.out.println("");

                backToMenu(user);
                break;
            case 5 :
                Scanner scanner=new Scanner(System.in);
                System.out.println("please enter start date");
                String startDate1=scanner.nextLine();
                System.out.println("please enter end date");
                String endDate1=scanner.nextLine();
                System.out.println("Total Books borrowed between "+startDate1 +" and  " +endDate1 +" is " +LivreEmprunteService.getBorrowedBookByTwoDate(startDate1, endDate1).size());
                System.out.println("");

                backToMenu(user);
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