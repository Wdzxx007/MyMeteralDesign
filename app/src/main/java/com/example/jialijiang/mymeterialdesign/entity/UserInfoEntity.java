package com.example.jialijiang.mymeterialdesign.entity;

/**
 * Created by zhuxingxing on 17/11/27.
 */

public class UserInfoEntity {
    /**
     * user_info : {"username":"chenc","realname":"陈超","company":{"company_name":"杭州总部",
     * "company_nickname":"杭州","company_id":"4028d88142a728d10142a72ca7d50000"}}
     * access : {"access_token":"gAAAAABYL
     * --BfIn37NGGwdGgB5Or9SlaOJh4t37Aev7Vp_39Af2_3WFbQjUmCFLpwO6G7XeEJXYhuGjRnRVM7dNkjeaQUuSID9steywPgqh2bcPi-mlIHEvnrEkCJK-7MecAC5P79nle"}
     */

    private MemberEntity user_info;
    private AccessEntity access;

    public MemberEntity getMember() { return user_info;}

    public AccessEntity getAccess() { return access;}
}
