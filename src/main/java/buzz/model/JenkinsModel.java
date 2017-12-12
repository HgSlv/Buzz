package buzz.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

import buzz.Tools;

public class JenkinsModel {
    
    public static JobWithDetails getEsfTests() {
        
        JenkinsServer jenkins = getJenkinsServer(//
                Tools.getLocalProperties().getProperty("jenkins_team_url"),//
                Tools.getLocalProperties().getProperty("jenkins_team_user"),//
                Tools.getLocalProperties().getProperty("jenkins_team_password")//
                );
        
        if (jenkins == null) {
            return null;
        }
        
        JobWithDetails job = getJobDetails(//
                jenkins,//
                Tools.getLocalProperties().getProperty("jenkins_team_esf_job_name")//
                );
        
        return job;
    }

    public static JobWithDetails getRats() {

        JenkinsServer jenkins = getJenkinsServer(//
                Tools.getLocalProperties().getProperty("jenkins_team_url"),//
                Tools.getLocalProperties().getProperty("jenkins_team_user"),//
                Tools.getLocalProperties().getProperty("jenkins_team_password")//
        );

        if (jenkins == null) {
            return null;
        }

        JobWithDetails job = getJobDetails(//
                jenkins,//
                Tools.getLocalProperties().getProperty("jenkins_rats_job_name")//
        );

        return job;
    }


    private static JenkinsServer getJenkinsServer(String jenkinsUrl, String jenkinsUser, String jenkinsPassword) {

        JenkinsServer jenkins = null;

        try {

            jenkins = new JenkinsServer(//
                    new URI(jenkinsUrl),//
                    jenkinsUser,//
                    jenkinsPassword//
            );

            return jenkins;
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    private static JobWithDetails getJobDetails(JenkinsServer jenkins, String jobName) {

        JobWithDetails job = null;

        try {

            Map<String, Job> jobs = jenkins.getJobs();

            job = jobs.get(jobName).details();

            return job;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return job;
    }

}
