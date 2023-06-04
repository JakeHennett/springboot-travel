package com.snhu.travel;
 
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
 
public class JXBExampleOutJava {
 
    private static final String xmlFilePath = "C:\\Users\\jakeh\\git\\travel\\src\\main\\resources\\xml\\Student.xml";
    //original "C:\\Users\\nikos7\\Desktop\\filesForExamples\\Student.xml";
    //local C:\Users\jakeh\git\travel\src\main\resources\xml\Student.xml
    //local formatted "C:\\Users\\jakeh\\git\\travel\\src\\main\\resources\\xml\\Student.xml";
 
    public static void main(String[] args) {
 
        try {
 
            File xmlFile = new File(xmlFilePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
 
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Student student = (Student) jaxbUnmarshaller.unmarshal(xmlFile);
            System.out.println(student);
 
        } catch (JAXBException e) {
            e.printStackTrace();
        }
 
    }
}