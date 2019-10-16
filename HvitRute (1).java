public class HvitRute extends Rute {
    HvitRute(int rad, int kol, Labyrint labyrint) {
        super(rad, kol, labyrint);
    }

    @Override
    public char tilTegn() {
        return '.';
    }

    @Override
    public void gaa(String vei, Rute komFra) {
        if (paaVeien) return;

        paaVeien = true;

        vei += toString() + " --> ";

        if (komFra != nord) nord.gaa(vei, this);
        if (komFra != oest) oest.gaa(vei, this);
        if (komFra != vest) vest.gaa(vei, this);
        if (komFra != soer) soer.gaa(vei, this);

        paaVeien = false;
    }
}
