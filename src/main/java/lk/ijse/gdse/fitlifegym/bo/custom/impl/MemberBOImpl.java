package lk.ijse.gdse.fitlifegym.bo.custom.impl;

import lk.ijse.gdse.fitlifegym.bo.custom.ClassBO;
import lk.ijse.gdse.fitlifegym.bo.custom.MemberBO;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.custom.MemberDAO;
import lk.ijse.gdse.fitlifegym.dto.ClassDTO;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;
import lk.ijse.gdse.fitlifegym.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBOImpl implements MemberBO {

    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);

    @Override
    public String generateId() throws SQLException {
        String id = memberDAO.generateId();

        if (id!=null){

            String subString = id.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("M%03d", newIndex);

        }

        return "M001";
    }

    @Override
    public ArrayList<MemberDTO> getAll() throws SQLException {
        ArrayList<Member> members = memberDAO.getAll();
        ArrayList<MemberDTO> memberDTOS = new ArrayList<>();

        for (Member member : members){
            memberDTOS.add(new MemberDTO(member.getMemberId(),member.getName(),member.getAge(),member.getAddress(),member.getJoinDate(),member.getEmail(),member.getContactInfo()));

        }

        return memberDTOS;
    }

    @Override
    public boolean save(MemberDTO memberDTO) throws SQLException {
        return memberDAO.save(new Member(memberDTO.getMemberId(),memberDTO.getName(),memberDTO.getAge(),memberDTO.getAddress(),memberDTO.getJoinDate(),memberDTO.getEmail(),memberDTO.getContactInfo()));

    }

    @Override
    public boolean delete(String memberId) throws SQLException {
        return memberDAO.delete(memberId);
    }

    @Override
    public boolean update(MemberDTO memberDTO) throws SQLException {
        return memberDAO.update(new Member(memberDTO.getMemberId(),memberDTO.getName(),memberDTO.getAge(),memberDTO.getAddress(),memberDTO.getJoinDate(),memberDTO.getEmail(),memberDTO.getContactInfo()));
    }

    @Override
    public MemberDTO getMemberEntityById(String memberId) throws SQLException {
        Member member = memberDAO.getMemberEntityById(memberId);
        return new MemberDTO(member.getMemberId(),member.getName(),member.getAge(),member.getAddress(),member.getJoinDate(),member.getEmail(),member.getContactInfo());
    }
}
