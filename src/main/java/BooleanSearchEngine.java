import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class BooleanSearchEngine implements SearchEngine {
    protected Map<String, ArrayList<PageEntry>> totalWords;

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы

        totalWords = new HashMap<>();

        Map<String, Integer> freqs = new HashMap<>(); // мапа, где ключом будет слово, а значением - частота
        List<File> pdfFileList = List.of(pdfsDir.listFiles());

        for (File pdf : pdfFileList) {
            var doc = new PdfDocument(new PdfReader(pdf));

            for (int nomberOfPage = 1; nomberOfPage <= doc.getNumberOfPages(); nomberOfPage++) {
                var text = PdfTextExtractor.getTextFromPage(doc.getPage(nomberOfPage));
                var words = text.split("\\P{IsAlphabetic}+");

                for (var word : words) { // перебираем слова
                    if (word.isEmpty()) {
                        continue;
                    }
                    word = word.toLowerCase();
                    freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                }
                int count;
                for (var j : freqs.keySet()) {
                    String word = j.toLowerCase();
                    if (freqs.get(word) != null) {
                        count = freqs.get(word.toLowerCase());

                        totalWords.computeIfAbsent(word, f -> new ArrayList<PageEntry>()).add(new PageEntry(pdf.getName(), nomberOfPage, count));

                    }
                    Collections.sort(totalWords.get(word));
                }
                freqs.clear();

            }
        }
    }

    @Override
    public List<PageEntry> search(String word) {
        List<PageEntry> searchResult = new ArrayList<>();
        if (totalWords.get(word.toLowerCase()) != null) {
            searchResult = totalWords.get(word.toLowerCase());
        }

        return searchResult;
    }

    @Override
    public String toString() {
        return "BooleanSearchEngine{" + "totalWords=" + totalWords + '}';
    }
}
