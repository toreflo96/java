import java.util.Iterator;

class Legeliste extends OrdnetLenkeliste<Lege> {

  public Lege finnLege(String navn){
    Iterator<Lege> iterator = iterator();
    while(iterator.hasNext()){
      Lege legen = iterator.next();
      if(legen.hentNavn().equals(navn)){
        return legen;
      }
    }
    return null;
  }

  public String[] stringArrayMedNavn(){
    Iterator<Lege> iterator = iterator();
    String[] stringArray = new String[storrelse()];
    for(int i=0; iterator.hasNext(); i++){
      stringArray[i] = iterator.next().hentNavn();
    }
    return stringArray;
  }
}
