public class LegemiddelB extends Legemiddel{
  private int vanedannendeStyrke;

  public LegemiddelB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.vanedannendeStyrke = styrke;
  }

  public int hentVanedannendeStyrke(){
    return vanedannendeStyrke;
  }

  public String toString(){
    return "legemiddel B. " + vanedannendeStyrke + super.toString();
  }

}
