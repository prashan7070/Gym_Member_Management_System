package lk.ijse.gdse.fitlifegym.dao.custom.impl;

import lk.ijse.gdse.fitlifegym.dao.custom.MemberDAO;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.dao.SQLUtil;
import lk.ijse.gdse.fitlifegym.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO {


    public String generateId() throws SQLException {

        ResultSet rst = SQLUtil.execute("SELECT memberId FROM member ORDER BY memberId DESC LIMIT 1");

        if (rst.next()){
            return rst.getString(1);

        }

        return null;
    }


    public boolean save(Member member) throws SQLException {
        return SQLUtil.execute(
                "insert into member values (?,?,?,?,?,?,?)",
                member.getMemberId(),
                member.getName(),
                member.getAge(),
                member.getAddress(),
                member.getJoinDate(),
                member.getEmail(),
                member.getContactInfo()
        );
    }

    public ArrayList<Member> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM member");

        ArrayList<Member> members = new ArrayList<>();

        while (rst.next()) {
            Member member = new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
            members.add(member);
        }
        return members;
    }

    public boolean delete(String memberId) throws SQLException {
        return SQLUtil.execute("delete from member where memberId=?",memberId );
    }

    public boolean update(Member member) throws SQLException {
        return SQLUtil.execute(
                "update member set name=?, age=?, address=?, joinDate=?, email=?, contactInfo=? where memberId=?",
                member.getName(),
                member.getAge(),
                member.getAddress(),
                member.getJoinDate(),
                member.getEmail(),
                member.getContactInfo(),
                member.getMemberId()
        );
    }

    public Member getMemberEntityById(String memberId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT memberId , name , age , address , joinDate , email , contactInfo FROM member WHERE memberId = ? ",memberId);

        if (rst.next()){
            Member member = new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            );

            return member;
        }

        return null;

    }



}
