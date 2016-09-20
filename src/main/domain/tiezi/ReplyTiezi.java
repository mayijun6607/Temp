package main.domain.tiezi;

/**
 * Created by Mayijun on 2016/9/12.
 */
public class ReplyTiezi {
    private String replyUsername;
    private int tieziId;
    private String tieziReply;
    private String replyTime;
    private int floorId;
    private int authId;

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public ReplyTiezi(){

    }
    public ReplyTiezi(String replyUsername,int tieziId,String tieziReply,String replyTime,int floorId,int authId){
        this.replyUsername=replyUsername;
        this.tieziId=tieziId;
        this.tieziReply=tieziReply;
        this.replyTime=replyTime;
        this.floorId=floorId;
        this.authId=authId;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }

    public int getTieziId() {
        return tieziId;
    }

    public void setTieziId(int tieziId) {
        this.tieziId = tieziId;
    }

    public String getTieziReply() {
        return tieziReply;
    }

    public void setTieziReply(String tieziReply) {
        this.tieziReply = tieziReply;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
