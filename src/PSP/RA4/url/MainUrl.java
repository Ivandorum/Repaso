package PSP.RA4.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * . --> cualquier caracter
 * [a-zA-Z] --> cualquier letra
 * [0-9] o \\d --> cualquier digito
 * \\s --> espacio
 *
 * -Cuantificadores
 * -- * --> 0..n --> {0,}
 * -- ? --> 0..1 --> {0,1}
 * -- + --> 1..n --> {1,}
 * -- {} --> numero exacto de veces
 */
public class MainUrl {
    public static void main(String[] args) {
        URI base = URI.create("https://example.org");
        try {
           URL url = base.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Chrome"); //Si devuelve error 403 esto puede solucionarlo a veces
            try(BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                Pattern pattern = Pattern.compile("<a(.*)href=\"(.*?)\"(.*)>"); //<a id="" href="" class="">
                Matcher matcher;
                String line;
                while((line = input.readLine()) != null){
                    matcher = pattern.matcher(line);
                    if(matcher.find()){
                        System.out.println(matcher.group(2));
                    }
                }
            }
        }  catch (IOException e) {
            System.err.println();
        }
    }
}
