package idwall.desafio;

import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdwallFormatterTest {

    @Test
    public void whenTextIsEmptyReturnsOutputEmpty(){

        String emptyText = "";

        StringFormatter sf = new IdwallFormatter();

        String output = sf.format(emptyText);

        assertTrue(output.isEmpty());
    }

}
