class Node  {
  Prosessor [] prosessorer = new Prosessor[2];
  int minne;
  int size = 2;
  int antallProsessorer;


  public Node(int minne, int antallKjerner, double maksKlokkehastighet) {
    this.minne = minne;
    prosessorer[0] = new Prosessor(antallKjerner, maksKlokkehastighet);
    antallProsessorer++;
  }

  public Node(int minne, int antallKjerner, int antallKjerner2, double maksKlokkehastighet, double maksKlokkehastighet2) {
    this.minne = minne;
    prosessorer[0] = new Prosessor(antallKjerner, maksKlokkehastighet);
    prosessorer[1] = new Prosessor(antallKjerner2, maksKlokkehastighet2);
    antallProsessorer++;
    antallProsessorer++;
  }

  public int minne(){
    return minne;
  }
  public int antallProsessorer(){
    return antallProsessorer;
  }

  public double flops(){
    double sum = 0;
    for(int i=0; i<prosessorer.length; i++){
      if(prosessorer[i]!=null){
        sum+=prosessorer[i].flops();
      }
    }
    return sum;
  }

}
