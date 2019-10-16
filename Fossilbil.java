public abstract class Fossilbil extends Bil {
  private double co2Utslipp;

  public Fossilbil(String regNr, String type, double co2Utslipp){
    super(regNr, type);
    this.co2Utslipp = co2Utslipp;
  }

  @Override
  public void skrivUt(){
    super.skrivUt();
    System.out.println("CO2-utslipp: "+co2Utslipp);
  }
}
