package idwall.desafio;

import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdwallFormatterTest {

    private final String text;
    private Integer limit;

    public IdwallFormatterTest() {
        this.text = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters."
                +
                " And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";;
        this.limit = 40;
    }

    @Test
    public void whenTextIsEmptyReturnsOutputEmpty(){

        String emptyText = "";

        StringFormatter sf = new IdwallFormatter();

        String output = sf.format(emptyText);

        assertTrue(output.isEmpty());
    }

    @Test
    public void formatTextAndReturnsFormattedOutput() {
        String textFormatted =
                "In the beginning God created the heavens\n" +
                "and the earth. Now the earth was\n" +
                "formless and empty, darkness was over\n" +
                "the surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters. And\n" +
                "God said, \"Let there be light,\" and\n" +
                "there was light. God saw that the light\n" +
                "was good, and he separated the light\n" +
                "from the darkness. God called the light\n" +
                "\"day,\" and the darkness he called\n" +
                "\"night.\" And there was evening, and\n" +
                "there was morning - the first day.";

        StringFormatter stringFormatter = new IdwallFormatter();

        stringFormatter.setLimit(this.limit);
        stringFormatter.setJustify(false);
        String outputFormatted = stringFormatter.format(this.text);

        assertEquals(textFormatted, outputFormatted);

    }

    @Test
    public void formatTextAndReturnsFormattedAndJustifiedOutput() {
        String textJustifiedFormatted =
                "In the beginning God created the heavens\n" +
                " and   the  earth.  Now  the  earth  was\n" +
                "formless  and  empty,  darkness was over\n" +
                "the  surface of the deep, and the Spirit\n" +
                "of God was hovering over the waters. And\n" +
                "God  said,  \"Let  there  be  light,\" and\n" +
                "there  was light. God saw that the light\n" +
                "was  good,  and  he  separated the light\n" +
                "from  the darkness. God called the light\n" +
                " \"day,\"   and  the  darkness  he  called\n" +
                "\"night.\"  And  there  was  evening,  and\n" +
                "there  was  morning  -  the  first  day.";

        StringFormatter stringFormatter = new IdwallFormatter();

        stringFormatter.setLimit(this.limit);
        stringFormatter.setJustify(true);
        String outputFormatted = stringFormatter.format(this.text);

        assertEquals(textJustifiedFormatted, outputFormatted);
    }

}
