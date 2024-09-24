package org.BatiCuisine.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ValidatorUtils {
    public static final Scanner scanner = new Scanner(System.in);
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String ERROR_EMOJI = "❌";

    public static String validString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        while (input == null || input.trim().isEmpty()) {
            System.out.println("                      " + RED + ERROR_EMOJI + " L'entrée ne doit pas être vide. Veuillez entrer une valeur." + RESET);
            System.out.print(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public static int validInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value <= 0) {
                    System.out.println("                      " + RED + ERROR_EMOJI + " Veuillez entrer un nombre entier supérieur à zéro." + RESET);
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("                      " + RED + ERROR_EMOJI + " Veuillez entrer un nombre entier valide." + RESET);
                scanner.next();
            }
        }
    }

    public static double validDouble(String prompt) {
        scanner.useLocale(Locale.US);
        System.out.print(prompt);
        while (true) {
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : veuillez entrer un nombre valide." + RESET);
                scanner.next();
            }
        }
    }

    private static boolean isValidName(String input) {
        return input != null && input.matches("[a-zA-Z\\s]+");
    }

    public static String validName(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        while (!isValidName(input)) {
            System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : le nom ne doit contenir que des lettres. Veuillez réessayer." + RESET);
            System.out.print(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public static String validPN(String prompt) {
        System.out.print(prompt);
        while (true) {
            String numeroTelephone = scanner.nextLine();
            if (numeroTelephone.length() < 10) {
                System.out.println("                      " + RED + ERROR_EMOJI + " Le numéro de téléphone doit contenir au moins 10 caractères." + RESET);
                continue;
            }
            if (!numeroTelephone.matches("[0-9\\s\\-\\.\\+()]*")) {
                System.out.println("                      " + RED + ERROR_EMOJI + " Le numéro de téléphone ne doit contenir que des chiffres et des caractères spéciaux autorisés (espace, tiret, point, plus, parenthèses)." + RESET);
                continue;
            }
            return numeroTelephone;
        }
    }

    public static int validProfessionnelInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty() || input.equals("0")) {
                return 0;
            } else if (input.equals("1")) {
                return 1;
            }
            System.out.println("                      " + RED + ERROR_EMOJI + " Veuillez entrer 0 ou 1." + RESET);
        }
    }

    public static LocalDate validDate(String prompt) {
        System.out.print(prompt);
        while (true) {
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : la date ne peut pas être dans le passé. Veuillez entrer une date future (format : jj/mm/aaaa) : " + RESET);
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : format de date invalide. Veuillez réessayer (format : jj/mm/aaaa) : " + RESET);
            }
        }
    }

    public static boolean validBoolean(String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("oui")) {
                return true;
            } else if (input.equals("non")) {
                return false;
            }
            System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : entrée invalide. Veuillez réessayer." + RESET);
        }
    }

    public static double validNegative(String prompt) {
        System.out.print(prompt);
        double tva = -1;
        while (tva <= 0) {
            if (scanner.hasNextDouble()) {
                tva = scanner.nextDouble();
                if (tva <= 0) {
                    System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : le taux de TVA doit être supérieur à 0." + RESET);
                }
            } else {
                System.out.println("                      " + RED + ERROR_EMOJI + " Erreur : veuillez entrer un nombre valide." + RESET);
                scanner.next();
            }
        }
        return tva;
    }
}
