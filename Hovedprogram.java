import java.io.*;
import java.util.*;

class Hovedprogram {
  public static void main(String[] args) {
    lesFil(args[0]);
    try {
      if (args[1].equals("EL")) {
        printEl();
      }
      else if (args[1].equals("FOSSIL")) {
        printFossil();
      }
    }
    catch(ArrayIndexOutOfBoundsException e){
      printEl();
      printFossil();
    }
  }

  private static ArrayList<Bil> biler = new ArrayList<Bil>();

  public static void lesFil(String filnavn){
    Scanner scanner;
    try{
      scanner = new Scanner(new File(filnavn));
      while(scanner.hasNext()){
        String type = scanner.next();
        if(type.equals("EL")){
          String regNr = scanner.next();
          Double batteriKapasitet = Double.parseDouble(scanner.next());
          Bil ny = new Elbil(regNr, type, batteriKapasitet);
          biler.add(ny);
        }
        if(type.equals("PERSONBIL")){
          String regNr = scanner.next();
          Double utslipp = Double.parseDouble(scanner.next());
          int antallSeter = Integer.parseInt(scanner.next());
          Bil ny = new Personbil(regNr, type, utslipp, antallSeter);
          biler.add(ny);
        }
        if(type.equals("LASTEBIL")){
          String regNr = scanner.next();
          Double utslipp = Double.parseDouble(scanner.next());
          Double nyttevekt = Double.parseDouble(scanner.next());
          Bil ny = new Lastebil(regNr, type, utslipp, nyttevekt);
          biler.add(ny);
        }
      }
      scanner.close();
    }
    catch(FileNotFoundException fnfe){
      System.out.println("File not found");
    }
  }

  public static void printEl(){
    for(int i=0; i<biler.size(); i++){
      if(biler.get(i) instanceof Elbil){
        Elbil elbil = (Elbil) biler.get(i);
        elbil.skrivUt();
        System.out.println(" ");
      }
    }
  }

  public static void printFossil(){
    for(int i=0; i<biler.size(); i++){
      if(biler.get(i) instanceof Personbil){
        Personbil personbil = (Personbil) biler.get(i);
        personbil.skrivUt();
        System.out.println(" ");
      }
      if(biler.get(i) instanceof Lastebil){
        Lastebil lastebil = (Lastebil) biler.get(i);
        lastebil.skrivUt();
        System.out.println(" ");
      }
    }
  }
}
