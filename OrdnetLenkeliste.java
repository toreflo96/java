import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrdnetLenkeliste<T extends Comparable<T>> implements Liste<T> {
  private Node foran = null;
  private Node bak = null;
  private int antallNoder;

  public OrdnetLenkeliste(){
    foran = new Node(null);
    bak = new Node(null);
    foran.neste = bak;
    bak.forrige = foran;
  }

  @Override
  public int storrelse(){
    return antallNoder;
  }

  @Override
  public boolean erTom(){
    return antallNoder == 0;
  }

  @Override
  public void settInn(T element){
    Node ny = new Node(element);
    finnRiktigPlassTilNode(ny);
    antallNoder++;
  }

  public void finnRiktigPlassTilNode(Node n){
    T t = n.data;
    Node denne = foran;
    while((denne = denne.neste) != bak){
      /*
      eks: la oss si vi har en liste med 0,1,3,4,0 og vi får inn node n med verdi 2.
      Første test sjekker om 0 er mindre/større/lik n(2). Vi får positivt tall. Denne = denne sin neste.
      Sjekker om 1 er mindre/større/lik n(2). Får positivt tall. Denne = denne sin neste.
      Sjeker om 3 er mindre/større/lik n(2). Får negativt tall. Hopper ut av while.
      Setter n inn mellom denne.forrige(1) og denne(3).
      */
      if(t.compareTo(denne.data) <= 0){
        break;
      }
      denne = denne.neste;
    }
    n.settInnMellom(denne.forrige, denne);
  }

  @Override
  public T fjern() {
        if(erTom()){
          throw new NoSuchElementException();
        }
        T data = foran.neste.data;
        foran.neste = foran.neste.neste;
        foran.neste.forrige = foran;
        antallNoder--;
        return data;
    }

  private class Node {
    T data;
    Node neste;
    Node forrige;

    public Node(T data){
      this.data=data;
    }

    public boolean settInnMellom(Node venstre, Node hoyre){
      if(venstre.neste != hoyre || hoyre.forrige != venstre){
        return false;
      }
      venstre.neste = this;
      hoyre.forrige = this;
      this.neste = hoyre;
      this.forrige = venstre;
      return true;
    }
  }

  @Override
  public Iterator<T> iterator(){
    return new OrdnetLenkelisteIterator();
  }

  public class OrdnetLenkelisteIterator implements Iterator<T>{
    private Node denne = foran;

    @Override
    public boolean hasNext(){
      return denne.neste != bak;
    }

    @Override
    public T next(){
      denne = denne.neste;
      return denne.data;
    }

    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }

}
