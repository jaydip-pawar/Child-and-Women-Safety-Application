package com.example.womensecurity.mDataBase;

public class Constants {
    //columns
    static final String ROW_ID="id";
    static final String NAME="name";
    static final String NUMBER="number";

    //DB Properties
    static final String DB_NAME="contact_details.db";
    static final String TB_NAME="contacts";
    static final int DB_VERSION=1;

    //Create TB Statement
    static final String CREATE_TB="create table contacts (id integer primary key autoincrement, name text not null, number text not null)";

    //Drop TB Statement
    static final String DROP_TB="DROP TABLE IF EXIST"+TB_NAME;
}
