public class Melding {
  private static int totalSekvensnummer = 0;
  private int sekvensnummer;
  private int kanalId;
  private String kryptertMelding;
  private String dekryptertMelding;

  public Melding(String kryptertMelding, int kanalId){
    this.kryptertMelding = kryptertMelding;
    this.kanalId = kanalId;
    this.sekvensnummer = this.totalSekvensnummer;
    this.totalSekvensnummer ++;
  }

  public void dekryptertMelding(String dekryptertMelding) {
    this.dekryptertMelding = dekryptertMelding;
  }

  public String toString(){
    return "" + this.kanalId + " " + this.sekvensnummer + " " + this.dekryptertMelding;
  }

  public String hentKryptertMelding(){
    return kryptertMelding;
  }

  public int hentSekvensnummer(){
    return sekvensnummer;
  }

  public int hentId(){
    return kanalId;
  }


}
