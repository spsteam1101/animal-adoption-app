/*
*This class is in charge of each Post object.
*/
package sps1101.data;
import java.util.ArrayList;
import java.util.List;

public class Post  {
    private String name;
    private String post;
    private String page;
    //Later add Comment class

    public Post(String n, String p, String pa)  {
        name = n;
        post =p;
        page = pa;
    }

    public void setName(String n)  {
        name = n;;
    }

    public void setPost(String p)  {
        post = p;
    }

    public void setPage(String n)  {
        page = n;;
    }

    public String getName()  {
        return name;
    }

    public String getPost()  {
        return post;
    }

    public String getPage()  {
        return page;
    }

}