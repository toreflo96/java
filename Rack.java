import java.util.Arrays;

class Rack {
  Node [] noder;
  int antallNoder;
  int rackSize;

  public Rack(int rackSize){
    this.rackSize = rackSize;
    noder = new Node[rackSize];
  }

  public void leggTilNode(Node node, int plass) {
    if(antallNoder < noder.length){
      noder[plass]=node;
      antallNoder++;
    }
  }


  public boolean harLedigPlass(){
    return antallNoder < noder.length;
  }

  public int antallNoder(){
    return antallNoder;
  }

  public double flops(){
    double sum =0;
    for(int i=0; i<noder.length; i++){
      if(noder[i]!=null){
        sum+=noder[i].flops();
      }
    }
    return sum;
  }

  public int noderMedNokMinneRack(int paakrevdMinne){
    int sum=0;
    for(int i=0; i<noder.length; i++){
      if(noder[i]!=null && noder[i].minne>paakrevdMinne){
        sum++;
      }
    }
    return sum;
  }

}
