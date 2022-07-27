import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão http e bucar os top 250 filmes 
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        // String url = "https://api.mocki.io/v2/549a5d8b"; // usando endereço alternativo
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB(); 

        // String url = "http://localhost:8080/linguagens"; // usando endereço da api criada
        String url = "https://renato-linguagens-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa(); 

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //Exibir e manipular os dados
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
         for (int i = 0; i < 2; i++) {

            Conteudo conteudo = conteudos.get(i);       
    
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
    
            geradora.cria(inputStream, nomeArquivo);;

            System.out.println(conteudo.getTitulo());
            System.out.println();
         
        }

    }

}
