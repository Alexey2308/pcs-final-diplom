public class PageEntry implements Comparable<PageEntry> {
    private final String fileName;
    private final int page;
    private final int count;


    public String getFileName() {
        return fileName;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public PageEntry(String fileName, int page, int count) {
        this.fileName = fileName;
        this.page = page;
        this.count = count;
    }

    @Override
    public int compareTo(PageEntry p) {
        return Integer.compare(p.count, this.count);

    }

    @Override
    public String toString() {
        return "PageEntry{" +
                "pdfName='" + fileName + '\'' +
                ", page=" + page +
                ", count=" + count +
                '}';
    }

}
