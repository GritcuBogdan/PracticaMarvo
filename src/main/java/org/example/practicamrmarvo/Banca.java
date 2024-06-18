//package org.example.practicamrmarvo;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Banca {
//    public static void main(String[] args) {
//        List<Client> clienti = new ArrayList<>();
//        List<Depozit> depozite = new ArrayList<>();
//        List<Contract> contracte = new ArrayList<>();
//        List<Filiala> filiale = new ArrayList<>();
//
//        // Adăugare exemple de date
//        Filiala filiala1 = new Filiala("Filiala Centrala", "Strada Principala nr. 1", "0123456789");
//        filiale.add(filiala1);
//
//        ClientFizic clientFizic1 = new ClientFizic("Ion Popescu", "1234567890123", "Strada Lalelelor nr. 2", "0987654321", 1);
//        clienti.add(clientFizic1);
//
//        ClientJuridic clientJuridic1 = new ClientJuridic("RO123456", "SC Firma SRL", "Privat", "Strada Florilor nr. 3", "0712345678", "Andrei Ionescu", "Maria Popa");
//        clienti.add(clientJuridic1);
//
//        Depozit depozit1 = new Depozit("Economii", "Depozit Standard", 3.5, "RON");
//        depozite.add(depozit1);
//
//
//        // Meniu minimalizat
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//
//        while (!exit) {
//            System.out.println("Meniu:");
//            System.out.println("1. Adauga filiala");
//            System.out.println("2. Adauga client fizic");
//            System.out.println("3. Adauga client juridic");
//            System.out.println("4. Adauga depozit");
//            System.out.println("5. Adauga contract");
//            System.out.println("6. Afiseaza filiale");
//            System.out.println("7. Afiseaza clienti");
//            System.out.println("8. Afiseaza depozite");
//            System.out.println("9. Afiseaza contracte");
//            System.out.println("10. Calculeaza venit din contracte");
//            System.out.println("11. Iesire");
//            System.out.print("Alege o optiune: ");
//            int optiune = scanner.nextInt();
//
//            switch (optiune) {
//                case 1:
//                    // Adauga filiala
//                    scanner.nextLine(); // consume newline
//                    System.out.print("Nume: ");
//                    String numeFiliala = scanner.nextLine();
//                    System.out.print("Adresa: ");
//                    String adresaFiliala = scanner.nextLine();
//                    System.out.print("Telefon: ");
//                    String telefonFiliala = scanner.nextLine();
//                    filiale.add(new Filiala(numeFiliala, adresaFiliala, telefonFiliala));
//                    break;
//
//                case 2:
//                    // Adauga client fizic
//                    scanner.nextLine(); // consume newline
//                    System.out.print("Nume: ");
//                    String numeClientFizic = scanner.nextLine();
//                    System.out.print("Cod Personal: ");
//                    String codPersonalClientFizic = scanner.nextLine();
//                    System.out.print("Adresa: ");
//                    String adresaClientFizic = scanner.nextLine();
//                    System.out.print("Telefon: ");
//                    String telefonClientFizic = scanner.nextLine();
//                    System.out.print("Numar Depozite: ");
//                    int numarDepoziteClientFizic = scanner.nextInt();
//                    clienti.add(new ClientFizic(numeClientFizic, codPersonalClientFizic, adresaClientFizic, telefonClientFizic, numarDepoziteClientFizic));
//                    break;
//
//                case 3:
//                    // Adauga client juridic
//                    scanner.nextLine(); // consume newline
//                    System.out.print("Cod Fiscal: ");
//                    String codFiscalClientJuridic = scanner.nextLine();
//                    System.out.print("Denumire: ");
//                    String denumireClientJuridic = scanner.nextLine();
//                    System.out.print("Tip Proprietate: ");
//                    String tipProprietateClientJuridic = scanner.nextLine();
//                    System.out.print("Adresa: ");
//                    String adresaClientJuridic = scanner.nextLine();
//                    System.out.print("Telefon: ");
//                    String telefonClientJuridic = scanner.nextLine();
//                    System.out.print("Nume Administrator: ");
//                    String numeAdministratorClientJuridic = scanner.nextLine();
//                    System.out.print("Persoana Contact: ");
//                    String persoanaContactClientJuridic = scanner.nextLine();
//                    clienti.add(new ClientJuridic(codFiscalClientJuridic, denumireClientJuridic, tipProprietateClientJuridic, adresaClientJuridic, telefonClientJuridic, numeAdministratorClientJuridic, persoanaContactClientJuridic));
//                    break;
//
//                case 4:
//                    // Adauga depozit
//                    scanner.nextLine(); // consume newline
//                    System.out.print("Tip: ");
//                    String tipDepozit = scanner.nextLine();
//                    System.out.print("Denumire: ");
//                    String denumireDepozit = scanner.nextLine();
//                    System.out.print("Procent Anual: ");
//                    double procentAnualDepozit = scanner.nextDouble();
//                    scanner.nextLine(); // consume newline
//                    System.out.print("Valuta: ");
//                    String valutaDepozit = scanner.nextLine();
//                    depozite.add(new Depozit(tipDepozit, denumireDepozit, procentAnualDepozit, valutaDepozit));
//                    break;
//
//                case 5:
//                    // Adauga contract
//                    scanner.nextLine(); // consume newline
//                    System.out.print("Numar Contract: ");
//                    String numarContract = scanner.nextLine();
//                    System.out.print("Data (YYYY-MM-DD): ");
//                    LocalDate dataContract = LocalDate.parse(scanner.nextLine());
//                    System.out.println("Selecteaza client (index): ");
//                    for (int i = 0; i < clienti.size(); i++) {
//                        System.out.println(i + ". " + clienti.get(i).getClass().getSimpleName() + ": " + (clienti.get(i) instanceof ClientFizic ? ((ClientFizic) clienti.get(i)).getNume() : ((ClientJuridic) clienti.get(i)).getDenumire()));
//                    }
//                    int clientIndex = scanner.nextInt();
//                    Client client = clienti.get(clientIndex);
//                    System.out.println("Selecteaza depozit (index): ");
//                    for (int i = 0; i < depozite.size(); i++) {
//                        System.out.println(i + ". " + depozite.get(i).getDenumire());
//                    }
//                    int depozitIndex = scanner.nextInt();
//                    Depozit depozit = depozite.get(depozitIndex);
//                    System.out.print("Suma Totala: ");
//                    double sumaTotala = scanner.nextDouble();
//                    System.out.print("Termen Pastrare (luni): ");
//                    int termenPastrare = scanner.nextInt();
//                    System.out.print("Procent Dobanda: ");
//                    double procentDobanda = scanner.nextDouble();
//                    contracte.add(new Contract(numarContract, dataContract, client, depozit, sumaTotala, termenPastrare, procentDobanda));
//                    break;
//
//                case 6:
//                    // Afiseaza filiale
//                    for (Filiala filiala : filiale) {
//                        System.out.println(filiala);
//                    }
//                    break;
//
//                case 7:
//                    // Afiseaza clienti
//                    for (Client c : clienti) {
//                        c.afisareDetalii();
//                    }
//                    break;
//
//                case 8:
//                    // Afiseaza depozite
//                    for (Depozit depozitAfisare : depozite) {
//                        System.out.println(depozitAfisare);
//                    }
//                    break;
//
//                case 9:
//                    // Afiseaza contracte
//                    for (Contract contract : contracte) {
//                        System.out.println(contract);
//                    }
//                    break;
//
//                case 10:
//                    // Calculeaza venit din contracte
//                    double venitTotal = 0;
//                    for (Contract contract : contracte) {
//                        venitTotal += contract.calculeazaVenit();
//                    }
//                    System.out.println("Venit Total: " + venitTotal);
//                    break;
//
//                case 11:
//                    exit = true;
//                    break;
//
//                default:
//                    System.out.println("Optiune invalida. Te rog sa incerci din nou.");
//            }
//        }
//
//        scanner.close();
//    }
//}