package Simulacros.PSP.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoSend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        URI base = URI.create ("https://dle.rae.es/");

        System.out.println("Porfavor pasame una capital: ");
        String capital = sc.nextLine();
        URI find = base.resolve(capital);

        try {
            URL url = find.toURL();

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent","Chrome");
            try(BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()))){
                Pattern pattern = Pattern.compile("(<.*?>).*?(<.*>)");
                Matcher matcher;
                String line;

                while ((line = input.readLine()) != null){
                    matcher = pattern.matcher(line);
                    if(matcher.find()){
                        System.out.println(matcher.group(1));
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
