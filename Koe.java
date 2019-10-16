import java.util.Iterator;
import java.util.NoSuchElementException;

public class Koe<T> implements Liste<T>{
  private Node foran = null;
  private Node bak = null;
  private int antallNoder;

  public Koe(){
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
    return antallNoder==0;
  }

  @Override
  public void settInn(T element){
    Node ny = new Node(element);
    ny.neste = bak;
    ny.forrige = bak.forrige;
    bak.forrige.neste = ny;
    bak.forrige=ny;
    antallNoder++;
  }

  @Override
  public T fjern() throws NoSuchElementException {
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
  }

  @Override
  public Iterator<T> iterator(){
    return new KoeIterator();
  }

  public class KoeIterator implements Iterator<T>{
    private Node denne = foran;
    private Node forrige = null;

    @Override
    public boolean hasNext(){
      return denne.neste != bak;
    }

    @Override
    public T next(){
      if (!hasNext()){
        throw new NoSuchElementException();
      }
      denne = denne.neste;
      return denne.data;
    }

    @Override
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
}
