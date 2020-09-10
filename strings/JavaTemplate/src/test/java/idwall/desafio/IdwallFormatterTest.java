package idwall.desafio;

import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdwallFormatterTest {

    private static final String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters."
            + "\n\n"+
            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
    private static final Integer DEFAULT_LIMIT = 40;

    @Test
    public void whenTextIsEmptyReturnsOutputEmpty(){
        String emptyText = "";

        StringFormatter stringFormatter = new IdwallFormatter();
        String output = stringFormatter.format(emptyText);

        assertTrue(output.isEmpty());
    }

    @Test
    public void formatTextAndReturnsFormattedOutput() {
        String textFormatted =
                "In the beginning God created the heavens\n" +
                        "and the earth. Now the earth was\n" +
                        "formless and empty, darkness was over\n" +
                        "the surface of the deep, and the Spirit\n" +
                        "of God was hovering over the waters.\n" +
                        "\n" +
                        "And God said, \"Let there be light,\" and\n" +
                        "there was light. God saw that the light\n" +
                        "was good, and he separated the light\n" +
                        "from the darkness. God called the light\n" +
                        "\"day,\" and the darkness he called\n" +
                        "\"night.\" And there was evening, and\n" +
                        "there was morning - the first day.";

        StringFormatter stringFormatter = new IdwallFormatter();
        stringFormatter.setLimit(DEFAULT_LIMIT);
        stringFormatter.setJustify(false);

        String outputFormatted = stringFormatter.format(DEFAULT_INPUT_TEXT);

        assertEquals(textFormatted, outputFormatted);

    }

    @Test
    public void formatTextAndReturnsFormattedAndJustifiedOutput() {
        String textJustifiedFormatted =
                "In the beginning God created the heavens\n" +
                        "and   the  earth.  Now  the  earth  was\n" +
                        "formless  and  empty,  darkness was over\n" +
                        "the  surface of the deep, and the Spirit\n" +
                        "of  God  was  hovering  over the waters.\n" +
                        "\n" +
                        "And  God said, \"Let there be light,\" and\n" +
                        "there  was light. God saw that the light\n" +
                        "was  good,  and  he  separated the light\n" +
                        "from  the darkness. God called the light\n" +
                        "\"day,\"   and  the  darkness  he  called\n" +
                        "\"night.\"  And  there  was  evening,  and\n" +
                        "there  was  morning  -  the  first  day.";

        StringFormatter stringFormatter = new IdwallFormatter();
        stringFormatter.setLimit(DEFAULT_LIMIT);
        stringFormatter.setJustify(true);

        String outputFormatted = stringFormatter.format(DEFAULT_INPUT_TEXT);

        assertEquals(textJustifiedFormatted, outputFormatted);
    }

}
