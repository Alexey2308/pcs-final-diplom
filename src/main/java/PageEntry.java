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
        if (count < p.count) {
            return 1;
        } else if (count > p.count) {
            return -1;
        } else {
            if (page < p.page) {
                return 1;
            } else if (page > p.page) {
                return -1;
            } else {
                return getFileName().compareTo(p.getFileName());
            }
        }
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
