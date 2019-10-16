public abstract class Rute {
    final int rad, kol;
    final Labyrint labyrint;
    public Rute nord, soer, oest, vest;
    boolean paaVeien = false;

    Rute(int rad, int kol, Labyrint labyrint) {
        this.rad = rad;
        this.kol = kol;
        this.labyrint = labyrint;
    }

    public static Rute lagRute(char tegn, int rad, int kol, Labyrint labyrint) {
        if (tegn == '#') {
            return new SortRute(rad, kol, labyrint);
        }
        else if (rad > 0 && kol > 0 && rad < labyrint.hoyde-1 &&
                 kol < labyrint.bredde-1) {
            return new HvitRute(rad, kol, labyrint);
        }
        else {
            return new Aapning(rad, kol, labyrint);
        }
    }

    public void finnUtvei() {
        gaa("", null);
    }

    public abstract void gaa(String vei, Rute komFra);

    public abstract char tilTegn();

    @Override
    public String toString() {
        return "("+(kol+1)+","+(rad+1)+")";
    }
}
