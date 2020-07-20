package sps1101.servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sps1101.data.Post;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/content")
public class ContentServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Uncomment this and run it to change what is on the daily content.  Will have to comment out the bottom DatastoreService.
        // Entity postEntity = new Entity("Content");
        // postEntity.setProperty("daily", "This is where the daily content will be stored!");

        // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        // datastore.put(postEntity);

        //Prints the daily content out.
        Query query = new Query("Content");
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);
        String cont = "";

        //Adds each comment on the database to the client.
        for (Entity entity : results.asIterable()) {
                cont = (String) entity.getProperty("daily");
        }

        response.setContentType("application/json");
        String json = new Gson().toJson(cont);
        response.getWriter().println(json);
    }
}