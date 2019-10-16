class Prosessor {
  private int antallKjerner;
  private double maksKlokkehastighet;

  public Prosessor (int antallKjerner, double maksKlokkehastighet) {
    this.antallKjerner = antallKjerner;
    this.maksKlokkehastighet = maksKlokkehastighet;
  }

  public int antallKjerner(){
    return antallKjerner;
  }

  public double maksKlokkehastighet(){
    return maksKlokkehastighet;
  }

  public double flops(){
    return antallKjerner*maksKlokkehastighet*8;
  }
}
