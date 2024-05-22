package PSP.RA4.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMatcher {

    /**
     *
     * https://wikepedia.org
     * <img class="dfdfdf" ... src ="portal/img/logo.png" width="";
     *<style>
     *     background-repeat:no-repeat;
     *</style>
     *
     */

    public static void main(String[] args) {
        URI uri = URI.create("https://www.stackoverflow.co");
        try {
            URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Chrome");
            int codigo = con.getResponseCode();
            if(codigo == 200) {
                try (BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    Pattern pattern = Pattern.compile("<img(.*?)src=\"(.*?)\"");
                    Pattern pattern1 = Pattern.compile("<style>(.*?)(background.*?:.*?);(.*)</style>");
                    String line;
                    Matcher matcher;
                    Matcher matcher1;
                    while ((line = input.readLine()) != null) {
                        matcher = pattern.matcher(line);
                        matcher1 = pattern1.matcher(line);
                        while (matcher1.find()) {
                            //System.out.println(matcher.group(2));
                            System.out.println(matcher1.group(2));
                        }

                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args) {
        String texto = "Curso 2023-2024";
        Pattern p = Pattern.compile("[a-zA-Z]{5}[ ][0-9]{4}[-][0-9]{4}");
        Matcher m = p.matcher(texto);
        //System.out.println(m.find());

        String password = "port:201 user:root password:pposdmasdasdksl2347578944*"; // 1 mayu + letras + dig + 1car esp
        Pattern pattern = Pattern.compile(".*(password:)([a-z]+[0-9]+\\W)");
        Matcher matcher = pattern.matcher(password);
        if(matcher.find()){
            System.out.println(matcher.group(2));
        }

        String html = "<h1>Encabeado</h1>" +
                "<p>estamos en un parafo</p>"+
                "<div><section>cosa</section>"+
                "<h4>estoy es en h4></h4></div>"+
                "<p>final</p>" +
                "<div>end</div><footer>";
        Pattern pattern1 = Pattern.compile("<div>(.*?)</div>");
        Matcher matcherDiv = pattern1.matcher(html);
        while(matcherDiv.find()){
            System.out.println(matcherDiv.group(1));
        }
    }

     */
}
