import com.sun.prism.paint.Stop;

class Lenkeliste<T> implements Liste<T> {
    protected Node<T> start;

    public Node<T> hentStart() {
        return start;
    }

    public int stoerrelse() {
        if (start == null) {
            return 0;
        }
        
        int teller = 0;
        Node<T> tmp = start;
        while (true) {
            if (tmp.neste == null) {
                return teller+1;
            }
            teller++;
            tmp = tmp.neste;
        }
    }

    public void leggTil(int pos, T x) {
        Node<T> tmp = start;
        int teller = 0;
        Node<T> nynode;
        if (pos > stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }

        if (pos == 0) {
            nynode = new Node<T>(x);
            nynode.neste = start;
            start = nynode;
        }

        while (tmp != null) {
            if(teller == pos-1) {
                nynode = new Node<T>(x);
                nynode.neste = tmp.neste; 
                tmp.neste = nynode;
            }
            teller++;
            tmp = tmp.neste;
        }
    }

    public void leggTil(T x) {
        Node<T> tmp;
        if (start == null) {
            start = new Node<T>(x);
            return;
        }
        while (true) {
            if (tmp.neste == null) {
                // Vi har kommet til slutten av lista
                tmp.neste = new Node<T>(x);
                return;
            } else {
                tmp = tmp.neste;
            }
        }
    }
//Metoden sett(int pos, T x) skal sette inn elementet på en gitt posisjon og overskrive
//det som var der fra før av.
    public void sett(int pos, T x) {
        Node<T> tmp = start;
        Node<T> erstatning = new Node<T>(x);
        int teller = 0;
        if (start == null) {
            throw new UgyldigListeIndeks(-1);
        }
        if (pos == 0 ) { 
            start = erstatning;
            erstatning.neste = tmp.neste;
            return;
        }
        // Edge-case:
        if (pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }

        //lager en lokke som gaar gjennom lenket liste
        while (true) {
            // Sjekker om vi har kommet frem til riktig element.
            if (teller == pos - 1) {
                //gjor dette forst, for aa ikke miste handtak til resterende etterfolgende
                erstatning.neste = tmp.neste.neste;
                tmp.neste = erstatning;
                return;
            }
            tmp = tmp.neste;
            teller ++; 
        }
    }

    /**
     * Hent et element i spesifisert posisjon i Lenkeliste.
     * @return
     *  T elementet paa gitt plass
     */
    public T hent(int pos) {
        // Sjekk forst om pos ikke er gyldig.
        if (pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        } else if (stoerrelse() == 0) {
            throw new UgyldigListeIndeks(-1);
        }
        Node<T> tmp = start;
        int teller = 0;
        while (true) {
            if (teller == pos) {
                return tmp.data;
            }
            tmp = tmp.neste;
            teller++;
        }
    }

    public T fjern(int pos) {
        if (pos >= stoerrelse() || pos < 0) 
            throw new UgyldigListeIndeks(pos);
        if (pos == 0) {
            return fjern();
        }
        // Her må pos være fra og med 1 til og med storrelse
        Node<T> tmp = start;
        int teller = 0;
        Node<T> elementSomFjernes = null;
        //lager en lokke som gaar gjennom lenket liste
        while (true) {
            //Naar kommer frem:
            if (teller == pos-1) {
                elementSomFjernes = tmp.neste;
                tmp.neste = tmp.neste.neste;
                return elementSomFjernes.data;
            }
            teller ++;
            tmp = tmp.neste;
        }
    }

    /**
     * Fjerner det forste elementet i listen.
     * @return
     *  Det forste elementet i listen.
     */
    public T fjern() {
        if (start == null) {
            throw new UgyldigListeIndeks(-1);
        }
        Node<T> tmp = start;
        start = start.neste;
        return tmp.data;
    }

    public Iterator<T> iterator() {
        return new ListeIterator<T>();

    }

    private class ListeIterator<T> implements Iterator<T> {
        private int gjeldendeIndeks = 0;

        public T next() {
            return null;
        }

        public boolean hasNext() {
            return true;
        }

        public void remove() {
        }
    } 
}
