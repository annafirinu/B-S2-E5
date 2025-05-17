package esercizio;

import java.util.List;
import java.util.Scanner;

public class Prova {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archivio archivio = new Archivio();

        System.out.println("Catalogo bibliotecario");

        while (true) {
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1 - Aggiungi Libro");
            System.out.println("2 - Aggiungi Rivista");
            System.out.println("3 - Ricerca per ISBN");
            System.out.println("4 - Rimuovi elemento tramite codice ISBN");
            System.out.println("5 - Ricerca elemento per anno pubblicazione");
            System.out.println("6 - Ricerca elemento per autore");
            System.out.println("7 - Aggiorna elemento dato un codice ISBN");
            System.out.println("8 - Statistiche catalogo");
            System.out.println("9 - Mostra Catalogo");
            System.out.println("0 - Esci");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> {
                    try {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(scanner.nextLine());

                        System.out.print("Numero pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());

                        System.out.print("Autore: ");
                        String autore = scanner.nextLine();

                        System.out.print("Genere: ");
                        String genere = scanner.nextLine();

                        Libri libro = new Libri(isbn, titolo, anno, pagine, autore, genere);
                        archivio.aggiungiElemento(libro);
                    }catch (NumberFormatException e) {
                        System.out.println("Errore: in questo campo è necessario inserire solo numeri.");
                    }catch (IllegalArgumentException e) {
                        System.out.println("Elemento con ISBN già presente");
                    }
                }

                case "2" -> {
                    try {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(scanner.nextLine());

                        System.out.print("Numero pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());

                        System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        String inputPeriodicita = scanner.nextLine().toUpperCase();
                        Periodicita periodicita = Periodicita.valueOf(inputPeriodicita);


                        Riviste rivista = new Riviste(isbn, titolo, anno, pagine, periodicita);
                        archivio.aggiungiElemento(rivista);
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: in questo campo è necessario inserire solo numeri.");
                    }catch (IllegalArgumentException e) {
                        System.out.println("Verificare campo Periodicità, se compilato ISBN già presente");
                    }
                }

                case "3" -> {
                    System.out.print("\nInserisci ISBN da cercare: ");
                    String isbn = scanner.nextLine();

                    try {
                        Catalogo trovato = archivio.ricercaPerIsbn(isbn);
                        System.out.println("Elemento trovato:"+trovato);
                    } catch (ElementoNonTrovato e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }

                case "4" -> {
                    try {
                        System.out.print("\nInserisci il codice ISBN dell'elemento da rimuovere: ");
                        String isbn = scanner.nextLine();
                        archivio.rimuoviElementoPerIsbn(isbn);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Nessun elemento trovato con questo isbn");
                    }
                }

                case "5" -> {
                    try {
                        System.out.print("\nInserisci l'anno da cercare: ");
                        int anno = Integer.parseInt(scanner.nextLine());

                        List<Catalogo> risultati = archivio.elementiPerAnno(anno);

                        if (risultati.isEmpty()) {
                            System.out.println("Nessun elemento trovato per l'anno " + anno);
                        } else {
                            System.out.println("Elementi pubblicati nell'anno " + anno + ":");
                            risultati.forEach(System.out::println);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Errore: inserisci un numero valido per l'anno.");
                    }
                }

                case "6" -> {
                    try {
                        System.out.print("\nInserisci il nome dell'autore da cercare: ");
                        String autore = scanner.nextLine();

                        if (autore == null || autore.trim().isEmpty()) {
                            throw new IllegalArgumentException("Il nome dell'autore non può essere vuoto.");
                        }

                        List<Libri> libriTrovati = archivio.libriPerAutore(autore);

                        if (libriTrovati.isEmpty()) {
                            System.out.println("Nessun libro trovato per l'autore: " + autore);
                        } else {
                            System.out.println("Libri scritti da " + autore + ":");
                            libriTrovati.forEach(System.out::println);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }

                case "7" -> {
                    try {
                        System.out.print("\nInserisci l'ISBN dell'elemento da aggiornare: ");
                        String isbnAggiornamento = scanner.nextLine();

                        Catalogo elemento = archivio.ricercaPerIsbn(isbnAggiornamento);
                        String nuovoTitolo = null;
                        int nuovoAnno = 0;
                        int nuovePagine = 0;
                        String nuovoAutore = null;
                        String nuovoGenere = null;
                        String nuovaPeriodicita = null;

                        System.out.print("Nuovo Titolo (lascia vuoto per non modificare): ");
                        nuovoTitolo = scanner.nextLine();
                        System.out.print("Nuovo Anno di pubblicazione (0 per non modificare): ");
                        nuovoAnno = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nuovo Numero di pagine (0 per non modificare): ");
                        nuovePagine = Integer.parseInt(scanner.nextLine());
                        if (elemento instanceof Libri) {
                        System.out.print("Nuovo Autore (lascia vuoto per non modificare): ");
                        nuovoAutore = scanner.nextLine();
                        System.out.print("Nuovo Genere (lascia vuoto per non modificare): ");
                        nuovoGenere = scanner.nextLine();} else if (elemento instanceof Riviste) {

                        System.out.print("Nuova Periodicità SETTIMANALE, MENSILE, SEMESTRALE (lascia vuoto per non modificare): ");
                        nuovaPeriodicita = scanner.nextLine();}

                        boolean aggiornato = archivio.aggiornaElemento(isbnAggiornamento, nuovoTitolo, nuovoAnno, nuovePagine,
                                nuovoAutore, nuovoGenere, nuovaPeriodicita);
                        if (aggiornato) {
                            System.out.println("Elemento aggiornato con successo.");
                        } else {
                            System.out.println("Elemento con ISBN " + isbnAggiornamento + " non trovato.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "8" ->{
                    try {
                        Archivio.stampaStatistiche(archivio.getCatalogo());
                    } catch (Exception e) {
                        System.out.println("Errore nel calcolo: " + e.getMessage());
                    }
                }


                case "9" -> {
                    System.out.println("\n--- Catalogo ---");
                    archivio.getCatalogo().forEach(System.out::println);
                }

                case "0" -> {
                    System.out.println("Chiusura del programma.");
                    return;
                }

                default -> System.out.println("Scelta non valida.");
            }
        }

    }
}
