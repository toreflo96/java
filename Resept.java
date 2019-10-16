public abstract class Resept {
  private static int statiskId = 0;
  private int id;

  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected int pasientId;
  protected int reit;

  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasientId = pasientId;
    this.reit = reit;
    this.id = statiskId;
    this.statiskId++;
  }

  public int hentId(){
    return id;
  }

  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  public Lege hentLege(){
    return utskrivendeLege;
  }

  public int hentPasientId(){
    return pasientId;
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){
    if(reit == 0){
      return false;
    }
    reit--;
    return true;
  }

  abstract public String farge();

  abstract public double prisAaBetale();

  public String toString(){
    return id + legemiddel.toString() + utskrivendeLege.toString();
  }
}
