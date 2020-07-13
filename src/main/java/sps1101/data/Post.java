/*
*This class is in charge of each Post object.
*/
package com.google.sps1101.data;
import java.util.ArrayList;
import java.util.List;

public class Post  {
    private String name;
    private String post;
    //Later add Comment class

    public Post(String n, String p)  {
        name = n;
        post =p;
    }

    public void setName(String n)  {
        name = n;;
    }

    public void setPost(String p)  {
        post = p;
    }

    public String getName()  {
        return name;
    }

    public String getPost()  {
        return post;
    }

}