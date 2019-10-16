import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    private final Rute[][] ruter;
    private boolean[][] boolLabyrint;
    public final int hoyde, bredde;
    public static boolean verbose = true;
    public Liste<String> losninger;
    public int antallLosninger;

    public static void main(String[] args) throws FileNotFoundException {
        Labyrint labyrint = Labyrint.lesFraFil(new File("3.in"));
    }

    private Labyrint(Rute[][] ruter, int hoyde, int bredde) {
        this.ruter = ruter;
        this.hoyde = hoyde;
        this.bredde = bredde;
        this.boolLabyrint = new boolean[hoyde][bredde];
           for (int i = 0; i < hoyde; i++){
               for(int j = 0; j < bredde; j++){
                   boolLabyrint[i][j] = false;
               }
           }
    }

    public boolean[][] hentBoolLabyrint(){
       return boolLabyrint;
   }

   public Rute[][] returnerRutearray(){
     return ruter;
   }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);
        String[] storrelse = scanner.nextLine().split(" ");
        int hoyde = Integer.parseInt(storrelse[0]);
        int bredde = Integer.parseInt(storrelse[1]);
        Rute[][] ruter = new Rute[hoyde][bredde];
        Labyrint labyrint = new Labyrint(ruter, hoyde, bredde);

        for (int rad = 0; rad < hoyde; rad++) {
            char[] linje = scanner.nextLine().toCharArray();
            for (int kol = 0; kol < bredde; kol++) {
                ruter[rad][kol] = Rute.lagRute(linje[kol], rad, kol, labyrint);
            }
        }

        for (int rad = 1; rad < hoyde-1; rad++) {
            for (int kol = 1; kol < bredde-1; kol++) {
                ruter[rad][kol].nord = ruter[rad-1][kol];
                ruter[rad][kol].vest = ruter[rad][kol-1];
                ruter[rad][kol].soer = ruter[rad+1][kol];
                ruter[rad][kol].oest = ruter[rad][kol+1];
            }
        }
        return labyrint;
    }

    public void settMinimalUtskrift() {
        verbose = false;
    }

    public Liste<String> finnUtveiFra(int kol, int rad) {
        losninger = new Koe<String>();
        ruter[rad-1][kol-1].finnUtvei();
        return losninger;
    }

    @Override
    public String toString() {
        String retur = "";
        for (Rute[] rad : ruter) {
            for (Rute rute : rad) {
                retur += rute.tilTegn();
            }
            retur += "\n";
        }
        return retur;
    }
}
