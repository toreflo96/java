import java.util.Iterator;


interface Liste<T> extends Iterable<T> {
    /**
     * Beregner antall elementer i listen
     * @return      antall elementer i listen
     */
    public int storrelse();

    /**
     * Sjekker om listen er tom
     * @return      om listen er tom
     */
    public boolean erTom();

    /**
     * Setter inn et element i listen
     * @param   element     elementet som settes inn
     */
    public void settInn(T element);

    /**
     * Fjerner et element fra listen. Hvis listen er tom,
     * returneres null.
     * @return      elementet
     */
    public T fjern();
}


// LENKELISTER

class Node<T> {
    private Node<T> neste;
    private Node<T> forrige;
    private T innhold;


    Node() {
        this(null);
    }

    Node(T innhold) {
        this.innhold = innhold;
    }

    public void settNeste(Node<T> neste) {
        this.neste = neste;
    }

    public void settForrige(Node<T> forrige) {
        this.forrige = forrige;
    }

    public Node<T> hentNeste() {
        return this.neste;
    }

    public Node<T> hentForrige() {
        return this.forrige;
    }

    public T hentInnhold() {
        return this.innhold;
    }

    public void settInnMellom(Node<T> venstre, Node<T> hoyre) {
        venstre.settNeste(this);
        hoyre.settForrige(this);
        settNeste(hoyre);
        settForrige(venstre);
    }

    public void kobleUt() {
        this.neste.settForrige(this.forrige);
        this.forrige.settNeste(this.neste);

        // for aa vaere paa den sikre siden
        this.neste = null;
        this.forrige = null;
    }
}


class DobbelLenkelisteIterator<T> implements Iterator<T> {
    Node<T> ende;
    Node<T> denne;

    DobbelLenkelisteIterator(Node<T> ende) {
        this.ende = ende;
        this.denne = ende.hentNeste();
    }

    public boolean hasNext() {
        return denne != ende;
    }

    public T next() {
        T innhold = denne.hentInnhold();
        denne = denne.hentNeste();
        return innhold;
    }

    public void remove() {}
}


class DobbelLenkeliste<T> implements Iterable<T> {
    protected int storrelse;
    protected Node<T> ende;
    protected Node<T> hode;
    protected Node<T> hale;

    DobbelLenkeliste() {
        this.storrelse = 0;
        initialiser();
    }

    protected void initialiser() {
        this.ende = new Node<T>();
        ende.settNeste(ende);
        ende.settForrige(ende);
        this.hode = ende;
        this.hale = ende;
    }

    public int storrelse() {
        return storrelse;
    }

    public boolean erTom() {
        return storrelse() == 0;
    }

    public void settInnForan(T innhold) {
        Node<T> ny = new Node<T>(innhold);
        Node<T> etter = this.hode.hentNeste();
        ny.settInnMellom(this.hode, etter);
        storrelse++;
    }

    public void settInnBak(T innhold) {
        Node<T> ny = new Node<T>(innhold);
        Node<T> foer = this.hale.hentForrige();
        ny.settInnMellom(foer, this.hale);
        storrelse++;
    }

    public T taUtForan() {
        if (erTom()) {
            return null;
        }
        Node<T> foerst = this.hode.hentNeste();
        foerst.kobleUt();
        storrelse--;
        return foerst.hentInnhold();
    }

    public T taUtBak() {
        if (erTom()) {
            return null;
        }
        Node<T> bakerst = this.hale.hentForrige();
        bakerst.kobleUt();
        storrelse--;
        return bakerst.hentInnhold();
    }

    public Iterator<T> iterator() {
        return new DobbelLenkelisteIterator<T>(ende);
    }
}


class OrdnetLenkeliste<T extends Comparable<T>> extends DobbelLenkeliste<T>
      implements Liste<T> {
    OrdnetLenkeliste() {
        super();
    }

    public void settInn(T innhold) {
        Node<T> ny = new Node<T>(innhold);
        finnRiktigPlassTilNode(ny);
        storrelse++;
    }

    private void finnRiktigPlassTilNode(Node<T> ny) {
        T t = ny.hentInnhold();
        Node<T> denne = hode;
        while ((denne = denne.hentNeste()) != hale) {
            if (t.compareTo(denne.hentInnhold()) <= 0) {
                break;
            }
        }
        ny.settInnMellom(denne.hentForrige(), denne);
    }

    public T fjern() {
        return taUtForan();
    }
}

class Stabel<T> extends DobbelLenkeliste<T> implements Liste<T> {
    Stabel() {
        super();
    }

    public void settInn(T innhold) {
        settInnForan(innhold);
    }

    public T fjern() {
        return taUtForan();
    }
}

class Koe<T> extends DobbelLenkeliste<T> implements Liste<T> {
    Koe() {
        super();
    }

    public void settInn(T innhold) {
        settInnBak(innhold);
    }

    public T fjern() {
        return taUtForan();
    }
}
