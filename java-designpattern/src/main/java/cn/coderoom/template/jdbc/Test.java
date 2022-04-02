package cn.coderoom.template.jdbc;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }

}
