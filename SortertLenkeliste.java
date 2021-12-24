class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern() {
        Node<T> tmp = start;
        T returneres;

        if (start == null) {
            throw new UgyldigListeIndeks(-1);
        }
        if (start.neste == null) {
            returneres = start.data;
            start = null;
            return returneres;
        }

        // Lager en returneres slik at man kan returnere data, siden vi mister 
        // referanse til siste/storste objektet.
        while (true) {
            if (tmp.neste.neste == null) {
                returneres = tmp.neste.data;
                tmp.neste = null;
                return returneres;
            } 
            tmp = tmp.neste;
        }
    }

    public void leggTil(T x) {
        Node<T> tmp = start;
        Node<T> nynode = new Node<T>(x);

        if (x == null) {
            throw new NullPointerException();
        }

        // Hvis listen er tom:
        if (start == null) {
            start = nynode;
            return;
        }
        
        // Hvis x skal forst i listen, må vi oppdatere start.
        if (nynode.data.compareTo(start.data) < 0) {
            nynode.neste = start;
            start = nynode;
            return;
        }

        while (true) {
            // Hvis vi har kommet til slutten, legge til til slutt.
            if (tmp.neste == null) {
                tmp.neste = nynode;
                return;
            }
            // Hvis mindre enn null får vi et negativt tall.
            // Det vil si at x er mindre enn neste Node sin x.
            if (nynode.data.compareTo(tmp.neste.data) < 0) {
                nynode.neste = tmp.neste;
                tmp.neste = nynode;
                return;
            }
            tmp = tmp.neste;
        }
    }
}

// TODO: Slett dette for innlevering
// class Bil implements Comparable<Bil>{
//     int finhet;
//     String s;
//     public int compareTo(Bil o) {
//         if (this.finhet > o.finhet) {
//             return 1;
//         }
//         if (this.finhet == o.finhet) {
//             return 0;
//         }
//         if (this.finhet < o.finhet) {
//             return -1;            
//         }
//         throw new Exception("Dette skal da ikke skje!");
//     }
// }

// class Katt implements Comparable<Bil> {

// }