package luccasdev.idwall.desafio.crawler.utils;

public enum CommandEnum {
    NADA_PRA_FAZER("/nadaprafazer"),
    HELP("/help"),
    START("/start");

    private String text;

    CommandEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
