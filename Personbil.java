public class Personbil extends Fossilbil{
  private int antallSeter;

  public Personbil(String regNr, String type, double co2Utslipp, int antallSeter){
    super(regNr, type, co2Utslipp);
    this.antallSeter = antallSeter;
  }

  @Override
  public void skrivUt(){
    super.skrivUt();
    System.out.println("Antall seter: "+antallSeter);
  }
}
