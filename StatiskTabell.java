import java.util.*;

public class StatiskTabell<T> implements Tabell<T>{
  private T [] array;
  private int kapasitet;
  private int storrelse;

  public StatiskTabell(int kapasitet){
    this.kapasitet = kapasitet;
    array = (T[]) new Object[kapasitet];
  }

  @Override
  public int storrelse(){
    return storrelse;
  }

  @Override
  public boolean erTom(){
    return storrelse == 0;
  }

  @Override
  public void settInn(T element){
    if(storrelse == kapasitet){
      throw new FullTabellUnntak(storrelse);
    }
    array[storrelse] = element;
    storrelse++;
  }

  @Override
  public T hentFraPlass(int plass){
    if(plass>storrelse||plass<0){
      throw new UgyldigPlassUnntak(plass, storrelse);
    }
    return array[plass];
  }

  public Iterator<T> iterator() {
    return new StatiskTabellIterator();
  }

  public class StatiskTabellIterator implements Iterator<T>{
    private int peker;

    public boolean hasNext(){
      return peker<storrelse;
    }

    public T next(){
      T neste = array[peker];
      peker++;
      return neste;
    }

    public void remove(){
      throw new UnsupportedOperationException();
    }
  }

}
