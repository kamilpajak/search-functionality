package setup;

public enum Environment {

    PRODUCTION("https://www.autohero.com/");

    private final String url;

    Environment(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}
