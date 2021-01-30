package contacts;

import contacts.factory.ContactFactory;
import contacts.products.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    PhoneBook phoneBook = new PhoneBook();
    boolean isAppTurningOn = true;
    boolean isSearchMenuIsTurningOn = false;
    boolean isListMenuIsTurningOn = false;
    ContactFactory contactFactory = new ContactFactory();
    List<Contact> searchedItems = new ArrayList<>();

    public void launchMainMenu() throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get("contactsfile.txt"))) {
            phoneBook.contacts = (List<Contact>) SerializationUtils.deserialize("contactsfile.txt");
        }
        while (isAppTurningOn) {
            printLineOfMainMenu();
            String userChoice = CollectorDataFromUser.getTextFromUser();
            while (!isDataFromUserNotValidatedMainMenu(userChoice)) {
                printLineOfMainMenu();
                userChoice = CollectorDataFromUser.getTextFromUser();
            }
            switch (userChoice) {
                case "add":
                    phoneBook.addContact(contactFactory.prepareContact());
                    break;
                case "list":
                    phoneBook.printContacts();
                    launchListMenu();
                    break;
                case "search":
                    launchSearchMenu();
                    break;
                case "count":
                    phoneBook.printNumberOfContacts();
                    break;
                case "exit":
                     SerializationUtils.serialize(phoneBook.contacts, "contactsfile.txt");
                    isAppTurningOn = false;
                    break;
            }
        }
    }

    public boolean isDataFromUserNotValidatedMainMenu(String inputData) {
        return inputData.matches("add|list|search|count|exit");
    }

    public void printLineOfMainMenu() {
        System.out.print("[menu] Enter action (add, list, search, count, exit): ");
    }

    public void launchListMenu() {
        isListMenuIsTurningOn = true;
        while (isListMenuIsTurningOn) {
            printLineOfListMenu();
            String userChoice = CollectorDataFromUser.getTextFromUser();
            while (!isDataFromUserNotValidatedListMenu(userChoice)) {
                printLineOfListMenu();
                userChoice = CollectorDataFromUser.getTextFromUser();
            }
            if ("back".equals(userChoice)) {
                isListMenuIsTurningOn = false;
            } else {
                if (phoneBook.contacts.isEmpty()) {
                    System.out.println("List is empty");
                    isListMenuIsTurningOn = false;
                } else if (phoneBook.contacts.size() < Integer.parseInt(userChoice)) {
                    System.out.println("Bad number");
                } else {
                    phoneBook.contacts.get(Integer.parseInt(userChoice) - 1).showInfo();
                    isListMenuIsTurningOn = false;
                    phoneBook.launchRecordMenu(phoneBook.contacts.get(Integer.parseInt(userChoice) - 1));
                }
            }
        }
        System.out.println();
    }

    public boolean isDataFromUserNotValidatedListMenu(String inputData) {
        return inputData.matches("[0-9]+|back");
    }

    public void printLineOfListMenu() {
        System.out.print("[list] Enter action ([number], back): ");
    }

    public void launchSearchMenu() {
        isSearchMenuIsTurningOn = true;
        while (isSearchMenuIsTurningOn) {
            searchedItems.clear();
            searchQuery();
            printLineOfSearchMenu();
            String userChoice = CollectorDataFromUser.getTextFromUser();
            while (!isDataFromUserNotValidatedSearchMenu(userChoice)) {
                printLineOfSearchMenu();
                userChoice = CollectorDataFromUser.getTextFromUser();
            }
            if ("again".equals(userChoice)) {
            } else if ("back".equals(userChoice)) {
                isSearchMenuIsTurningOn = false;
            } else {
                searchedItems.get(Integer.parseInt(userChoice) - 1).showInfo();
                phoneBook.launchRecordMenu(searchedItems.get(Integer.parseInt(userChoice) - 1));
                isSearchMenuIsTurningOn = false;
            }
        }
        System.out.println();
    }

    private void printLineOfSearchMenu() {
        System.out.print("[search] Enter action ([number], back, again]): ");
    }

    public boolean isDataFromUserNotValidatedSearchMenu(String inputData) {
        return inputData.matches("[0-9]+|back|again");
    }

    public void printSearchedContacts() {
        for (int i = 0; i < searchedItems.size(); i++) {
            System.out.println(i + 1 + ". " + searchedItems.get(i).getInfoForList());
        }
        System.out.println();
    }

    private void searchQuery() {
        System.out.print("Enter search query: ");
        String searchedPhrase = CollectorDataFromUser.getTextFromUser();
        Pattern pattern = Pattern.compile(searchedPhrase, Pattern.CASE_INSENSITIVE);
        for (Contact contact : phoneBook.contacts) {
            Matcher matcher = pattern.matcher(contact.toString());
            if (matcher.find()){
                searchedItems.add(contact);
            }
        }
        if (searchedItems.isEmpty()) {
            System.out.println("Found 0 results");
        } else if (searchedItems.size() == 1) {
            System.out.println("Found 1 result");
            printSearchedContacts();
        } else {
            System.out.println("Found " + searchedItems.size() + " results");
            printSearchedContacts();
        }
    }
}
