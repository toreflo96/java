public class LegemiddelC extends Legemiddel{

  public LegemiddelC(String navn, double pris, double virkestoff){
    super(navn, pris, virkestoff);
  }

  public String toString(){
    return "legemiddel C. " + super.toString();
  }

}
