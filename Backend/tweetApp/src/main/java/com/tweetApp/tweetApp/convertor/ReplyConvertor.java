package com.tweetApp.tweetApp.convertor;



import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.tweetApp.tweetApp.model.Reply;

 

public class ReplyConvertor implements DynamoDBTypeConverter<String, List<Reply>> {
    
    @Override
    public String convert(List<Reply> reply) {
        String replyResult = "_";
        try {
            if (reply != null) {
                for(int i=0; i<reply.size(); i++) {
                    String replyArray = String.format("%s*%s*%s", reply.get(i).getUserName(), reply.get(i).getReplyDesc(),
                            reply.get(i).getDate());
                
                    replyResult = String.format("%s^%s", replyResult, replyArray);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return replyResult;
    }

 

     

 

    @Override
    public List<Reply> unconvert(String object) {
        
        List<Reply> replies = new ArrayList<Reply>();
        try {
            if (object != null && object.length() != 1) {
                String[] dataReply = object.replace("^", " ").split(" "); // ["123$123$123", "15$15"]
                for(int i=0; i<dataReply.length; i++) {
                    if(dataReply[i].trim().length() != 1) {
                    String[] data = dataReply[i].replace("*", " ").split(" "); // ["123", "123", "123"]
                        Reply reply = new Reply();
                        reply.setUserName(data[0].trim());
                        reply.setDate(data[2].trim());
                        reply.setReplyDesc(data[1].trim());

 

                        replies.add(reply);
                }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

 

     

 

        return replies;
    }

 

}
 










