package buzz.controllers;

import java.util.HashMap;
import java.util.Map;

import buzz.model.GithubModel;
import spark.template.velocity.*;

import spark.ModelAndView;
import spark.Request;

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

    private static String renderTemplate(String template, Map<String, Object> model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }

}
