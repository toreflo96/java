import krypto.*;
import java.util.concurrent.CountDownLatch;

public class Telegrafist implements Runnable {
  private Kanal linje;
  private int kanalId;
  private Meldinger meldinger;
  private CountDownLatch teller;

  public Telegrafist(Kanal linje, Meldinger meldinger, CountDownLatch teller){
    this.linje = linje;
    this.kanalId = linje.hentId();
    this.meldinger = meldinger;
    this.teller = teller;
  }

  @Override
  public void run(){
    String lytter = linje.lytt();
    while(lytter != null){
      //lager en medling av det den lytter+id
      Melding melding = new Melding(lytter, this.kanalId);
      //legger til melding i liste med krypterte meldinger
      meldinger.settInnKryptertMelding(melding);
      //lytter pa nytt, skal vere null
      lytter = linje.lytt();

      if(lytter == null){
        //ferdig
        System.out.println("ferdig");
        //hva skjer her???
        this.teller.countDown();
      }
    }
  }
}
