package com.example.jialijiang.mymeterialdesign.entity;

/**
 * Created by zhuxingxing on 17/11/27.
 */

public class MemberEntity {
    /**
     * username : chenc
     * realname : 陈超
     * company : {"company_name":"杭州总部","company_nickname":"杭州",
     * "company_id":"4028d88142a728d10142a72ca7d50000"}
     */

    private String username;
    private String realname;
    private CompanyEntity company;

    public String getUsername() { return username;}

    public String getRealname() { return realname;}

    public CompanyEntity getCompany() { return company;}
}
