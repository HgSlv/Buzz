package buzz.model;

import buzz.Tools;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.greenhopper.GreenHopperClient;
import net.rcarz.jiraclient.greenhopper.RapidView;

public class JiraModel {

    public static String getTickets() {

        Integer boardId = Integer.parseInt(Tools.getLocalProperties().getProperty("jira_board_id"));
        JiraClient jiraClient = getJiraRestClient();

        GreenHopperClient greenHopperClient = new GreenHopperClient(jiraClient);

        RapidView board = null;

        try {
            board = greenHopperClient.getRapidView(boardId);
            return board.getSprints().get(0).getName();
            
        } catch (JiraException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return  "";

    }

    private static JiraClient getJiraRestClient() {

        String jiraUrl = Tools.getLocalProperties().getProperty("jira_url");
        String jiraUser = Tools.getLocalProperties().getProperty("jira_user");
        String jiraPassword = Tools.getLocalProperties().getProperty("jira_password");

        BasicCredentials credentials = new BasicCredentials(jiraUser, jiraPassword);

        JiraClient jiraClient = new JiraClient(jiraUrl, credentials);

        return jiraClient;
    }

}