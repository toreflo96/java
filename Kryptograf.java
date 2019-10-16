import krypto.*;

public class Kryptograf implements Runnable{
  private Meldinger meldinger;

  public Kryptograf(Meldinger meldinger){
    this.meldinger = meldinger;
  }

  @Override
  public void run(){
    while(meldinger.telegrafIkkeFerdig() || meldinger.ingenKrypterteMeldinger() == false){
      Melding melding = meldinger.hentKryptertMelding();
      String dekryptertMelding = Kryptografi.dekrypter(melding.hentKryptertMelding());
      melding.dekryptertMelding(dekryptertMelding);
	    this.meldinger.settInnDekryptertMelding(melding);
    }
  }
}
