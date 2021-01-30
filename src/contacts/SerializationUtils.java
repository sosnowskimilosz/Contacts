package contacts;

import contacts.products.Contact;

import java.io.*;
import java.util.List;

public class SerializationUtils {

    public static void serialize(List<Contact> list, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(list);
        oos.close();
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        List<Contact> list = (List<Contact>) ois.readObject();
        ois.close();
        return list;
    }
}
