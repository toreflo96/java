class Fastlege extends Lege implements Kommuneavtale{
  private int avtalenummer;

  public Fastlege(String navn, int avtalenummer){
    super(navn);
    this.avtalenummer = avtalenummer;
  }

  public int hentAvtalenummer(){
    return this.avtalenummer;
  }

  public String toString(){
    return super.toString() + avtalenummer;
  }
}
