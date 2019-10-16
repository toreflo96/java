abstract class Rute {
  private int rad;
  private int kol;
  private Labyrint lab;
  private Rute[] retninger = new Rute[4];

  public Rute(int rad, int kol, Labyrint lab){
    this.rad = rad;
    this.kol = kol;
    this.lab = lab;
  }

  public void settRetninger(){
    retninger[0] = lab.hentRute(rad-2, kol-1);
    retninger[1] = lab.hentRute(rad, kol-1);
    retninger[2] = lab.hentRute(rad-1, kol-2);
    retninger[3] = lab.hentRute(rad-1, kol);
  }

  public void finnUtvei(){
    gaa("", this);
  }

  public void gaa(String sti, Rute fra){
    if(this instanceof HvitRute){
      if(this instanceof Aapning){
        sti += "("+kol+","+rad+")";
        System.out.println("Aapning");
        lab.leggTilUtvei(sti);
      }
      else {
        sti += "("+kol+","+rad+")-->";
        System.out.println(sti);
        int i=0;
        for(Rute rute : retninger){
          i++;
          System.out.println(rute+ " 35"+i);
          if(rute != null && rute != fra){
            System.out.println("37");
            rute.gaa(sti, this);
          }
        }
      }
    }
    else {
      System.out.println("ingen hvit rute");
    }
  }

  abstract char tilTegn();
}
