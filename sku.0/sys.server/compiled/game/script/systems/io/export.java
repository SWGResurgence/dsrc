package script.systems.io;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import script.obj_id;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class export extends script.base_script
{
    public export()
    {
    }

    public static void pause(int ms)
    {
        try
        {
            Thread.sleep(ms);
        } catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void writeXml(Document doc, OutputStream output) throws TransformerException
    {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);

    }

    public int OnAddedToWorld(obj_id self) throws InterruptedException, ParserConfigurationException, TransformerException
    {
        pause(30000);
        String filename = "/home/swg/swg-main/pl_export/" + getPlayerName(getSelf()) + ".xml";
        exportToJson(getSelf(), filename);
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public void exportToJson(obj_id self, String filename) throws InterruptedException, TransformerException, ParserConfigurationException
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("resurgence");
        doc.appendChild(rootElement);
        Element character = doc.createElement("character");
        rootElement.appendChild(character);
        character.setAttribute("id", self.toString());
        Element name = doc.createElement("name");
        name.setTextContent(getPlayerFullName(self));
        character.appendChild(name);
        Element profession = doc.createElement(getSkillTemplate(self));
        profession.setTextContent("support");
        character.appendChild(profession);//
        Element bindloc = doc.createElement("bindloc");
        profession.setTextContent(getHomeLocation(self).toString());
        character.appendChild(bindloc);
        Element salary = doc.createElement("credits");
        salary.setAttribute("depositbox", getCashBalance(self) + "");
        salary.setTextContent(getBankBalance(self) + " Galactic Credits");
        character.appendChild(salary);
        Comment comment = doc.createComment("End Player Information");
        character.appendChild(comment);
        writeXml(doc, System.out);
    }
}
