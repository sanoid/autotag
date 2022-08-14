import java.io.IOException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class autotag {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Input album title: ");
        String albumTitle = in.nextLine().replaceAll("\\s+", " ");
        System.out.println("Specify artist name: ");
        String artistName = in.nextLine().replaceAll("\\s+", " ");
        Document doc = null;

        try {
            String baseUrl = "https://www.last.fm/music/";
            artistName = URLEncoder.encode(artistName, "utf-8");
            albumTitle = URLEncoder.encode(albumTitle, "utf-8");
            String albumUrl = baseUrl + artistName + "/" + albumTitle;
            doc = Jsoup.connect(albumUrl).get();
        } catch (HttpStatusException e) {
            System.out.println("Ошибка! Не удалось найти альбом по указанным критериям.");
            System.exit(1);
        } catch (UnknownHostException e) {
            System.out.println("Сервер недоступен.");
            System.exit(2);
        }

        System.out.println("Tracklist: ");
        int attributeCount = doc.getElementsByClass("chartlist-name").size();
        int i = 0;

        while (i < attributeCount) {
            Element trackTitleAttribute = doc.getElementsByClass("chartlist-name").get(i);
            Elements trackTitle = trackTitleAttribute.select("a[title]");
            String trackTitleValue = trackTitle.attr("title");
            i++;
            System.out.println(i + ". " + trackTitleValue);
        }

        System.exit(0);
    }
}