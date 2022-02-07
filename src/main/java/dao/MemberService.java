package dao;

import metier.Member;

public class MemberService {
    private MemberDao dao;
    private static final MemberService instance = new MemberService();
    private MemberService(){
        dao = new MemberDao();
    }

    public static MemberService getInstance(){
        return instance;
    }

    public int checkLogin(String username, String pass){
        /*
        Member member = dao.getMember(username);
        if(member == null){
            return MemberSet.NO_ID;
        }else{
            String passDb = member.getPass();
            if(passDb != null) passDb = passDb.trim();
            if(!pass.equals(passDb)){
                return MemberSet.NO_PWD;
            }else{
                return MemberSet.PASS;
            }

        }
        */
        return 1;
    }
}
