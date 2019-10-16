public class BlaaResept extends Resept{

  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public String farge(){
    return "blaa";
  }

  public double prisAaBetale(){
    return hentLegemiddel().hentPris()*0.25;
  }

}
