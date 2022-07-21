import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão http e bucar os top 250 filmes 
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://api.mocki.io/v2/549a5d8b"; // usando endereço alternativo

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        //System.out.println(body); imprimir a listagem de filmes
        
        // Extair só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(json);

        //Exibir e manipular os dados 
        var geradora = new GeradoraDeFigurinhas();
         for (int i = 0; i < 3; i++) {

            Map<String,String> conteudo = listaDeConteudos.get(i);

            String urlImagem = conteudo.get("image");
            String titulo = conteudo.get("title");
    
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";
    
            geradora.cria(inputStream, nomeArquivo);;

            System.out.println(titulo);
            System.out.println();
         
        }

    }

}
