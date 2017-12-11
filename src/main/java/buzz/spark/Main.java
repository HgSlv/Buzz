package buzz.spark;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Main {

    public static void main (String[] args) {

        exception(Exception.class, (e, rq, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(8888);

        get ("/", (req, res) -> TemplateController.renderStatusMonitor(req));

        get ("/jenkins", (req, res) -> TemplateController.renderStatusMonitor(req));

        get ("/pull-requests", (req, res) -> TemplateController.renderPullRequests(req));


        after((req, res) -> {
            if (res.body() == null) {
                res.body(TemplateController.renderStatusMonitor(req));
            }
        });

        
    }

}
