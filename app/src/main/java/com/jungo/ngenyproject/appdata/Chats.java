package com.jungo.ngenyproject.appdata;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Chat")
public class Chats {
    @DatabaseField(generatedId = true)
    private int idChat;
    @DatabaseField(dataType = DataType.STRING)
    private String auther;
    @DatabaseField(dataType = DataType.STRING)
    private String me;
    @DatabaseField(dataType = DataType.STRING)
    private String text;

    public Chats() {
    }

    public Chats(String auther, String me, String text) {
        this.auther = auther;
        this.me = me;
        this.text = text;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
