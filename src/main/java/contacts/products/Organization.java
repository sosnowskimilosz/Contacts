package contacts.products;

import contacts.CollectorDataFromUser;

public class Organization extends Contact {
    private String address;

    public Organization() {
        setData();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setData() {
        System.out.print("Enter the organization name: ");
        setName(CollectorDataFromUser.getTextFromUser());
        System.out.print("Enter the address: ");
        setAddress(CollectorDataFromUser.getTextFromUser());
        System.out.print("Enter the number: ");
        setNumberWithValidation(CollectorDataFromUser.getTextFromUser());
        setCreatedTime();
        setLastEditTime();
    }

    @Override
    public void showInfo() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getCreatedTime());
        System.out.println("Time last edit: " + getLastEditTime());
        System.out.println();
    }

    @Override
    public void edit() {
        printEditMenu();
        String userChoice = CollectorDataFromUser.getTextFromUser();
        while (!isDataFromUserNotValidatedEditMenu(userChoice)) {
            printEditMenu();
            userChoice = CollectorDataFromUser.getTextFromUser();
        }
        switch (userChoice) {
            case "name":
                System.out.print("Enter name: ");
                setName(CollectorDataFromUser.getTextFromUser());
                break;
            case "address":
                System.out.print("Enter address: ");
                setAddress(CollectorDataFromUser.getTextFromUser());
                break;
            case "number":
                System.out.print("Enter number: ");
                setNumberWithValidation(CollectorDataFromUser.getTextFromUser());
                break;
        }
        setLastEditTime();
        System.out.println("Saved");
        showInfo();
    }

    @Override
    public String getInfoForList() {
        return getName();
    }

    public boolean isDataFromUserNotValidatedEditMenu(String inputData) {
        return inputData.matches("name|address|number");
    }

    public void printEditMenu() {
        System.out.print("Select a field(name, address, number): ");
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return super.getName()+" "+getAddress()+" "+super.getPhoneNumber();
    }
}
