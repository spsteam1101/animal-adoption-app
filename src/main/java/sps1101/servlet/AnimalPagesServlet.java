// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

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
@WebServlet("/animal-data")
public class AnimalPagesServlet extends HttpServlet {

    private List<Post> posts;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    posts = new ArrayList<>();

   Query query = new Query("Post").addSort("timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    //Adds each comment on the database to the client.
    for (Entity entity : results.asIterable()) {
            String name = (String) entity.getProperty("name");
            String post = (String) entity.getProperty("post");
            String page = (String) entity.getProperty("animalPage");
            Post p = new Post(name, post, page);
            posts.add(p);
    }

    response.setContentType("application/json");
    String json = new Gson().toJson(posts);
    response.getWriter().println(json);
  }

   @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { 
        //Gets comment from client.
        String name = getParameter(request, "name-area", "");
        String post = getParameter(request, "post-area", "");
        String pageFinder = getParameter(request, "animalId", "");
        //Add animalID param for url link stuff
        long timestamp = System.currentTimeMillis();
      
        //Sets up datastore.
        Entity postEntity = new Entity("Post");
        postEntity.setProperty("name", name);
        postEntity.setProperty("post", post);
        postEntity.setProperty("animalPage", pageFinder);
        postEntity.setProperty("timestamp", timestamp);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(postEntity);

        //TODO  Set up comments either here or in another servlet.

        response.sendRedirect(pageFinder + ".html"); //Change to seperate link depending on what .html needs to be shown via if statement.
  }

   /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

}
