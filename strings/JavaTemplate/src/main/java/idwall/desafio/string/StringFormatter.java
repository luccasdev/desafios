package idwall.desafio.string;

import java.util.List;

/**
 * Template by Rodrigo Catão Araujo on 06/02/2018.
 * Implemented by Lucas Souza on 08/09/2020.
 */
public abstract class StringFormatter {

    private Integer limit;
    private boolean justify;

    public StringFormatter() {
        this.limit = 40;
        this.justify = true;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text - Text to apply format
     * @return String
     */
    public abstract String format(String text);

    /**
     * It receives a text and should return it formatted
     *
     * @param lines - Lines to apply format
     * @return List<String>
     */
    public abstract List<String> justifyLines(List<String> lines);


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public boolean isJustify() {
        return justify;
    }

    public void setJustify(boolean justify) {
        this.justify = justify;
    }
}
