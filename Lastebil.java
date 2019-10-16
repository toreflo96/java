public class Lastebil extends Fossilbil{
  private double nyttevekt;

  public Lastebil(String regNr, String type, double co2Utslipp, double nyttevekt){
    super(regNr, type, co2Utslipp);
    this.nyttevekt = nyttevekt;
  }

  @Override
  public void skrivUt(){
    super.skrivUt();
    System.out.println("Nyttevekt: "+nyttevekt);
  }
}
