package luccasdev.idwall.desafio.crawler.controller;

import luccasdev.idwall.desafio.crawler.errors.CustomException;
import luccasdev.idwall.desafio.crawler.model.RedditThread;
import luccasdev.idwall.desafio.crawler.service.RedditThreadService;
import luccasdev.idwall.desafio.crawler.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/thread")
public class RedditThreadController {

    private final RedditThreadService redditThreadService;

    public RedditThreadController(RedditThreadService redditThreadService) {
        this.redditThreadService = redditThreadService;
    }

    @GetMapping("/hot")
    public List<RedditThread> findHotRedditThreads(@RequestParam("subreddits") String subreddits) {
        if (subreddits.isEmpty())
            throw new CustomException(MessageUtil.EMPTY_SUBREDDIT_MESSAGE, HttpStatus.BAD_REQUEST);

        return this.redditThreadService.findHotRedditThreadsBySubreddits(Arrays.asList(subreddits.split(";")));
    }
}
