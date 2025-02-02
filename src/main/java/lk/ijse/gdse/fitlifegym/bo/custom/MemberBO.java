package lk.ijse.gdse.fitlifegym.bo.custom;

import lk.ijse.gdse.fitlifegym.bo.SuperBO;
import lk.ijse.gdse.fitlifegym.dto.MemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {

    String generateId() throws SQLException;

    ArrayList<MemberDTO> getAll() throws SQLException;

    boolean save(MemberDTO memberDTO) throws SQLException;

    boolean delete(String memberId) throws SQLException;

    boolean update(MemberDTO memberDTO) throws SQLException;


}
