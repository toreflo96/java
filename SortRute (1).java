public class SortRute extends Rute {
    SortRute(int rad, int kol, Labyrint labyrint) {
        super(rad, kol, labyrint);
    }

    @Override
    public char tilTegn() {
        return '#';
    }

    @Override
    public void gaa(String vei, Rute komFra) {
        return;
    }
}
