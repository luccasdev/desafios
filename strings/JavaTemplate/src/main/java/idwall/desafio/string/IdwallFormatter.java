package idwall.desafio.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Template by Rodrigo Catão Araujo on 06/02/2018.
 * Implemented by Lucas Souza on 08/09/2020.
 */
public class IdwallFormatter extends StringFormatter {

    private static final String LINE_BREAK = "\n";
    private static final String WHITE_SPACE = " ";
    private static final String WORD_SPLIT = " +";

    /**
     * It receives a text and should return it formatted
     *
     * @param text - Text to apply format
     * @return String
     */
    @Override
    public String format(String text) {
        String[] words = text.split(WORD_SPLIT);
        StringBuilder builder = new StringBuilder();
        List<String> lines = new ArrayList<>();

        for (String word : words) {

            if (this.fitInLine(word, builder.length())) {
                lines.add(builder.toString());
                builder = new StringBuilder();
            }

            builder.append(WHITE_SPACE).append(word);
            builder = new StringBuilder(builder.toString().trim());
        }
        lines.add(builder.toString());

        if (this.isJustify()) {
            lines = this.justifyLines(lines);
        }

        return lines.stream()
                .map(Object::toString)
                .collect(Collectors.joining(LINE_BREAK));
    }

    /**
     * @param word - Word to validate if is available in line
     * @param lineLength - current length in line
     * @return boolean
     */
    private boolean fitInLine(String word, Integer lineLength) {
        return word.length() + lineLength + 1 > this.getLimit();
    }


    /**
     * @param lines - List of lines to apply justify
     * @return List<String>
     */
    @Override
    public List<String> justifyLines(List<String> lines) {
        List<String> justifiedLines = new ArrayList<>();

        for (String line : lines) {
            String[] words = line.split(WORD_SPLIT);
            String outerPadding = "";
            int remaining = this.getLimit() - line.length();
            int spaces = remaining / words.length;
            int spacesToFill = remaining % words.length;

            StringBuilder builder = new StringBuilder();

            for (String word : words) {
                int innerPadding = spaces + 1;
                if (spacesToFill > 0) {
                    innerPadding += 1;
                    spacesToFill--;
                }
                builder.append(word).append(String.format("%" + innerPadding + "s", ""));
            }

            builder = new StringBuilder(builder.toString().trim());

            spacesToFill = this.getLimit() - builder.length();

            if (spacesToFill > 0) {
                outerPadding = String.format("%" + spacesToFill + "s", "");
            }

            justifiedLines.add(outerPadding + builder);
        }
        return justifiedLines;
    }
}
