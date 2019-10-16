import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Labyrint {
  private Rute[][] labyrint;
  private int rader;
  private int kolonner;
  private Stabel<String> utveier;

  public static Labyrint lesFraFil(File fil)throws FileNotFoundException{
    int rader;
    int kolonner;
    char[][] lab;
    Scanner scanner = new Scanner(fil);
    rader = scanner.nextInt();
    kolonner = scanner.nextInt();
    lab = new char[rader][kolonner];
    int i=0;
    while(scanner.hasNext()){
      lab[i] = scanner.next().toCharArray();
      i++;
    }
    return new Labyrint(rader,kolonner,lab);
  }

  private Labyrint(int rader, int kolonner, char[][] lab){
    this.rader = rader;
    this.kolonner = kolonner;
    this.utveier = new Stabel<String>();
    labyrint = new Rute[rader][kolonner];
    for(int i=0; i<rader; i++){
      for(int j=0; j<kolonner; j++){

        if(lab[i][j]=='.'){
          if(i==0 || j==0 || j==(rader-1) || j==(kolonner-1)){
            labyrint[i][j] = new Aapning(i+1, j+1, this);
            //System.out.println("ny åpning"+i+j);
          }
          else{
            labyrint[i][j] = new HvitRute(i+1, j+1, this);
            //System.out.println("ny hvitrute"+i+j);
          }
        }
        else if(lab[i][j]=='#'){
          labyrint[i][j] = new SortRute(i+1, j+1, this);
          //System.out.println("ny sortrute"+i+j);
        }
      }
    }
    for(Rute[] rute : labyrint){
      for(Rute rute2 : rute){
        rute2.settRetninger();
      }
    }
  }


  public Stabel<String> finnUtveiFra(int kol, int rad){
    utveier = new Stabel<String>();
    labyrint[rad-1][kol-1].finnUtvei();
    return utveier;
  }

  public void leggTilUtvei(String utvei){
    utveier.settInn(utvei);
  }

  public void settMinimalUtskrift(){}


  public int hentRader(){
    return rader;
  }

  public int hentKolonner(){
    return kolonner;
  }

  public Rute hentRute(int rad, int kol){
    if(rad<0 || kol<0 || rad>=rad || kol<=kol){
      return null;
    }
    return labyrint[rad][kol];
  }

  public String toString(){
    String string = new String("");
    for(int rad=0; rad<rader; rad++){
      for(int kol=0; kol<kolonner; kol++){
        if(labyrint[rad][kol] instanceof HvitRute){
          string += " ";
        }
        else {
          string += "#";
        }
      }
    }
    return string;
  }
}
