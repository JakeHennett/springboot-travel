package com.snhu.travel;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.OutputStream;

@RestController
public class RestSecurityController {

    private static final String FILENAME = "c:\\test\\users.xml";

    //Health check endpoint to determine if security endpoints are live
    @RequestMapping("/security")
    public String securityTest() {
        return "test security endpoints";
    }

    // http://localhost:8080/security/allusers?continue
    @GetMapping(value = "/security/allusers")
    private List<User> getAllUsers() {
        String result = "";
        List<User> userList = new ArrayList<User>();

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("user");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();

                    result += "\nCurrent Element :" + node.getNodeName();
                    result += "\nUsername : " + username;
                    result += "\nPassword : " + password;

                    User newUser = new User(username, password);
                    userList.add(newUser);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        return userList;
    }

    // http://localhost:8080/security/validate?uname=default&pass=password
    @GetMapping(value = "/security/validate")
    private boolean validateUser(@RequestParam String uname, @RequestParam String pass) {
        List<User> userList = getAllUsers();
        User validate = new User(uname, pass);

        for (User checkUser : userList) {
            if (validate.getUsername().equals(checkUser.getUsername())) {
                if (validate.getPassword().equals(checkUser.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    // http://localhost:8080/security/register?uname=default&pass=password
    @GetMapping(value = "/security/register")
    private void registerUser(@RequestParam String uname, @RequestParam String pass) {
        // TODO: Add some conditions at this level. No duplicate users, etc.
        // TODO: Make it a POST and not a GET
        addUser(uname, pass);
    }

    // http://localhost:8080/security/delete?uname=default
    @GetMapping(value = "/security/delete")
    private void deleteUser(@RequestParam String uname) {
        // TODO: Make it a DELETE and not a GET
        deleteByUsername(uname);
    }

    //Accept a username and delete the corresponding user record from the XML file.
    private boolean deleteByUsername(String uname) {
        boolean result = false;
        //TODO: Create logic to search for provided username and delete that record
        return result;
    }

    private boolean addUser(String uname, String pass) {
        User newUser = new User(uname, pass);
        List<User> userList = getAllUsers();
        userList.add(newUser);

        try {
            writeToFile(userList);
            return true;
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    private void writeToFile(List<User> userList) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("users");
        doc.appendChild(rootElement);

        // repeat here
        for (User buildUser : userList) {
            // build out the individual user element

            Element user = doc.createElement("user");
            rootElement.appendChild(user);

            Element username = doc.createElement("username");
            username.setTextContent(buildUser.getUsername());
            user.appendChild(username);

            Element password = doc.createElement("password");
            password.setTextContent(buildUser.getPassword());
            user.appendChild(password);
        }

        // write dom document to a file
        try (FileOutputStream output = new FileOutputStream("c:\\test\\users.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write doc to output stream
    private static void writeXml(Document doc,
            OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print XML
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
