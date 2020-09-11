package luccasdev.idwall.desafio.crawler.service;

import luccasdev.idwall.desafio.crawler.errors.CustomException;
import luccasdev.idwall.desafio.crawler.model.RedditThread;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RedditThreadService {

    private static final String REDDIT_URL = "https://old.reddit.com";
    private static final String SUBREDDIT_PATH= "/r/";
    private static final Integer PAGINATION_LIMIT = 50;
    private static final Integer HOT_THREAD_POINTS = 5000;
    private static final String SUBREDDIT_SEPARATOR = ";";
    private static final String EMPTY_SUBREDDIT_MESSAGE = "Você deve informar ao menos um subreddit.";

    public List<RedditThread> findHotRedditThreadsBySubreddits(String subreddits) {
        if (Strings.isEmpty(subreddits))
            throw new CustomException(EMPTY_SUBREDDIT_MESSAGE, HttpStatus.BAD_REQUEST);

        List<String> subredditList = Arrays.asList(subreddits.split(SUBREDDIT_SEPARATOR));
        List<RedditThread> hotThreadList = new ArrayList<>();

        subredditList.forEach(subreddit -> {
            try {
                List<Element> allThreadElements = this.findAllThreadElements(subreddit);
                List<Element> hotThreadElements = this.findHotThreadElements(allThreadElements);
                hotThreadElements.forEach(hotThreadElement -> hotThreadList.add(
                        RedditThread.builder()
                                .subreddit(subreddit)
                                .title(this.getThreadTitleFromElement(hotThreadElement))
                                .link(this.getThreadLinkFromElement(hotThreadElement))
                                .commentLink(this.getThreadLinkFromElement(hotThreadElement))
                                .upVotes(this.getTotalOfUpVotesByElement(hotThreadElement))
                                .build()
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return hotThreadList;
    }

    public List<Element> findAllThreadElements(String subreddit) throws IOException {
        Document subredditDocument = Jsoup.connect(REDDIT_URL + SUBREDDIT_PATH + subreddit + "?limit=" + PAGINATION_LIMIT).get();
        return subredditDocument.getElementById("siteTable").children().select("div[class*=thing]");
    }

    private List<Element> findHotThreadElements(List<Element> threadElements) {
        List<Element> hotThreadElementList = new ArrayList<>();
        threadElements.forEach(threadElement -> {
            int totalUpVotes = this.getTotalOfUpVotesByElement(threadElement);
            if (totalUpVotes >= HOT_THREAD_POINTS) {
                hotThreadElementList.add(threadElement);
            }
        });
        return hotThreadElementList;
    }

    private int getTotalOfUpVotesByElement(Element threadElement) {
        String totalUpVotes = threadElement.children().select("div[class=score unvoted]").get(0).attr("title");
        if (Strings.isEmpty(totalUpVotes)) {
            return 0;
        }
        return Integer.parseInt(totalUpVotes);
    }

    private String getThreadTitleFromElement(Element threadElement) {
        return threadElement.selectFirst("a.title").text();
    }

    private String getThreadLinkFromElement(Element threadElement) {
        return REDDIT_URL + threadElement.attr("data-permalink");
    }

}
