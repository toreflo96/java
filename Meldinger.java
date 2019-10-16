import krypto.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class Meldinger {
  private int antallTelegrafister;
  private int antallKryptografer;
  private Koe<Melding> krypterteMeldinger;
  private Koe<Melding> dekrypterteMeldinger;
  private boolean telegrafFerdig;
  private Lock laas;
  private Condition krypterteMeldingerIkkeTom;
  private int antall;

  public Meldinger(int antallTelegrafister, int antallKryptografer){
    this.antallTelegrafister = antallTelegrafister;
    this.antallKryptografer = antallKryptografer;
    krypterteMeldinger = new Koe<Melding>();
    dekrypterteMeldinger = new Koe<Melding>();
    telegrafFerdig = false;

    laas = new ReentrantLock();
    krypterteMeldingerIkkeTom = laas.newCondition();
  }

  //#####################TELEGRAF######################

  public void settInnKryptertMelding(Melding melding){
    laas.lock();
    try {
      krypterteMeldinger.settInn(melding);
      krypterteMeldingerIkkeTom.signalAll();
    }
    finally {
      laas.unlock();
    }
  }

  public void skrivUtKrypterteMeldinger(){
    for(Melding m : krypterteMeldinger){
      System.out.println(m);
    }
  }

  public void telegrafFerdig(){
    this.telegrafFerdig = true;
  }

  public boolean telegrafIkkeFerdig(){
    return !this.telegrafFerdig;
  }

  //#######################KRYPTOGRAF##################

  public Melding hentKryptertMelding(){
    Melding melding = null;
    laas.lock();
    try {
      while(ingenKrypterteMeldinger()){
        krypterteMeldingerIkkeTom.await();
      }
      melding = krypterteMeldinger.fjern();
    }
    catch (InterruptedException e){}
    finally{
      laas.unlock();
    }
    return melding;
  }

  public void settInnDekryptertMelding(Melding melding){
    laas.lock();
    try {
      dekrypterteMeldinger.settInn(melding);
    }
    finally {
      laas.unlock();
    }
  }

  public boolean ingenKrypterteMeldinger(){
    return krypterteMeldinger.erTom();
  }

  public void skrivUtDekrypterteMeldinger(){
    for (Melding m : dekrypterteMeldinger){
      System.out.println(m);
    }
  }

  //#####################OPERASJONSLEDER##############

  public void skrivUtTilFil(){
    //-skrive ut dekrypterte meldinger i riktig rekkefølge til fil
    //-en fil for hver kanal, hver melding atskilt av to linjeskift
    //sortere etter kanal og sekvensnummer
    //opprette en ny fil for hver kanal
    //skrive ut hver melding med to linjeskift
  }
}
