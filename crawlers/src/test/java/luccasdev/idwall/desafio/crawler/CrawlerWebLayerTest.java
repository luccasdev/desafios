package luccasdev.idwall.desafio.crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import luccasdev.idwall.desafio.crawler.controller.RedditThreadController;
import luccasdev.idwall.desafio.crawler.model.RedditThread;
import luccasdev.idwall.desafio.crawler.service.RedditThreadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RedditThreadController.class)
@ActiveProfiles("test")
public class CrawlerWebLayerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    RedditThreadService redditThreadService;

    private static final String REQUEST_PATH = "/thread";

    @Test
    public void getHotRedditThreadsReturnsOkWithThreads() throws Exception{
        List<RedditThread> redditThreadListMock = new ArrayList<>();

        List<String> subredditMock = List.of("cats");

        RedditThread redditThreadTest = RedditThread.builder()
                .title("Gigi and her tiny tongue")
                .subreddit("cats")
                .upVotes(9671)
                .link("https://old.reddit.com/r/cats/comments/iq0cel/gigi_and_her_tiny_tongue/")
                .commentLink("https://old.reddit.com/r/cats/comments/iq0cel/gigi_and_her_tiny_tongue/")
                .build();

        redditThreadListMock.add(redditThreadTest);

        Mockito.when(redditThreadService.findHotRedditThreadsBySubreddits(subredditMock)).thenReturn(redditThreadListMock);


        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_PATH + "/hot")
                .param("subreddits", "cats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Gigi and her tiny tongue"));
    }

}
