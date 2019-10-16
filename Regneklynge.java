import java.util.ArrayList;
import java.util.Arrays;

class Regneklynge {
  ArrayList<Rack> racks = new ArrayList<Rack>();
  int noderPerRack;
  int antallRack;
  int antallNoder;

  public Regneklynge(int noderPerRack){
    this.noderPerRack = noderPerRack;
    opprettRack();
  }

  public void opprettRack(){
    racks.add(new Rack(noderPerRack));
    antallRack++;
  }

  //Setter inn node i et ledig rack objekt, dersom det ikke finnes ledig Rack objekt
  //opprettes et nytt Rack objekt som noden plasseres i.
  public void settInnNode(Node node) {
    for(int i=0; i<racks.size(); i++) {
      if(racks.get(i).harLedigPlass()) {
        racks.get(i).leggTilNode(node, i);
        System.out.println("node ble satt inn i rack nr "+i);
      }
      else{
        opprettRack();
        System.out.println("nytt rack opprettet "+racks.size());
      }
    }
  }

  public int antallRack(){
    return antallRack;
  }

  public double flops(){
    double sum=0;
    for(int i=0; i<racks.size(); i++){
      if(racks.get(i)!=null){
        sum+=racks.get(i).flops();
      }
    }
    return sum;
  }

  public int antallNoder(){
    int sum=0;
    for(int i=0; i<racks.size(); i++){
      if(racks.get(i)!=null){
        sum+=racks.get(i).antallNoder();
      }
    }
    return sum;
  }

  public int noderMedNokMinne(int paakrevdMinne){
    int sum=0;
    for(int i=0; i<racks.size(); i++){
      if(racks.get(i)!=null){
        sum+=racks.get(i).noderMedNokMinneRack(paakrevdMinne);
      }
    }
    return sum;
  }

  public void skrivUt(){
    for(int i=0; i<racks.size(); i++){
      System.out.println("i: "+i);
      System.out.println("noder: "+racks.get(i).antallNoder());
    }
  }



}
