package buzz.model;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;

import buzz.Tools;

public class GithubModel {

    public static List<PullRequest> getTeamPullRequests() {

        GitHubClient client = getGithubClient();

        PullRequestService service = new PullRequestService(client);

        RepositoryId repositoryId = new RepositoryId(//
            Tools.getLocalProperties().getProperty("github_repository_owner"),//
            Tools.getLocalProperties().getProperty("github_repository_name")//
        );

        List<PullRequest> openPullRequests = null;

        try {
            openPullRequests = service.getPullRequests(repositoryId, "open");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return openPullRequests;
    }


    private static GitHubClient getGithubClient() {

        GitHubClient client = new GitHubClient();

        client.setCredentials(//
            Tools.getLocalProperties().getProperty("github_user"),//
            Tools.getLocalProperties().getProperty("github_password")//
        );

        return client;
    }

}
