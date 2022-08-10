import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class autotag {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Input album title: ");
        String albumTitle = in.nextLine();
        System.out.println("Specify artist name: ");
        String artistName = in.nextLine();
        String editedAlbumTitle = albumTitle.replaceAll(" ", "+");
        String editedArtistName = artistName.replaceAll(" ", "+");
        String albumUrl = "https://www.last.fm/music/" + artistName + "/" + albumTitle;
        Document doc = Jsoup.connect(albumUrl).get();
        System.out.println("Tracklist: ");
        int attributeCount = doc.getElementsByClass("chartlist-name").size();
        int i = 0;

        while(i < attributeCount) {
            Element trackTitleAttribute = doc.getElementsByClass("chartlist-name").get(i);
            Elements trackTitle = trackTitleAttribute.select("a[title]");
            String trackTitleValue = trackTitle.attr("title");
            i++;
            System.out.println(i + ". " + trackTitleValue);
        }

    }
}