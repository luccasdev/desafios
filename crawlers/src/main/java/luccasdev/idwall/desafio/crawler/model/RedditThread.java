package luccasdev.idwall.desafio.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedditThread {

    private String title;
    private String link;
    private String commentLink;
    private String subreddit;
    private Integer upVotes;

}
