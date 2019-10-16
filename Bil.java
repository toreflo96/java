public abstract class Bil{
  private String regNr;
  private String type;

  public Bil(String regNr, String type){
    this.regNr = regNr;
    this.type = type;
  }

  public void skrivUt(){
    System.out.println("Type motorvogn: "+type);
    System.out.println("Reg.nr: "+regNr);
  }

}
