public class Lege implements Comparable<Lege>{
  private String navn;
  private Koe<Resept> resepter = new Koe<Resept>();

  public Lege(String navn){
    this.navn = navn;
  }

  public String hentNavn(){
    return this.navn;
  }

  public int compareTo(Lege annenLege){
    return this.hentNavn().compareTo(annenLege.hentNavn());
  }

  public void leggTilResept(Resept resept){
    this.resepter.settInn(resept);
  }

  public Koe<Resept> hentReseptliste(){
    return this.resepter;
  }

  public String toString(){
    return this.hentNavn();
  }
}
