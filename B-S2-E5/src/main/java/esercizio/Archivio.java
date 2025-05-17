package esercizio;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio{

    //Aggiunta di un elemento(non duplicato ISBN)
    Set<Catalogo> catalogo = new HashSet<>();
    public void aggiungiElemento(Catalogo elemento) {
        boolean aggiunto = catalogo.add(elemento);
        if (aggiunto) {
            System.out.println("Elemento aggiunto: " + elemento);
        }
    }


    public Set<Catalogo> getCatalogo() {
        return catalogo;
    }

    //Ricerca per ISBN con lancio eccezione custom
    public Catalogo ricercaPerIsbn(String isbn) throws ElementoNonTrovato {
        return catalogo.stream()
                .filter(e -> e.getCosiceISBN().equalsIgnoreCase(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovato("Elemento con ISBN " + isbn + " non trovato."));
    }

    //Rimozione di un elemento dato un codice ISBN
    public void rimuoviElementoPerIsbn(String isbn) {

            boolean rimuovi = catalogo.removeIf(e -> e.getCosiceISBN().equals(isbn));
            if (rimuovi) {
                System.out.println("Elemento con ISBN " + isbn + " rimosso correttamente.");
            }else {
                throw new IllegalArgumentException("Nessun elemento trovato con questo ISBN");
            }

    }

    //Ricerca per anno pubblicazione
    public List<Catalogo> elementiPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    //Ricerca per autore
    public List<Libri> libriPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libri)
                .map(e -> (Libri) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    //Aggiornamento di un elemento esistente dato l'ISBN
    public boolean aggiornaElemento(String codiceISBN, String nuovoTitolo, int nuovoAnno, int nuovePagine,
                                    String nuovoAutore, String nuovoGenere, String nuovaPeriodicita) {
        boolean trovato = catalogo.stream()
                .filter(e -> e.getCosiceISBN().equals(codiceISBN))
                .peek(e -> {
                    if (e instanceof Libri) {
                        Libri libro = (Libri) e;
                        if (nuovoTitolo != null) libro.setTitolo(nuovoTitolo);
                        if (nuovoAnno > 0) libro.setAnnoPubblicazione(nuovoAnno);
                        if (nuovePagine > 0) libro.setNumeroPagine(nuovePagine);
                        if (nuovoAutore != null) libro.setAutore(nuovoAutore);
                        if (nuovoGenere != null) libro.setGenere(nuovoGenere);
                    } else if (e instanceof Riviste) {
                        Riviste rivista = (Riviste) e;
                        if (nuovoTitolo != null) rivista.setTitolo(nuovoTitolo);
                        if (nuovoAnno > 0) rivista.setAnnoPubblicazione(nuovoAnno);
                        if (nuovePagine > 0) rivista.setNumeroPagine(nuovePagine);
                        if (nuovaPeriodicita != null && !nuovaPeriodicita.isBlank()) {
                            rivista.setPeriodicita(Periodicita.valueOf(nuovaPeriodicita.toUpperCase()));
                        }
                    }
                })
                .count() > 0;

        return trovato;
    }

    //Stampa statistiche catalogo: n.totale libri, n.totale riviste, elemento con più pagine, media pagine elementi.
    public static void stampaStatistiche(Set<Catalogo> catalogo) {
        if (catalogo == null || catalogo.isEmpty()) {
            System.out.println("Il catalogo è vuoto");
            return;
        }

        long numeroLibri = catalogo.stream()
                .filter(e -> e instanceof Libri)
                .count();

        long numeroRiviste = catalogo.stream()
                .filter(e -> e instanceof Riviste)
                .count();

        Catalogo elementoConPiuPagine = catalogo.stream()
                .max(Comparator.comparingInt(Catalogo::getNumeroPagine))
                .orElse(null);

        double mediaPagine = catalogo.stream()
                .mapToInt(Catalogo::getNumeroPagine)
                .average()
                .orElse(0);


        System.out.println("\nSTATISTICHE CATALOGO:");
        System.out.println("Totale libri presenti: " + numeroLibri);
        System.out.println("Totale riviste presenti: " + numeroRiviste);
        System.out.println("Media pagine: "+ mediaPagine);

        if (elementoConPiuPagine != null) {
            System.out.println("Elemento con più pagine: " + elementoConPiuPagine.getTitolo() +
                    " con " + elementoConPiuPagine.getNumeroPagine() + " pagine");
        }
    }


}
