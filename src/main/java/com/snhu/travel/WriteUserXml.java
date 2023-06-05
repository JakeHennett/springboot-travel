package com.snhu.travel;
//my variation on writexmldom1

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteUserXml {

    public static void main(String[] args)
            throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("users");
        doc.appendChild(rootElement);

        Element user = doc.createElement("user");
        rootElement.appendChild(user);

        Element username = doc.createElement("username");
        username.setTextContent("programmer");
        user.appendChild(username);

        Element password = doc.createElement("password");
        password.setTextContent("hardcoded");
        user.appendChild(password);

        /*
        Element userElement = doc.createElement("user");
        rootElement.appendChild(userElement);

        userElement.appendChild(doc.createElement("username"));
        userElement.appendChild(doc.createElement("password"));

        Element user = doc.createElement("user");
        rootElement.appendChild(user);
        */



        /*
      // staff 1001
      Element name = doc.createElement("name");
      // JDK 1.4
      //name.appendChild(doc.createTextNode("mkyong"));
      // JDK 1.5
      name.setTextContent("mkyong");
      staff.appendChild(name);

      Element role = doc.createElement("role");
      role.setTextContent("support");
      staff.appendChild(role);

      Element salary = doc.createElement("salary");
      salary.setAttribute("currency", "USD");
      salary.setTextContent("5000");
      staff.appendChild(salary);

      // add xml comment
      Comment comment = doc.createComment(
              "for special characters like < &, need CDATA");
      staff.appendChild(comment);

      Element bio = doc.createElement("bio");
      // add xml CDATA
      CDATASection cdataSection =
              doc.createCDATASection("HTML tag <code>testing</code>");
      bio.appendChild(cdataSection);
      staff.appendChild(bio);

      // staff 1002
      Element staff2 = doc.createElement("staff");
      // add staff to root
      rootElement.appendChild(staff2);
      staff2.setAttribute("id", "1002");

      Element name2 = doc.createElement("name");
      name2.setTextContent("yflow");
      staff2.appendChild(name2);

      Element role2 = doc.createElement("role");
      role2.setTextContent("admin");
      staff2.appendChild(role2);

      Element salary2 = doc.createElement("salary");
      salary2.setAttribute("currency", "EUD");
      salary2.setTextContent("8000");
      staff2.appendChild(salary2);

      Element bio2 = doc.createElement("bio");
      // add xml CDATA
      bio2.appendChild(doc.createCDATASection("a & b"));
      staff2.appendChild(bio2);
         */
        //user.setAttribute("username", "programmer");
        //user.setAttribute("password", "hardcoded");

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