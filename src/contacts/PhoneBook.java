package contacts;

import contacts.products.Contact;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    List<Contact> contacts = new ArrayList<>();
    boolean isRecordMenuTurningOn=false;

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("The record added.\n");
    }

    public void printContacts() {
        if (contacts.isEmpty()) {
            System.out.println("The Phone Book is empty.\n");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println(i + 1 + ". " + contacts.get(i).getInfoForList());
            }
            System.out.println();
        }
    }

    public void printNumberOfContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.\n");
    }

    public void launchRecordMenu(Contact contact) {
        isRecordMenuTurningOn=true;
        while (isRecordMenuTurningOn) {
            printLineOfRecordMenu();
            String userChoice = CollectorDataFromUser.getTextFromUser();
            while (!isDataFromUserNotValidatedRecordMenu(userChoice)) {
                printLineOfRecordMenu();
                userChoice = CollectorDataFromUser.getTextFromUser();
            }
            switch (userChoice) {
                case "edit":
                    contact.edit();
                    break;
                case "delete":
                    contacts.remove(contact);
                    isRecordMenuTurningOn=false;
                    break;
                case "menu":
                    isRecordMenuTurningOn=false;
                    break;
            }
        }
    }

    public boolean isDataFromUserNotValidatedRecordMenu(String inputData) {
        return inputData.matches("edit|delete|menu");
    }

    private void printLineOfRecordMenu() {
        System.out.print("[record] Enter action (edit, delete, menu): ");
    }
}
