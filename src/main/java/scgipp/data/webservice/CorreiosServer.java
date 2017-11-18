<<<<<<< HEAD
package scgipp.data.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import static scgipp.data.webservice.CorreiosServer.PropertyTags.*;

/**
 * User: hugo_<br/>
 * Date: 03/10/2017<br/>
 * Time: 23:02<br/>
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CorreiosServer {

    private static final String urlString = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx";
    private static final String returnType = "XML";

    public static class ServiceCode {
        private ServiceCode() {}
        public static final String SEDEX_VAREJO = "40010";
        public static final String PAC_VAREJO = "41106";
    }
    public static class PackageType {
        private PackageType() {}
        public static final int BOX_PACKAGE = 1;
        public static final int ROLLER_PRISM = 2;
        public static final int ENVELOPE = 3;
    }
    public static class PropertyTags {
        public static final String VALUE = "Valor";
        public static final String DEADLINE = "PrazoEntrega";
        public static final String VALIE_WITHOUT_ADDICTS = "ValorSemAdicionais";
        public static final String VALUE_WITH_INDENTITY = "ValorMaoPropria";
        public static final String VALUE_WITH_RECEIPT_WARNING = "ValorAvisoRecebimento";
        public static final String DECLARED_VALUE = "ValorValorDeclarado";
        public static final String HOME_DELIVERY = "EntregaDomiciliar";
        public static final String SATURDAY_DELIVERY = "EntregaSabado";
        public static final String ERROR = "Erro";
        public static final String ERROR_MESSAGE = "MsgErro";
    }

    @WebMethod
    public Properties calcFreightSimulation(@Nullable String nCdEmpresa, @Nullable String sDsSenha, @NotNull String nCdServico, @NotNull String sCepOrigem, @NotNull String sCepDestino, @NotNull String nVlpeso, @NotNull int nCdFormato, @NotNull double nVlComprimento, @NotNull double nVlAltura, @NotNull double nVlLargura, @NotNull double nVlDiametro, @NotNull boolean MaoPropria, @NotNull double nVlValorDeclarado, @NotNull boolean AvisoRecebimento) {

        if (nCdEmpresa == null) nCdEmpresa = "";
        if (sDsSenha == null) sDsSenha = "";

        String sCdMaoPropria = MaoPropria ? "S" : "N";
        String sCdAvisoRecebimento = AvisoRecebimento ? "S" : "N";

        Properties parameters = new Properties();
        parameters.setProperty("nCdEmpresa", nCdEmpresa);
        parameters.setProperty("sDsSenha", sDsSenha);
        parameters.setProperty("nCdServico", nCdServico);
        parameters.setProperty("sCepOrigem", sCepOrigem);
        parameters.setProperty("sCepDestino", sCepDestino);
        parameters.setProperty("nVlPeso", nVlpeso);
        parameters.setProperty("nCdFormato", String.valueOf(nCdFormato));
        parameters.setProperty("nVlComprimento", String.valueOf(nVlComprimento));
        parameters.setProperty("nVlAltura", String.valueOf(nVlAltura));
        parameters.setProperty("nVlLargura", String.valueOf(nVlLargura));
        parameters.setProperty("nVlDiametro", String.valueOf(nVlDiametro));
        parameters.setProperty("sCdMaoPropria", sCdMaoPropria);
        parameters.setProperty("nVlValorDeclarado", String.valueOf(nVlValorDeclarado));
        parameters.setProperty("sCdAvisoRecebimento", String.valueOf(sCdAvisoRecebimento));
        parameters.setProperty("StrRetorno", returnType);

        String urlStringEdit = urlString;

        Iterator i = parameters.keySet().iterator();
        int counter = 0;
        while (i.hasNext()) {

            String name = (String)i.next();
            String value = parameters.getProperty(name);

            StringBuilder stringBuilder = new StringBuilder(urlStringEdit);
            urlStringEdit = stringBuilder.append((++counter == 1 ? "?" : "&") + name + "=" + value).toString();

        }

        Properties returnProperties = new Properties();

        try {

            URL url = new URL(urlStringEdit);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Request-Method", "GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer newData = new StringBuffer();

            String s;
            while (null != ((s = br.readLine()))) {
                newData.append(s);
            }

            br.close();

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(newData.toString()));
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("cServico");

            returnProperties = new Properties();

            for (int j = 0; j < nodes.getLength(); j++) {

                Element element = (Element)nodes.item(j);
                NodeList valor = element.getElementsByTagName(VALUE);
                Element line = (Element) valor.item(0);
                returnProperties.setProperty(VALUE, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(DEADLINE);
                line = (Element) valor.item(0);
                returnProperties.setProperty(DEADLINE, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(VALIE_WITHOUT_ADDICTS);
                line = (Element) valor.item(0);
                returnProperties.setProperty(VALIE_WITHOUT_ADDICTS, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(VALUE_WITH_INDENTITY);
                line = (Element) valor.item(0);
                returnProperties.setProperty(VALUE_WITH_INDENTITY, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(VALUE_WITH_RECEIPT_WARNING);
                line = (Element) valor.item(0);
                returnProperties.setProperty(VALUE_WITH_RECEIPT_WARNING, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(DECLARED_VALUE);
                line = (Element) valor.item(0);
                returnProperties.setProperty(DECLARED_VALUE, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(HOME_DELIVERY);
                line = (Element) valor.item(0);
                returnProperties.setProperty(HOME_DELIVERY, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(SATURDAY_DELIVERY);
                line = (Element) valor.item(0);
                returnProperties.setProperty(SATURDAY_DELIVERY, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(ERROR);
                line = (Element) valor.item(0);
                returnProperties.setProperty(ERROR, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(ERROR_MESSAGE);
                line = (Element) valor.item(0);
                returnProperties.setProperty(ERROR_MESSAGE, getCharacterDataFromElement(line));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnProperties;

    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

}
=======
package scgipp.data.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import static scgipp.data.webservice.CorreiosServer.PropertyTags.*;

/**
 * User: hugo_<br/>
 * Date: 03/10/2017<br/>
 * Time: 23:02<br/>
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CorreiosServer {

    private static final String urlString = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx";
    private static final String returnType = "XML";

    public static class ServiceCode {
        private ServiceCode() {}
        public static final String SEDEX_VAREJO = "40010";
        public static final String PAC_VAREJO = "41106";
    }
    public static class PackageType {
        private PackageType() {}
        public static final int BOX_PACKAGE = 1;
        public static final int ROLLER_PRISM = 2;
        public static final int ENVELOPE = 3;
    }
    public static class PropertyTags {
        public static final String VALUE = "Valor";
        public static final String DEADLINE = "PrazoEntrega";
        public static final String VALIE_WITHOUT_ADDICTS = "ValorSemAdicionais";
        public static final String VALUE_WITH_INDENTITY = "ValorMaoPropria";
        public static final String VALUE_WITH_RECEIPT_WARNING = "ValorAvisoRecebimento";
        public static final String DECLARED_VALUE = "ValorValorDeclarado";
        public static final String HOME_DELIVERY = "EntregaDomiciliar";
        public static final String SATURDAY_DELIVERY = "EntregaSabado";
        public static final String ERROR = "Erro";
        public static final String ERROR_MESSAGE = "MsgErro";
    }

    @WebMethod
    public Properties calcFreightSimulation(@Nullable String nCdEmpresa, @Nullable String sDsSenha, @NotNull String nCdServico, @NotNull String sCepOrigem, @NotNull String sCepDestino, @NotNull String nVlpeso, @NotNull int nCdFormato, @NotNull double nVlComprimento, @NotNull double nVlAltura, @NotNull double nVlLargura, @NotNull double nVlDiametro, @NotNull boolean MaoPropria, @NotNull double nVlValorDeclarado, @NotNull boolean AvisoRecebimento) {

        if (nCdEmpresa == null) nCdEmpresa = "";
        if (sDsSenha == null) sDsSenha = "";

        String sCdMaoPropria = MaoPropria ? "S" : "N";
        String sCdAvisoRecebimento = AvisoRecebimento ? "S" : "N";

        Properties parameters = new Properties();
        parameters.setProperty("nCdEmpresa", nCdEmpresa);
        parameters.setProperty("sDsSenha", sDsSenha);
        parameters.setProperty("nCdServico", nCdServico);
        parameters.setProperty("sCepOrigem", sCepOrigem);
        parameters.setProperty("sCepDestino", sCepDestino);
        parameters.setProperty("nVlPeso", nVlpeso);
        parameters.setProperty("nCdFormato", String.valueOf(nCdFormato));
        parameters.setProperty("nVlComprimento", String.valueOf(nVlComprimento));
        parameters.setProperty("nVlAltura", String.valueOf(nVlAltura));
        parameters.setProperty("nVlLargura", String.valueOf(nVlLargura));
        parameters.setProperty("nVlDiametro", String.valueOf(nVlDiametro));
        parameters.setProperty("sCdMaoPropria", sCdMaoPropria);
        parameters.setProperty("nVlValorDeclarado", String.valueOf(nVlValorDeclarado));
        parameters.setProperty("sCdAvisoRecebimento", String.valueOf(sCdAvisoRecebimento));
        parameters.setProperty("StrRetorno", returnType);

        String urlStringEdit = urlString;

        Iterator i = parameters.keySet().iterator();
        int counter = 0;
        while (i.hasNext()) {

            String name = (String)i.next();
            String value = parameters.getProperty(name);

            StringBuilder stringBuilder = new StringBuilder(urlStringEdit);
            urlStringEdit = stringBuilder.append((++counter == 1 ? "?" : "&") + name + "=" + value).toString();

        }

        Properties returnProperties = new Properties();

        try {

            URL url = new URL(urlStringEdit);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Request-Method", "GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer newData = new StringBuffer();

            String s;
            while (null != ((s = br.readLine()))) {
                newData.append(s);
            }

            br.close();

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(newData.toString()));
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("cServico");

            returnProperties = new Properties();

            for (int j = 0; j < nodes.getLength(); j++) {

                Element element = (Element)nodes.item(j);
                NodeList valor = element.getElementsByTagName(VALUE);
                Element line = (Element) valor.item(0);
                returnProperties.setProperty(VALUE, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(DEADLINE);
                line = (Element) valor.item(0);
                returnProperties.setProperty(DEADLINE, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(VALIE_WITHOUT_ADDICTS);
                line = (Element) valor.item(0);
                returnProperties.setProperty(VALIE_WITHOUT_ADDICTS, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(VALUE_WITH_INDENTITY);
                line = (Element) valor.item(0);
                returnProperties.setProperty(VALUE_WITH_INDENTITY, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(VALUE_WITH_RECEIPT_WARNING);
                line = (Element) valor.item(0);
                returnProperties.setProperty(VALUE_WITH_RECEIPT_WARNING, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(DECLARED_VALUE);
                line = (Element) valor.item(0);
                returnProperties.setProperty(DECLARED_VALUE, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(HOME_DELIVERY);
                line = (Element) valor.item(0);
                returnProperties.setProperty(HOME_DELIVERY, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(SATURDAY_DELIVERY);
                line = (Element) valor.item(0);
                returnProperties.setProperty(SATURDAY_DELIVERY, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(ERROR);
                line = (Element) valor.item(0);
                returnProperties.setProperty(ERROR, getCharacterDataFromElement(line));

                element = (Element)nodes.item(j);
                valor = element.getElementsByTagName(ERROR_MESSAGE);
                line = (Element) valor.item(0);
                returnProperties.setProperty(ERROR_MESSAGE, getCharacterDataFromElement(line));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnProperties;

    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

}
>>>>>>> [C]ObservableCustomer
