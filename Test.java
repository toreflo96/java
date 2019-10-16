class Test {
  public static void main(String[] args) {

    testProsessor(4, 3.2);
    testNode1(16, 4, 3.2);
    testNode2(16, 4, 4, 3.2, 3.2);
    testRack(3);
    testRegneklynge(20);
    hovedprogram();








  }

  public static void testProsessor(int antallKjerner, double maksKlokkehastighet){
    Prosessor ny = new Prosessor(antallKjerner, maksKlokkehastighet);
    System.out.println(ny.antallKjerner());
    System.out.println(ny.maksKlokkehastighet());
    System.out.println(ny.flops());
  }

  public static void testNode1(int minne, int antallKjerner, double maksKlokkehastighet){
    Node nyNode = new Node(minne, antallKjerner, maksKlokkehastighet);
    System.out.println("Minne: "+nyNode.minne());
    System.out.println(nyNode.antallProsessorer());
    System.out.println(nyNode.flops());
  }

  public static void testNode2(int minne, int antallKjerner1, int antallKjerner2, double maksKlokkehastighet1, double maksKlokkehastighet2){
    Node nyNode = new Node(minne, antallKjerner1, antallKjerner2, maksKlokkehastighet1, maksKlokkehastighet2);
    System.out.println("Minne: "+nyNode.minne());
    System.out.println(nyNode.antallProsessorer());
    System.out.println(nyNode.flops());
  }

  public static void testRack(int rackSize){
    Node nyNode = new Node(16, 4, 4, 3.2, 3.2);
    Rack nyttRack = new Rack(rackSize);
    nyttRack.leggTilNode(nyNode, 1);
    nyttRack.leggTilNode(nyNode, 2);
    nyttRack.leggTilNode(nyNode, 3);
    System.out.println("Antall noder i Rack: "+nyttRack.antallNoder());
    System.out.println("flops "+nyttRack.flops());
    System.out.println("noder med nok minne: "+nyttRack.noderMedNokMinneRack(15));
}

  public static void testRegneklynge(int noderPerRack){
    Node nyNode = new Node(16, 4, 4, 3.2, 3.2);
    Regneklynge ny = new Regneklynge(noderPerRack);
    System.out.println(ny.antallRack());
    ny.settInnNode(nyNode);
    ny.settInnNode(nyNode);
    ny.settInnNode(nyNode);
    System.out.println("flops "+ny.flops());
    System.out.println("racks: "+ny.antallRack());
    System.out.println("noder med nok minne: "+ny.noderMedNokMinne(15));
    System.out.println("antall noder: "+ny.antallNoder());
  }

  public static void hovedprogram(){
    Node node1 = new Node(64, 8, 8, 2.6, 2.6);
    Node node2 = new Node(1024, 8, 8, 2.3, 2.3);
    Regneklynge abel = new Regneklynge(12);
    for(int i=0; i<650; i++){
      abel.settInnNode(node1);
    }
    for(int i=0; i<16; i++){
      abel.settInnNode(node2);
    }
    System.out.println("Flops: "+abel.flops());
    System.out.println("Antall rack: "+abel.antallRack());
  }



}
