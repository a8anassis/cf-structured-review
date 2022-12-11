package testbed.review;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MobileApp2 {
    static Scanner in = new Scanner(System.in);
    static Path path = Paths.get("C:/tmp/logMobile.txt");
    static String[][] contacts = new String[100][3];
    static int pivot = -1;

    public static void main(String[] args) {
        boolean quit = false;
        String s = "";

        do {
            printGenericMenu();
            s = getChoice();
            if (s.matches("[qQ]")) quit = true;
            else {
                try {
                    handleChoice(s);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!quit);

        System.out.println("Bios");
    }

    public static boolean isValid(int choice) {
        return ((choice >= 1) && (choice <= 5));
    }

    public static void handleChoice(String s) {
        int choice = 0;
        String phoneNumber = "";

        try {
            choice = Integer.parseInt(s);
            if (!isValid(choice)) {
                throw new IllegalArgumentException("Error - Choice between 1-5");
            }

            switch (choice) {
                case 1:
                    printContactMenu();
                    try {
                        insertContactService(getFirstname(), getLastname(), getPhoneNumber());
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                    break;
                case 2:
                    phoneNumber = getPhoneNumber();
                    try {
                        deleteContactService(phoneNumber);
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                    break;
                case 3:
                    phoneNumber = getPhoneNumber();
                    printContactMenu();
                    try {
                        updateContactService(phoneNumber, getFirstname(), getLastname(), getPhoneNumber());
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                    break;
                case 4:
                    phoneNumber = getPhoneNumber();
                    try {
                        getAndPrintContactService(phoneNumber);
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                    break;
                case 5:
                    try {
                        printContactsService();
                    } catch (IllegalArgumentException e) {
                        throw e;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Choice");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void getAndPrintContactService(String phoneNumber) {
        try {
            String[] contact = getContactByPhoneNumber(phoneNumber);
            if (contact.length == 0) {
                throw new IllegalArgumentException("Contact not found");
            } else {
                printContact(contact);
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void insertContactService(String firstname, String lastname, String phoneNumber) {
        try {
            if (insertContact(firstname, lastname, phoneNumber)) {
                System.out.println("Successfully inserted");
            } else {
                throw new IllegalArgumentException("Error in insert");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void updateContactService(String oldPhoneNumber, String firstname, String lastname, String phoneNumber) {
        try {
            if (updateContact(oldPhoneNumber, firstname, lastname, phoneNumber)) {
                System.out.println("Successfully updated");
            } else {
                throw new IllegalArgumentException("Update error");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void deleteContactService(String phoneNumber) {
        try {
            if (deleteContact(phoneNumber)) {
                System.out.println("Successfully deleted");
            } else {
                throw new IllegalArgumentException("Error in delete");
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void printContactsService() {
        try {
            if (pivot == -1) throw new IllegalArgumentException("List is empty");
            for (int i = 0; i <= pivot; i ++) {
                System.out.printf("%s\t%s\t%s\n", contacts[i][0],contacts[i][1], contacts[i][2]);
            }
        } catch (IllegalArgumentException e) {
            log(e);
            throw e;
        }
    }

    public static void printContact(String[] contact) {
        for (String item : contact) {
            System.out.print(item + " ");
        }

        System.out.println();
    }


    public static void printGenericMenu() {
        System.out.println("Επιλέξτε ένα από τα παρακάτω: ");
        System.out.println("1. Εισαγωγή Επαφής");
        System.out.println("2. Διαγραφή Επαφής");
        System.out.println("3. Ενημέρωση Επαφής");
        System.out.println("4. Αναζήτηση Επαφής");
        System.out.println("5. Εκτυπώστε τις επαφές");
        System.out.println("Q. Έξοδος");
    }

    public static void printContactMenu() {
        System.out.println("Εισάγετε Όνομα, Επώνυμο και Τηλέφωνο");
    }

    public static String getChoice() {
        System.out.println("Εισάγετε επιλογή");
        return in.nextLine().trim();
    }

    public static String getFirstname() {
        System.out.println("Εισάγετε Όνομα");
        return in.nextLine().trim();
    }

    public static String getLastname() {
        System.out.println("Εισάγετε Επώνυμο");
        return in.nextLine().trim();
    }

    public static String getPhoneNumber() {
        System.out.println("Εισάγετε Αριθμό Τηλεφώνου");
        return in.nextLine().trim();
    }


    // CRUD API

    public static int getContactIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i <= pivot; i++) {
            if (contacts[i][2].equals(phoneNumber)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean insertContact(String firstname, String lastname, String phoneNumber) {
        if (pivot == contacts.length) throw new IllegalArgumentException("Contacts list full");
        boolean inserted = false;

        if (getContactIndexByPhoneNumber(phoneNumber) == -1) {
            pivot++;
            contacts[pivot][0] = firstname;
            contacts[pivot][1] = lastname;
            contacts[pivot][2] = phoneNumber;
            inserted = true;
        }

        return inserted;
    }

    public static boolean updateContact(String oldPhoneNumber, String firstname, String lastname, String newPhoneNumber) {
        int positionToUpdate = getContactIndexByPhoneNumber(oldPhoneNumber);
        boolean updated = false;

        if (positionToUpdate != -1) {
            contacts[positionToUpdate][0] = firstname;
            contacts[positionToUpdate][1] = lastname;
            contacts[positionToUpdate][2] = newPhoneNumber;
            updated = true;
        }

        return updated;
    }

    public static boolean deleteContact(String phoneNumber) {
        int positionToDelete = getContactIndexByPhoneNumber(phoneNumber);
        boolean deleted = false;

        if (positionToDelete != -1) {
            System.arraycopy(contacts, positionToDelete + 1, contacts, positionToDelete, pivot - (positionToDelete - 1));
            pivot--;
            deleted = true;
        }

        return deleted;
    }

    public static String[] getContactByPhoneNumber(String phoneNumber) {
        int positionToReturn = getContactIndexByPhoneNumber(phoneNumber);

        if (positionToReturn == -1) {
            return new String[] {};
        } else {
            return contacts[getContactIndexByPhoneNumber(phoneNumber)];
        }
    }

    public static void log(Exception e) {
        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
