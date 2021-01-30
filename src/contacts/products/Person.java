package contacts.products;

import contacts.CollectorDataFromUser;

public class Person extends Contact {

    private String surname;
    private String birthDate;
    private String gender;

    public Person() {
        setData();
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String txtDate) {
        if (txtDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
            this.birthDate = txtDate;
        } else {
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        }
    }

    public void setGender(String txtGender) {
        if (txtGender.matches("F|M")) {
            this.gender = txtGender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }

    @Override
    public void setData() {
        System.out.print("Enter the name: ");
        setName(CollectorDataFromUser.getTextFromUser());
        System.out.print("Enter the surname: ");
        setSurname(CollectorDataFromUser.getTextFromUser());
        System.out.print("Enter the birthdate: ");
        setBirthDate(CollectorDataFromUser.getTextFromUser());
        System.out.print("Enter the gender (M, F): ");
        setGender(CollectorDataFromUser.getTextFromUser());
        System.out.print("Enter the number: ");
        setNumberWithValidation(CollectorDataFromUser.getTextFromUser());
        setCreatedTime();
        setLastEditTime();
    }

    @Override
    public void showInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + getCreatedTime());
        System.out.println("Time last edit: " + getLastEditTime());
        System.out.println();
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public void edit() {
        printEditMenu();
        String userChoice = contacts.CollectorDataFromUser.getTextFromUser();
        while (!isDataFromUserNotValidatedEditMenu(userChoice)) {
            printEditMenu();
            userChoice = contacts.CollectorDataFromUser.getTextFromUser();
        }
        switch (userChoice) {
            case "name":
                System.out.print("Enter name: ");
                setName(contacts.CollectorDataFromUser.getTextFromUser());
                break;
            case "surname":
                System.out.print("Enter surname: ");
                setSurname(contacts.CollectorDataFromUser.getTextFromUser());
                break;
            case "number":
                System.out.print("Enter number: ");
                setNumberWithValidation(contacts.CollectorDataFromUser.getTextFromUser());
                break;
            case "birth":
                System.out.print("Enter birth: ");
                setBirthDate(contacts.CollectorDataFromUser.getTextFromUser());
                break;
            case "gender":
                System.out.print("Enter the gender (M, F): ");
                setGender(contacts.CollectorDataFromUser.getTextFromUser());
                break;
        }
        setLastEditTime();
        System.out.println("Saved");
        showInfo();
    }

    public boolean isDataFromUserNotValidatedEditMenu(String inputData) {
        return inputData.matches("name|surname|birth|gender|number");
    }

    public void printEditMenu() {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
    }

    @Override
    public String getInfoForList() {
        return getName()+" "+getSurname();
    }

    @Override
    public String toString() {
        return super.getName()+" "+getSurname()+" "+getBirthDate()+" "+getGender()+" "+super.getPhoneNumber();
    }
}
