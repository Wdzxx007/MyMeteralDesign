package com.example.jialijiang.mymeterialdesign.Entity;

import java.util.List;

/**
 * Created by zhuxingxing on 17/11/27.
 */

public class CompanyEntity {
    /**
     * company_id : dddddddddd
     * company_name : 杭州总部
     * company_nickname : 杭州
     * sub_groups[]:分公司
     */

    private String company_id;
    private String company_name;
    private String company_nickname;
    private List<CompanyEntity> sub_groups;

    public CompanyEntity( String s, String s1, String s2,
            List<CompanyEntity> companyEntities ) {
        this.company_id = s;
        this.company_name = s1;
        this.company_nickname = s2;
        this.sub_groups = companyEntities;
    }

    public CompanyEntity( String s, String s1, String s2 ) {
        this.company_id = s;
        this.company_name = s1;
        this.company_nickname = s2;
    }

    public String getCompany_id() { return company_id;}

    public String getCompany_name() { return company_name;}

    public String getCompany_nickname() { return company_nickname;}

    public List<CompanyEntity> getSub_groups() {
        return sub_groups;
    }

    @Override
    public String toString() {
        return company_name;
    }
}
