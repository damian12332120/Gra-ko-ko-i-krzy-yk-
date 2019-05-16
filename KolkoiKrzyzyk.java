package zadaniaNieobiektowe.kolkoIKrzyzyk;

import java.util.Random;
import java.util.Scanner;

public class KolkoiKrzyzyk {
    private static char[][] plansza = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};
    private static char znakPoziomy;
    private static char znakPionowy;
    private static int licznik = 0;
    private static int wyznaczenieZnaku = 0;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int wyborGry;
        boolean koniec = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Wybierz rodzaj gry:\n" +
                    " 1. Użytkownik vs Użytkownik\n" +
                    " 2. Użytkownik vs komputer\n" +
                    " 3. Zakończyć grę");

            wyborGry = scanner.nextInt();
            switch (wyborGry) {
                case 1:
                    rozruchGry2Graczy();
                    break;
                case 2:
                    wyznaczenieZnaku = 1;
                    rozruchGryGraczIKomputer();
                    break;
                default:
                    koniec = true;
            }
        } while (!koniec);
        System.out.println("Do następnego razu!");
    }

    public static void rozruchGry2Graczy() {
        wyswietlaniePlanszy();
        do {
            ruchGracza();
            if (!sprawdzenieWygranej()) {
                break;
            }
        } while (sprawdzCzyMoznaGrać());
        if (!sprawdzCzyMoznaGrać()) {
            gdySieNieDograli();
        }
    }

    public static void wyswietlaniePlanszy() {
        int licznik = 0;
        char wskaznikPoziomy = 'a';
        System.out.println("");
        System.out.println("  1  2  3");
        System.out.print("a");
        wskaznikPoziomy++;
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                System.out.print("|");
                System.out.print(plansza[i][j]);
                System.out.print("|");
                licznik++;
                if (licznik % 3 == 0 && wskaznikPoziomy != 'd') {
                    System.out.println();
                    System.out.print(wskaznikPoziomy);
                    wskaznikPoziomy++;
                }
            }
        }
    }

    public static void ruchGracza() {
        wskazPole();
        zaznaczPole();
    }

    public static void wskazPole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPodaj pole do zaznaczenia");
        String pole = scanner.nextLine();
        znakPoziomy = pole.charAt(0);
        znakPionowy = pole.charAt(1);
    }

    public static void zaznaczPole() {
        int a, b;
        if (znakPionowy == 'a') {
            a = 0;
        } else if (znakPionowy == 'b') {
            a = 1;
        } else {
            a = 2;
        }
        if (znakPoziomy == '1') {
            b = 0;
        } else if (znakPoziomy == '2') {
            b = 1;
        } else {
            b = 2;
        }
        if (plansza[a][b] != ' ') {
            System.out.println("To pole jest już zajęte");
        } else {
            plansza[a][b] = wybranieZnaku();
        }
        wyswietlaniePlanszy();
    }

    public static char wybranieZnaku() {
        char znak = 'x';
        if (wyznaczenieZnaku != 1) {
            if (licznik % 2 == 0) {
                znak = 'O';
            }
        }
        licznik++;
        return znak;
    }

    public static void rozruchGryGraczIKomputer() {
        wyswietlaniePlanszy();
        do {
            ruchGracza();
            ruchKomutera();
            if (!sprawdzenieWygranej()) {
                break;
            }
        } while (sprawdzCzyMoznaGrać());
        if (!sprawdzCzyMoznaGrać()) {
            gdySieNieDograli();
        }
    }

    public static void ruchKomutera() {
        if (sprawdzCzySrodekJestPusty()) {
            plansza[1][1] = 'O';
        } else {
            if (!sprawdzCzyMozeszWygrac()) {
                if (!sprawdzCzyTrzebaZablokowacGracza()) {
                    wstawLosowaLiczbe();
                }
            }
        }
        wyswietlaniePlanszy();
    }

    public static boolean sprawdzCzySrodekJestPusty() {
        if (plansza[1][1] == ' ') {
            return true;
        }
        return false;
    }

    public static void wstawLosowaLiczbe() {
        Random random = new Random();
        int losujPionowo;
        int losujPoziomo;
        do {
            losujPionowo = random.nextInt(2);
            losujPoziomo = random.nextInt(2);
            if (plansza[losujPionowo][losujPoziomo] == ' ') {
                plansza[losujPionowo][losujPoziomo] = 'O';
                break;
            }
        } while (plansza[losujPionowo][losujPoziomo] != 'O');
        wyswietlaniePlanszy();
    }


    private static boolean sprawdzCzyMozeszWygrac() {
        for (int i = 0; i < 3; i++) {
            if (plansza[i][0] == 'O' && plansza[i][1] == 'O' && plansza[i][2] == ' ') {
                plansza[i][2] = 'O';
                return true;
            }
            if (plansza[i][0] == 'O' && plansza[i][2] == 'O' && plansza[i][1] == ' ') {
                plansza[i][1] = 'O';
                return true;
            }
            if (plansza[i][2] == 'O' && plansza[i][1] == 'O' && plansza[i][0] == ' ') {
                plansza[i][0] = 'O';
                return true;
            }
            if (plansza[0][i] == 'O' && plansza[1][i] == 'O' && plansza[2][i] == ' ') {
                plansza[2][i] = 'O';
                return true;
            }
            if (plansza[0][i] == 'O' && plansza[2][i] == 'O' && plansza[1][i] == ' ') {
                plansza[1][i] = 'O';
                return true;
            }
            if (plansza[2][i] == 'O' && plansza[1][i] == 'O' && plansza[0][i] == ' ') {
                plansza[0][i] = 'O';
                return true;
            }
        }
        if (plansza[0][0] == 'O' && plansza[1][1] == 'O' && plansza[2][2] == ' ') {
            plansza[2][2] = 'O';
            return true;
        }
        if (plansza[0][0] == 'O' && plansza[2][2] == 'O' && plansza[1][1] == ' ') {
            plansza[1][1] = 'O';
            return true;
        }
        if (plansza[2][2] == 'O' && plansza[1][1] == 'O' && plansza[0][0] == ' ') {
            plansza[0][0] = 'O';
            return true;
        }
        if (plansza[0][2] == 'O' && plansza[1][1] == 'O' && plansza[2][0] == ' ') {
            plansza[2][0] = 'O';
            return true;
        }
        if (plansza[0][2] == 'O' && plansza[2][0] == 'O' && plansza[1][1] == ' ') {
            plansza[1][1] = 'O';
            return true;
        }
        if (plansza[2][0] == 'O' && plansza[1][1] == 'O' && plansza[0][2] == ' ') {
            plansza[0][2] = 'O';
            return true;
        }
        return false;
    }

    public static boolean sprawdzCzyTrzebaZablokowacGracza() {
        for (int i = 0; i < 3; i++) {
            if (plansza[i][0] == 'x' && plansza[i][1] == 'x' && plansza[i][2] == ' ') {
                plansza[i][2] = 'O';
                return true;
            }
            if (plansza[i][0] == 'x' && plansza[i][2] == 'x' && plansza[i][1] == ' ') {
                plansza[i][1] = 'O';
                return true;
            }
            if (plansza[i][2] == 'x' && plansza[i][1] == 'x' && plansza[i][0] == ' ') {
                plansza[i][0] = 'O';
                return true;
            }
            if (plansza[0][i] == 'x' && plansza[1][i] == 'x' && plansza[2][i] == ' ') {
                plansza[2][i] = 'O';
                return true;
            }
            if (plansza[0][i] == 'x' && plansza[2][i] == 'x' && plansza[1][i] == ' ') {
                plansza[1][i] = 'O';
                return true;
            }
            if (plansza[2][i] == 'x' && plansza[1][i] == 'x' && plansza[0][i] == ' ') {
                plansza[0][i] = 'O';
                return true;
            }
        }
        if (plansza[0][0] == 'x' && plansza[1][1] == 'x' && plansza[2][2] == ' ') {
            plansza[2][2] = 'O';
            return true;
        }
        if (plansza[0][0] == 'x' && plansza[2][2] == 'x' && plansza[1][1] == ' ') {
            plansza[1][1] = 'O';
            return true;
        }
        if (plansza[2][2] == 'x' && plansza[1][1] == 'x' && plansza[0][0] == ' ') {
            plansza[0][0] = 'O';
            return true;
        }
        if (plansza[0][2] == 'x' && plansza[1][1] == 'x' && plansza[2][0] == ' ') {
            plansza[2][0] = 'O';
            return true;
        }
        if (plansza[0][2] == 'x' && plansza[2][0] == 'x' && plansza[1][1] == ' ') {
            plansza[1][1] = 'O';
            return true;
        }
        if (plansza[2][0] == 'x' && plansza[1][1] == 'x' && plansza[0][2] == ' ') {
            plansza[0][2] = 'O';
            return true;
        }
        return false;
    }

    public static boolean sprawdzenieWygranej() {
        if ((plansza[0][0] == plansza[1][0] && plansza[1][0] == plansza[2][0] && plansza[2][0] == 'x') ||
                (plansza[0][1] == plansza[1][1] && plansza[1][1] == plansza[2][1] && plansza[2][1] == 'x') ||
                (plansza[0][2] == plansza[1][2] && plansza[1][2] == plansza[2][2] && plansza[2][2] == 'x') ||
                (plansza[0][0] == plansza[0][1] && plansza[0][1] == plansza[0][2] && plansza[0][2] == 'x') ||
                (plansza[1][0] == plansza[1][1] && plansza[1][1] == plansza[1][2] && plansza[1][2] == 'x') ||
                (plansza[2][0] == plansza[2][1] && plansza[2][1] == plansza[2][2] && plansza[2][2] == 'x') ||
                (plansza[0][0] == plansza[1][1] && plansza[1][1] == plansza[2][2] && plansza[2][2] == 'x') ||
                (plansza[0][2] == plansza[1][1] && plansza[1][1] == plansza[2][0] && plansza[2][0] == 'x')) {
            System.out.println("\n Koniec gry wygrał gracz 1 ");
            czyszczeniePlanszy();
            return false;
        } else if ((plansza[0][0] == plansza[1][0] && plansza[1][0] == plansza[2][0] && plansza[2][0] == 'O') ||
                (plansza[0][1] == plansza[1][1] && plansza[1][1] == plansza[2][1] && plansza[2][1] == 'O') ||
                (plansza[0][2] == plansza[1][2] && plansza[1][2] == plansza[2][2] && plansza[2][2] == 'O') ||
                (plansza[0][0] == plansza[0][1] && plansza[0][1] == plansza[0][2] && plansza[0][2] == 'O') ||
                (plansza[1][0] == plansza[1][1] && plansza[1][1] == plansza[1][2] && plansza[1][2] == 'O') ||
                (plansza[2][0] == plansza[2][1] && plansza[2][1] == plansza[2][2] && plansza[2][2] == 'O') ||
                (plansza[0][0] == plansza[1][1] && plansza[1][1] == plansza[2][2] && plansza[2][2] == 'O') ||
                (plansza[0][2] == plansza[1][1] && plansza[1][1] == plansza[2][0] && plansza[2][0] == 'O')) {
            System.out.println("\n Koniec gry wygrał gracz 2 ");
            czyszczeniePlanszy();
            return false;
        }
        return true;
    }

    public static void czyszczeniePlanszy() {
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                plansza[i][j] = ' ';
            }
        }
        licznik = 0;
        System.out.println();
    }

    public static boolean sprawdzCzyMoznaGrać() {
        int dodawajGdyJestZajete = 0;
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                if (plansza[i][j] == 'x' || plansza[i][j] == 'O') {
                    dodawajGdyJestZajete++;
                }
            }
        }
        if (dodawajGdyJestZajete == 9) {
            return false;
        }
        return true;
    }

    public static void gdySieNieDograli() {
        czyszczeniePlanszy();
        licznik = 0;
        System.out.println("\nGra nie została dograna");
    }



}
