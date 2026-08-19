package view;

import model.domain.*;

import java.util.List;
import java.util.Scanner;

public class SegreteriaView {

    static Scanner scanner = new Scanner(System.in);

    public static int showMenu() {

        int scelta;

        while (true) {

            System.out.println("===== MENU SEGRETERIA =====");
            System.out.println("1. Registra cliente");
            System.out.println("2. Registra prodotto");
            System.out.println("3. Registra ordine di vendita");
            System.out.println("4. Registra fornitore");
            System.out.println("5. Lista ordini di vendita");
            System.out.println("6. Esci");
            System.out.print("Seleziona un'opzione (1-5): ");


            if(scanner.hasNextInt()) {
                scelta = scanner.nextInt();
                scanner.nextLine();
                if (scelta >= 1 && scelta <= 5) {
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

    public static Prodotto registrazioneProdotto() {
        System.out.println("===== REGISTRAZIONE PRODOTTO =====");

        String codice;
        String nome;
        Categoria categoria;
        float prezzoKg;

        System.out.print("Inserisci codice prodotto: ");
        codice = scanner.nextLine().trim();

        System.out.print("Inserisci nome prodotto: ");
        nome = scanner.nextLine().trim();

        while (true) { // Validazione categoria
            System.out.println("Scegli categoria: ");
            System.out.println("1. Frutta");
            System.out.println("2. Verdura");
            System.out.print("Seleziona un'opzione (1-2): ");
            int scelta;
            if(scanner.hasNextInt()) {
                scelta = scanner.nextInt();
                scanner.nextLine();
                if (scelta<1 || scelta>2) {
                    System.out.println("Scelta non valida. Riprova.");
                } else {
                    if(scelta == 1) {
                        categoria = Categoria.FRUTTA;
                    } else {
                        categoria = Categoria.VERDURA;
                    }
                    break;
                }
            } else {
                scanner.next();
                System.out.println("Scelta non valida. Riprova.");
            }
        }

        while (true) {
            System.out.print("Inserisci prezzo al kg: ");
            if (scanner.hasNextFloat()) {
                prezzoKg = scanner.nextFloat();
                scanner.nextLine();
                if (prezzoKg >= 0) {
                    break;
                } else {
                    System.out.println("Il prezzo non può essere negativo. Riprova.");
                }
            } else {
                System.out.println("Valore non valido. Inserisci un numero.");
                scanner.next();
            }
        }

        return new Prodotto(codice, nome, categoria, prezzoKg, 0); //quando un prodotto viene inserito la disponibilità è settata a 0
    }

    public static Fornitore registrazioneFornitore() {

        System.out.println("===== REGISTRAZIONE FORNITORE =====");

        System.out.print("Inserisci codice fornitore: ");
        String codice = scanner.nextLine().trim();

        System.out.print("Inserisci codice fiscale: ");
        String cf = scanner.nextLine().trim();

        System.out.print("Inserisci nome fornitore: ");
        String nome = scanner.nextLine().trim();

        Fornitore newFornitore = new Fornitore(codice, cf, nome);

        // Inserimento delle sedi
        System.out.println("Inserisci almeno un indirizzo di sede:");

        do {

            Indirizzo sede = inserisciIndirizzo();
            newFornitore.addSede(sede);

            System.out.print("Vuoi inserire un'altra sede? (s/n): ");
            String risposta = scanner.nextLine().trim().toLowerCase();
            if (!risposta.equals("s")) {
                break;
            }

        } while (true);

        // Inserimento prodotti forniti (opzionale in questa fase)
        System.out.println("--- Inserimento prodotti forniti ---");

        while (true) {
            System.out.print("Inserisci codice prodotto (oppure premi INVIO per terminare): ");
            String codiceProdotto = scanner.nextLine().trim();

            if (codiceProdotto.isEmpty()) {
                break;
            }
            newFornitore.addProdFornito(codiceProdotto);
        }

        return newFornitore;
    }


    public static Cliente registrazioneCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== REGISTRAZIONE CLIENTE =====");

        System.out.print("Inserisci partita IVA: ");
        String partitaIva = scanner.nextLine().trim();

        System.out.print("Inserisci nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Inserisci cognome: ");
        String cognome = scanner.nextLine().trim();

        // Indirizzo di residenza
        System.out.println("\nInserisci indirizzo di residenza:");
        Indirizzo indirizzoResidenza = inserisciIndirizzo();

        // Indirizzo di fatturazione
        System.out.println("\nInserisci indirizzo di fatturazione:");

        Indirizzo indirizzoFatturazione = inserisciIndirizzo();


        // Creazione cliente
        Cliente nuovoCliente = new Cliente(partitaIva, nome, cognome, indirizzoResidenza, indirizzoFatturazione);

        // Inserimento contatti
        System.out.println("Inserisci almeno un contatto:");

        do {
            Contatto contatto = inserisciContatto();
            nuovoCliente.addContatto(contatto);

            System.out.print("Vuoi inserire un altro contatto? (s/n): ");
            String risposta = scanner.nextLine().trim().toLowerCase();
            if (!risposta.equals("s")) {
                break;
            }

        } while (true);

        return nuovoCliente;
    }

    public static OrdineDiVendita registrazioneOrdineVendita() {
        System.out.println("===== REGISTRAZIONE ORDINE DI VENDITA =====");

        System.out.print("Partita IVA cliente: ");
        String cliente = scanner.nextLine().trim();

        System.out.println("\n---Inserisci indirizzo di spedizione---");
        Indirizzo indirizzoSpedizione = inserisciIndirizzo();

        System.out.println("\n---Inserisci contatto---");
        Contatto contattoRecapito = inserisciContatto();

        OrdineDiVendita ordine = new OrdineDiVendita(contattoRecapito, cliente, indirizzoSpedizione);

        System.out.println("\n---Inserisci prodotti---");

        do {
            System.out.print("Codice prodotto: ");
            String codiceProdotto = scanner.nextLine().trim();

            System.out.print("Quantità: ");
            int quantita = Integer.parseInt(scanner.nextLine().trim());

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

    public static void stampaListaOrdini(List<OrdineDiVendita> lo){
        StringBuilder sb = new StringBuilder();
        for (OrdineDiVendita ordine : lo) {
            sb.append(ordine.toString());
        }

        System.out.println(sb.toString());
    }

    private static Indirizzo inserisciIndirizzo() {
        System.out.print("Via: ");
        String via = scanner.nextLine().trim();

        System.out.print("Civico: ");
        String civico = scanner.nextLine().trim();

        System.out.print("CAP: ");
        String cap = scanner.nextLine().trim();

        System.out.print("Città: ");
        String citta = scanner.nextLine().trim();

        System.out.print("Provincia: ");
        String provincia = scanner.nextLine().trim();

        return new Indirizzo(via, civico, cap, citta, provincia);
    }

    private static Contatto inserisciContatto() {
        // Selezione tipo contatto
        TipoContatto tipo = null;
        while (tipo == null) {
            System.out.println("Seleziona tipo contatto:");
            System.out.println("1 - Cellulare");
            System.out.println("2 - Telefono");
            System.out.println("3 - Email");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine().trim();

            switch (scelta) {
                case "1":
                    tipo = TipoContatto.CELLULARE;
                    break;
                case "2":
                    tipo = TipoContatto.TELEFONO;
                    break;
                case "3":
                    tipo = TipoContatto.EMAIL;
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }

        // Inserimento valore
        System.out.print("Valore contatto: ");
        String valore = scanner.nextLine().trim();

        return new Contatto(valore, tipo);
    }


}

