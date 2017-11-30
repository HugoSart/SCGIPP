package scgipp.service.validators.CEP;

/**
 * @author: danilovazb
 * Site: http://respostas.guj.com.br/6592-api-java-para-buscar-cep-correios
 */

import java.io.IOException;
import java.net.SocketTimeoutException;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CepData {

    private static CepData cepData;

    public static CepData get() {
        if (cepData == null) {
            cepData = new CepData();
        }
        return cepData;
    }

    private CepData() {
    }

    public String getRua(String CEP) throws IOException {
        try {

            Document doc = Jsoup
                    .connect("http://www.qualocep.com/busca-cep/" + CEP)
                    .timeout(120000).get();
            Elements urlPesquisa = doc.select("span[itemprop=streetAddress]");
            for (Element urlEndereco : urlPesquisa) {
                return urlEndereco.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return null;
    }

    public String getBairro(String CEP) throws IOException {
        try {

            Document doc = Jsoup
                    .connect("http://www.qualocep.com/busca-cep/" + CEP)
                    .timeout(120000).get();
            Elements urlPesquisa = doc.select("td:gt(1)");
            for (Element urlBairro : urlPesquisa) {
                return urlBairro.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return null;
    }

    public String getCidade(String CEP) throws IOException {
        try {

            Document doc = Jsoup
                    .connect("http://www.qualocep.com/busca-cep/" + CEP)
                    .timeout(120000).get();
            Elements urlPesquisa = doc.select("span[itemprop=addressLocality]");
            for (Element urlCidade : urlPesquisa) {
                return urlCidade.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return null;
    }

    public String getUF(String CEP) throws IOException {
        try {

            Document doc = Jsoup
                    .connect("http://www.qualocep.com/busca-cep/" + CEP)
                    .timeout(120000).get();
            Elements urlPesquisa = doc.select("span[itemprop=addressRegion]");
            for (Element urlUF : urlPesquisa) {
                return urlUF.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return null;
    }
}

