package view;

import model.domain.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GestoreMagazzinoView {
    static Scanner scanner = new Scanner(System.in);

    public static int showMenu() {

        int scelta;

        while (true) {

            System.out.println("===== MENU DIPARTIMENTO DI GESTIONE MAGAZZINO =====");
            System.out.println("1. Registra lotto");
            System.out.println("2. Registra ordine di rifornimento");
            System.out.println("3. Traccia giacenze");
            System.out.println("4. Report lotti in scadenza");
            System.out.println("5. Modifica lotto");
            System.out.println("6. Lista ordini di vendita");
            System.out.println("7. Marca ordine di vendita pronto");
            System.out.println("8. Esci");
            System.out.print("Seleziona un'opzione (1-8): ");


            if(scanner.hasNextInt()) {
                scelta = scanner.nextInt();
                scanner.nextLine();
                if (scelta >= 1 && scelta <= 8) {
                    break;
                } else {
                    System.out.println("Scelta non valida. Riprova.");
                }
            } else {
                scanner.next();
                System.out.println("Scelta non valida. Riprova.");
            }
        }

        return scelta;

    }

    public static Lotto registrazioneLotto() {
        System.out.println("===== REGISTRAZIONE NUOVO LOTTO =====");

        System.out.print("Numero lotto: ");
        String numero = scanner.nextLine().trim();

        Date scadenza = null;
        while (scadenza == null) {
            System.out.print("Data scadenza (YYYY-MM-DD): ");
            String dataInput = scanner.nextLine().trim();
            try {
                scadenza = Date.valueOf(dataInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato data non valido! Usare YYYY-MM-DD");
            }
        }

        System.out.print("Peso (kg): ");
        int peso = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                peso = scanner.nextInt();
                scanner.nextLine();
                break;
            }
        }

        System.out.print("Codice prodotto: ");
        String prodotto = scanner.nextLine().trim();

        return new Lotto(numero, scadenza, peso, prodotto);
    }

    public static OrdineDiRifornimento registrazioneOrdineRifornimento() {
        System.out.println("===== REGISTRAZIONE ORDINE DI RIFORNIMENTO =====");

        System.out.print("Codice fornitore: ");
        String fornitore = scanner.nextLine().trim();

        OrdineDiRifornimento ordine = new OrdineDiRifornimento(fornitore);

        // Inserimento prodotti
        System.out.println("\n--- Inserimento prodotti ---");

        do {
            System.out.print("Codice prodotto: ");
            String codiceProdotto = scanner.nextLine().trim();

            int quantita = 0;
            while (true) {
                System.out.print("Quantità: ");
                if (scanner.hasNextInt()) {
                    quantita = scanner.nextInt();
                    scanner.nextLine();
                    if (quantita > 0) {
                        break;
                    } else {
                        System.out.println("La quantità deve essere maggiore di 0!");
                    }
                } else {
                    System.out.println("Inserisci un numero valido!");
                    scanner.nextLine();
                }
            }

            VoceOrdine voceOrdine = new VoceOrdine(codiceProdotto, quantita);
            ordine.addProdotto(voceOrdine);

            System.out.print("Vuoi inserire un altro prodotto? (s/n): ");
            String risposta = scanner.nextLine().trim().toLowerCase();
            if (!risposta.equals("s")) {
                break;
            }

        } while (true);

        return ordine;
    }

    public static Lotto modificaLotto() {
        System.out.println("===== MODIFICA PESO LOTTO =====");

        System.out.print("Codice lotto da modificare: ");
        String codiceLotto = scanner.nextLine().trim();

        int nuovoPeso = -1;
        while (nuovoPeso < 0) {
            System.out.print("Nuovo peso (kg): ");
            if (scanner.hasNextInt()) {
                nuovoPeso = scanner.nextInt();
                scanner.nextLine();
                if (nuovoPeso < 0) {
                    System.out.println("Il peso deve essere maggiore o uguale a 0!");
                }
            } else {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
            }
        }

        return new Lotto(codiceLotto, null, nuovoPeso, null);
    }

    public static OrdineDiVendita marcaOrdinePronto() {

        int numeroOrdine;
        while (true) {
            System.out.print("Inserisci il numero dell'ordine da marcare come pronto: ");
            if (scanner.hasNextInt()) {
                numeroOrdine = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
            }
        }

        OrdineDiVendita ordine = new OrdineDiVendita();
        ordine.setNumero(numeroOrdine);

        return ordine;
    }

    public static void stampaListaOrdini(List<OrdineDiVendita> lo){
        StringBuilder sb = new StringBuilder();
        for (OrdineDiVendita ordine : lo) {
            sb.append(ordine.toString());
        }

        System.out.println(sb.toString());
    }

    public static void stampaReport(List<VoceReportScadenze> r){
        StringBuilder sb = new StringBuilder();
        for (VoceReportScadenze v : r) {
            sb.append(v);
        }

        System.out.println(sb.toString());
    }

    public static void stampaListaGiacenze(ListaGiacenze g){
        System.out.println(g);
    }
}
