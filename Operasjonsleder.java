import krypto.*;

public class Operasjonsleder implements Runnable {
  private Meldinger meldinger;

  public Operasjonsleder(Meldinger meldinger){
    this.meldinger = meldinger;
  }

  @Override
  public void run(){
    meldinger.skrivUtTilFil();
  }
}
