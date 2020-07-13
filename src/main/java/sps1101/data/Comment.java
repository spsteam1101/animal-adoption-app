/*
*This class is in charge of each comment object.
* We will probably use this later on so I added it.
*/
package com.google.sps1101.data;
import java.util.ArrayList;
import java.util.List;


public class Comment  {
    private String name;
    private String comment;

    public void setName(String n)  {
        name = n;;
    }

    public void setComment(String c)  {
        comment = c;
    }

    public String getName()  {
        return name;
    }

    public String getComment()  {
        return comment;
    }
}