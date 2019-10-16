class Legemiddel {
  private String navn;
  private static int statiskId = 0;
  private int id;
  private double pris;
  private double virkestoff;

  public Legemiddel(String navn, double pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    this.id=statiskId;
    this.statiskId++;
  }

  public int hentId(){
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public double hentPris(){
    return pris;
  }

  public double hentVirkestoff(){
    return virkestoff;
  }

  public String toString(){
    return id + navn + virkestoff; 
  }
}
