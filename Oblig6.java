import krypto.*;
import java.util.concurrent.CountDownLatch;

public class Oblig6{
  public static void main(String[] args) {
    int antallTelegrafister = 3;
    int antallKryptografer = 20;
    int antallOperasjonsleder = 1;

    Meldinger meldinger = new Meldinger(antallTelegrafister, antallKryptografer);

    CountDownLatch countdownTelegrafer = new CountDownLatch(antallTelegrafister);
    CountDownLatch countdownKryptografer = new CountDownLatch(antallKryptografer);

    Operasjonssentral ops = new Operasjonssentral(antallTelegrafister);
    Kanal[] kanaler = ops.hentKanalArray();

    Thread[] telegrafer = new Thread[antallTelegrafister];
    Thread[] kryptografer = new Thread[antallKryptografer];
    Thread[] operasjonsleder = new Thread[antallOperasjonsleder];

    // Skaffer telegrafer og starter telegrafene.
    int indeks = 0;
    for(Kanal telegrafLinje: kanaler) {
      Runnable telegraf = new Telegrafist(telegrafLinje, meldinger, countdownTelegrafer);
      telegrafer[indeks] = new Thread(telegraf);
      telegrafer[indeks].start();
      indeks ++;
    }

    for (int i = 0; i<antallKryptografer; i++) {
      Runnable kryptograf = new Kryptograf(meldinger);
	    kryptografer[i] = new Thread(kryptograf);
	    kryptografer[i].start();
    }

    // Venter på telegrafene skal bli ferdig
    try {
      countdownTelegrafer.await();
	    meldinger.telegrafFerdig();
	    System.out.println("TelegraferFerdig");
    }
    catch (InterruptedException ex){
      System.out.println(" Uventet avbrudd ");  System.exit(0);
    }

    try {
      for (int i = 0; i < antallKryptografer; i++) {
        kryptografer[i].join();
      }
    }
    catch (InterruptedException e) {}

    //System.out.println("Skriver ut meldinger:");
    //meldinger.skrivUtDekrypterteMeldinger();
  }
}
