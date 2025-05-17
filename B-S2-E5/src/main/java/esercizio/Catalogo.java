package esercizio;

import java.time.Year;
import java.util.Objects;

public abstract class Catalogo {
    private String cosiceISBN;
    private String titolo;
    private Integer annoPubblicazione;
    private Integer numeroPagine;

    public Catalogo(String cosiceISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine) {
        this.cosiceISBN = cosiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String getCosiceISBN() {
        return cosiceISBN;
    }

    public void setCosiceISBN(String cosiceISBN) {
        this.cosiceISBN = cosiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "\nCatalogo{" +
                "cosiceISBN=" + cosiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalogo)) return false;
        Catalogo that = (Catalogo) o;
        return Objects.equals(cosiceISBN, that.cosiceISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cosiceISBN);
    }
}
