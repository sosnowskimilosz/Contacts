package contacts.factory;

import contacts.CollectorDataFromUser;
import contacts.products.Contact;
import contacts.products.Organization;
import contacts.products.Person;

public class ContactFactory {

    public Contact prepareContact(){
        System.out.print("Enter the type (person, organization): ");
        String type= CollectorDataFromUser.getTextFromUser();
        while(!type.matches("person|organization")){
            type= CollectorDataFromUser.getTextFromUser();
        }
        if("person".equals(type)){
            return new Person();
        }else if("organization".equals(type)){
            return new Organization();
        }else{
            return null;
        }
    }
}
