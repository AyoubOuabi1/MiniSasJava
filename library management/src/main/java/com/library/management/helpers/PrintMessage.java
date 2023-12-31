package com.library.management.helpers;

import com.library.management.model.Livre;
import com.library.management.model.User;

import java.util.List;
import java.util.Scanner;

public class PrintMessage {
    public static int printMenu(User user){

        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                                                                                                                                                               |");
        System.out.println("|                                                                                  Welcome Back                                                                                                                 |");
        System.out.println("|                                                                               Please choose option                                                                                                            |");
        System.out.println("|                                                                                                                                                                                                               |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                                                                                                                                                               |");
        if(user.getRole().equalsIgnoreCase("admin")){
            System.out.println("|                                                                                 1 Add new Book                                                                                                        |");

        }
        System.out.println("|                                                                                 2 Show Available Books                                                                                                        |");
        if(user.getRole().equalsIgnoreCase("admin")){
            System.out.println("|                                                                                 3 Show borrow Books                                                                                                           |");
            System.out.println("|                                                                                 4 Show lost books                                                                                                             |");

        }
        System.out.println("|                                                                                 5 Search For books                                                                                                            |");
        System.out.println("|                                                                                 6 show My Books                                                                                                               |");
        if(user.getRole().equalsIgnoreCase("admin")){
            System.out.println("|                                                                                 7 show statistical                                                                                                            |");
            System.out.println("|                                                                                 8 Add new User                                                                                                            |");
        }
        System.out.println("|                                                                                                                                                                                                               |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        Scanner myObj=new Scanner(System.in);
        return myObj.nextInt();
    }

    public static  void printLivre(List<Livre> livreList){
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                                                                                                                                                               |");
        System.out.println("|----------Id------------------Isbn-------------------Titre---------------------------Auteur----------------------Langage------------annee-----------------Categorie---------------------------status-----------|");
        System.out.println("|                   |                     |                             |                              |                        |                 |                          |                                  |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        for (int i =0;i<livreList.size();i++){
            System.out.println("|      "+livreList.get(i).getId()+"              "+livreList.get(i).getIsbn()+"               "+livreList.get(i).getTitre()+"                    "+livreList.get(i).getAuteur()+"                    "+livreList.get(i).getLangage()+"                    "+livreList.get(i).getAnnee()+"                    "+livreList.get(i).getCategory()+"                    "+livreList.get(i).getStatus());
        }

    }
    public static  void prinBorrowedBook(List<Livre> livreList){
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                                                                                                                                                               |");
        System.out.println("|----------Id------------------Isbn-------------------title---------------------------Author----------------------language------------year-----------------Category---------------------------borrowed by-------|");
        System.out.println("|                   |                     |                             |                              |                        |                 |                          |                                  |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        for (int i =0;i<livreList.size();i++){
            System.out.println("|      "+livreList.get(i).getId()+"              "+livreList.get(i).getIsbn()+"               "+livreList.get(i).getTitre()+"                    "+livreList.get(i).getAuteur()+"                    "+livreList.get(i).getLangage()+"                    "+livreList.get(i).getAnnee()+"                    "+livreList.get(i).getCategory()+"                    "+livreList.get(i).getStatus());
        }

    }
    public static void printOperations(){
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please choose operation--------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("    ");
        System.out.println("|---------------------------1 update -----------------------2 delete ---------------------------3 perdu -------------------------4 emprunte ------------------------5 close-----------------------|");
        System.out.println("    ");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

    }

    public static String printSearchAlert(){
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please search by title or author-----------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        Scanner myObj = new Scanner(System.in);
       return myObj.nextLine();
    }

    public static Livre readLivreData(){
        Scanner myObj = new Scanner(System.in);
        Livre livre=new Livre();
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please Fill this form ---------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------ISBN ----------------------------------------------------------------------------------------------------|");
        livre.setIsbn(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Title ---------------------------------------------------------------------------------------------------|");
        livre.setTitre(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Author --------------------------------------------------------------------------------------------------|");
        livre.setAuteur(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Language ------------------------------------------------------------------------------------------------|");
        livre.setLangage(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Category ------------------------------------------------------------------------------------------------|");
        livre.setCategory(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------year ----------------------------------------------------------------------------------------------------|");
        livre.setAnnee(myObj.nextInt());
        System.out.println("    ");
        System.out.println("    ");

        System.out.println("|----------------------------------------------------------------------------------------Quanity -------------------------------------------------------------------------------------------------|");
        livre.setQuantity(myObj.nextInt());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        livre.setStatus("disponible");
        return  livre;
    }
    public static Livre readUpdatedLivreData(){
        Scanner myObj = new Scanner(System.in);
        Livre livre=new Livre();
        livre.setId(1);
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please Fill this form ---------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new ISBN ----------------------------------------------------------------------------------------------------|");
        livre.setIsbn(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new Title ---------------------------------------------------------------------------------------------------|");
        livre.setTitre(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new Author --------------------------------------------------------------------------------------------------|");
        livre.setAuteur(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new Language ------------------------------------------------------------------------------------------------|");
        livre.setLangage(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new Category ------------------------------------------------------------------------------------------------|");
        livre.setCategory(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new year ----------------------------------------------------------------------------------------------------|");
        livre.setAnnee(myObj.nextInt());
        System.out.println("    ");
        System.out.println("    ");

        System.out.println("|----------------------------------------------------------------------------------------new Quanity -------------------------------------------------------------------------------------------------|");
        livre.setQuantity(myObj.nextInt());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        livre.setStatus("disponible");
        return  livre;
    }
    public static int getBookId(){
        System.out.println("");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please Write Book id ------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        Scanner scanner =new Scanner(System.in);
        return  scanner.nextInt();
    }

    public  static int printOption(User user){
        System.out.println("");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please choose Option-----------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                                                                                                                                                 |");
        System.out.println("|                                                                                 1 Back To Menu                                                                                                  |");
        if (user.getRole().equalsIgnoreCase("admin")){
            System.out.println("|                                                                                 2 Delete Book                                                                                                   |");
            System.out.println("|                                                                                 3 Update Book                                                                                                   |");

        }
        System.out.println("|                                                                                 4 borrow book                                                                                                   |");
        if (user.getRole().equalsIgnoreCase("admin")){
            System.out.println("|                                                                                 5 mark book as lost                                                                                             |");

        }
        System.out.println("|                                                                                                                                                                                                 |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        Scanner scanner =new Scanner(System.in);
        return  scanner.nextInt();
    }
    public  static int printStatistiqueOption(){
        System.out.println("");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please choose Option-----------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                                                                                                                                                 |");
        System.out.println("|                                                                                 1 show total lost,borrowed,available Books                                                                                                  |");
        System.out.println("|                                                                                 2 show Total books lost  today                                                                                                   |");
        System.out.println("|                                                                                 3 show total books lost between two dates                                                                                                  |");
        System.out.println("|                                                                                 4 show total books borrowed today                                                                                                   |");
        System.out.println("|                                                                                 5 show total books borrowed between two dates                                                                                             |");
        System.out.println("|                                                                                                                                                                                                 |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        Scanner scanner =new Scanner(System.in);
        return  scanner.nextInt();
    }
    public  static int printReturnOption(){
        System.out.println("");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                                      to return this book  please press on 1                                                                                      |");
        System.out.println("|                                                                      to back to menu please press on 2                                                                                          |");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        Scanner scanner =new Scanner(System.in);
        return  scanner.nextInt();
    }

    public static User  printLogin(){
         User user = new User();
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|----------------------------------------------------------------------------------------Connection ----------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println(" ");
        System.out.println(" ");
        Scanner myObj = new Scanner(System.in);
        System.out.println("|------------------------------------------------------------------------------------------User Name----------------------------------------------------------------------------------------------|");
        System.out.println("    ");
        System.out.println("    ");
        user.setEmail(myObj.nextLine());
        System.out.println("|------------------------------------------------------------------------------------------Password-----------------------------------------------------------------------------------------------|");
        System.out.println("    ");
        System.out.println("    ");
        user.setPassword(myObj.nextLine());
         return user;
    }


    public static User readNewUserData(){
        Scanner myObj = new Scanner(System.in);
        User user=new User();
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|------------------------------------------------------------------------------please Fill this form ---------------------------------------------------------------------------------------------|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------CIN ----------------------------------------------------------------------------------------------------|");
        user.setCin(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Last name ---------------------------------------------------------------------------------------------------|");
        user.setNom(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------First Name --------------------------------------------------------------------------------------------------|");
        user.setPrenom(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Email------------------------------------------------------------------------------------------------|");
        user.setEmail(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------Password------------------------------------------------------------------------------------------------|");
        user.setPassword(myObj.nextLine());
        System.out.println("    ");
        System.out.println("    ");
        System.out.println("|----------------------------------------------------------------------------------------new year ----------------------------------------------------------------------------------------------------|");

        return  user;
    }
}
