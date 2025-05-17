package esercizio;

public class Riviste extends Catalogo{
    private Periodicita periodicita;

    public Riviste(String cosiceISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine, Periodicita periodicita) {
        super(cosiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "codiceISBN='" + getCosiceISBN() + '\'' +
                "titolo='" + getTitolo() + '\'' +
                "anno pubblicazione='" + getAnnoPubblicazione() + '\'' +
                "numero pagine='" + getNumeroPagine() + '\'' +
                "periodicita=" + periodicita +
                '}';
    }


}
