package contacts.products;

import contacts.ValidatorOfPhoneNumber;

import java.io.Serializable;
import java.time.LocalDateTime;

abstract public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private LocalDateTime createdTime;
    private LocalDateTime lastEditTime;

    abstract public void setData();
    abstract public void showInfo();
    abstract public void edit();

    public void setNumberWithValidation(String inputData) {
        if (ValidatorOfPhoneNumber.isNumberOk(inputData)) {
            setPhoneNumber(inputData);
        } else {
            System.out.println("Wrong number format!");
            setPhoneNumber("[no number]");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedTime() {
        this.createdTime = LocalDateTime.now().withNano(0);
    }

    public void setLastEditTime() {
        this.lastEditTime = LocalDateTime.now().withNano(0);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public abstract String getInfoForList();
}
