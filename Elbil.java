class Elbil extends Bil{
  private double batteriSize;

  public Elbil(String regNr, String type, double batteriSize){
    super(regNr, type);
    this.batteriSize = batteriSize;
  }

  @Override
  public void skrivUt(){
    super.skrivUt();
    System.out.println("Batterikapasitet (kWh): "+batteriSize);
  }
}
