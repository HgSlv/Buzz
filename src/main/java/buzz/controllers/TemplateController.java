package buzz.controllers;

import java.util.HashMap;
import java.util.Map;

import buzz.model.GithubModel;
import buzz.model.JenkinsModel;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

public class TemplateController {

    public static String renderStatusMonitor(Request req) {

        String statusStr = req.queryParams("status");

        Map<String, Object> model = new HashMap<>();

        model.put("status", statusStr);
        model.put("wurst", "hi");

        return renderTemplate("templates/index2.vm", model);
    }

    public static String renderPullRequests(Request req) {
        Map<String, Object> model = new HashMap<>();
        
        model.put("pullRequests", GithubModel.getTeamPullRequests());
        return renderTemplate("templates/pull-requests.vm", model);
    }

    public static String renderJiraEsf(Request req) {
        Map<String, Object> model = new HashMap<>();

        model.put("esfJob", JenkinsModel.getEsfTests().toString());
        return renderTemplate("templates/esf.vm", model);
    }

    public static String renderJiraRats(Request req) {
        Map<String, Object> model = new HashMap<>();

        model.put("esfJob", JenkinsModel.getRats().toString());
        return renderTemplate("templates/rats.vm", model);
    }

    private static String renderTemplate(String template, Map<String, Object> model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }

}
