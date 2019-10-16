public class Pasient {
  private static int statiskId = 0;
  private int id;
  private String navn;
  private long fodselsnummer;
  private String gateAdresse;
  private int postnummer;
  private Stabel<Resept> resepter;

  public Pasient(String navn, long fodselsnummer, String gateAdresse, int postnummer){
    this.navn = navn;
    this.fodselsnummer = fodselsnummer;
    this.gateAdresse = gateAdresse;
    this.postnummer = postnummer;
    this.resepter = new Stabel<Resept>();
    this.id=statiskId;
    statiskId++;
  }

  public int hentId(){
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public long hentFodselsnummer(){
    return fodselsnummer;
  }

  public String hentGateadresse(){
    return gateAdresse;
  }

  public int hentPostnummer(){
    return postnummer;
  }

  public void leggTilResept(Resept resept){
    resepter.settInn(resept);
  }

  public Stabel<Resept> hentReseptliste(){
    return this.resepter;
  }

  public String toString(){
    return this.hentNavn() + " " + this.hentFodselsnummer();
  }

}
