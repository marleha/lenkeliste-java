class Stabel<T> extends Lenkeliste<T> {

    public void leggPaa(T x) {
        leggTil(x);
    }

    public T taAv() {
        System.out.println("Str:" + stoerrelse());
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

        while (true) {
            // Naar paa slutten av listen.
            if (tmp.neste.neste == null) {
                returneres = tmp.neste.data;
                tmp.neste = null;
                return returneres;
            }
            tmp = tmp.neste;
        }
    }
}