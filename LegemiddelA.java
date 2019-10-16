class LegemiddelA extends Legemiddel {
  private int narkotiskStyrke;

  public LegemiddelA(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.narkotiskStyrke = styrke;
  }

  public int hentNarkotiskStyrke(){
    return narkotiskStyrke;
  }

  public String toString(){
    return "legemiddel A. " + narkotiskStyrke + super.toString();
  }
}
