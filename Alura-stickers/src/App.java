import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.cert.URICertStoreParameters;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão http e bucar os top 250 filmes 
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://api.mocki.io/v2/549a5d8b"; // usando endereço alternativo
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response  = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body); imprimir a listagem de filmes
        
        // Extair só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados 
        var geradora = new GeradoraDeFigurinhas();
         for (int i = 0; i < 3; i++) {

            Map<String,String> filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
    
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";
    
            geradora.cria(inputStream, nomeArquivo);;

            System.out.println(titulo);
            System.out.println();
         
        }

    }

}
