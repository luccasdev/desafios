package idwall.desafio.string;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Template by Rodrigo Catão Araujo on 06/02/2018.
 * Implemented by Lucas Souza on 08/09/2020.
 */
public class IdwallFormatter extends StringFormatter {

    private static final String LINE_BREAK = "\n";
    private static final String PARAGRAPH_BREAK = "\n\n";
    private static final String WHITE_SPACE = " ";
    private static final String WORD_SPLIT = " ";

    @Override
    public String format(String text) {
        List<String> paragraphs = Arrays.asList(text.split(PARAGRAPH_BREAK).clone());

        StringBuilder textFormattedBuilder = new StringBuilder();

        for (Iterator<String> paragraphIterator = paragraphs.iterator(); paragraphIterator.hasNext();) {
            StringBuilder paragraphBuilder = new StringBuilder();
            String paragraph = paragraphIterator.next();
            String[] words = paragraph.split(WORD_SPLIT);
            int totalWordsInLine = 0;

            for (String word : words) {

                if (this.notFitInLine(word, totalWordsInLine)) {
                    totalWordsInLine = word.length() + 1;
                    paragraphBuilder.append(LINE_BREAK).append(word);
                } else {
                    totalWordsInLine += word.length() + 1;
                    paragraphBuilder.append(WHITE_SPACE).append(word);
                }
            }

            String paragraphFormatted = paragraphBuilder.toString().trim();

            if (this.isJustify()) {
                paragraphFormatted = justifyParagraph(paragraphFormatted);
            }

            textFormattedBuilder.append(paragraphFormatted);

            if (paragraphIterator.hasNext()) {
                textFormattedBuilder.append(PARAGRAPH_BREAK);
            }
        }

        return textFormattedBuilder.toString();
    }

    private boolean notFitInLine(String word, Integer lineLength) {
        return word.length() + lineLength > this.getLimit();
    }

    @Override
    public String justifyParagraph(String paragraph) {
        String[] lines = paragraph.split("(?=\n)");

        StringBuilder paragraphJustifiedBuilder = new StringBuilder();

        for (String line : lines) {
            String[] words = line.split(WORD_SPLIT);
            String outerPadding = "";

            int remaining = calculateRemaining(line);
            int spacesPerWord = (remaining / words.length) + 1;
            int spacesToFill = remaining % words.length;

            for (String word : words) {
                int padding = spacesPerWord;
                if (spacesToFill > 0) {
                    padding += 1;
                    spacesToFill--;
                }
                paragraphJustifiedBuilder.append(word).append(String.format("%" + padding + "s", ""));
            }

            paragraphJustifiedBuilder = new StringBuilder(paragraphJustifiedBuilder.toString().trim());

            spacesToFill = this.getLimit() - paragraphJustifiedBuilder.length();

            if (spacesToFill > 0) {
                outerPadding = String.format("%" + spacesToFill + "s", "");
            }
            paragraphJustifiedBuilder = paragraphJustifiedBuilder.insert(0, outerPadding);
        }
        return paragraphJustifiedBuilder.toString();
    }

    public int calculateRemaining(String line) {
        int lineSizeDifference = 0;
        if (line.contains(LINE_BREAK)) {
            lineSizeDifference = 1;
        }
        return this.getLimit() - (line.length() - lineSizeDifference);
    }

}
