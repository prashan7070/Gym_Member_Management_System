package lk.ijse.gdse.fitlifegym.dao;

import lk.ijse.gdse.fitlifegym.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){

        if (daoFactory==null){
            daoFactory = new DAOFactory();
        }

        return daoFactory;
    }

    public enum DAOType{

        ADMIN,ATTENDANCE,BOOKING,CLASS,DASHBOARD,DIET_PLAN,EMPLOYEE,EQUIPMENT,EQUIPMENT_SUPPLY,MEMBER,PAYMENT,PAYMENT_PLAN,QUERY,SESSION,SUPPLEMENT,SUPPLEMENT_SUPPLY,SUPPLIER,WORK_OUT_EQUIP,WORK_OUT_PLAN

    }

    public SuperDAO getDAO(DAOType daoType){

        switch (daoType){
            case ADMIN : return new AdminDAOImpl();
            case ATTENDANCE : return new AttendanceDAOImpl();
            case BOOKING : return new BookingDAOImpl();
            case CLASS : return new ClassDAOImpl();
            case DASHBOARD : return new DashbordDAOImpl();
            case DIET_PLAN : return new DietPlanDAOImpl();
            case EMPLOYEE : return new EmployeeDAOImpl();
            case EQUIPMENT : return new EquipmentDAOImpl();
            case EQUIPMENT_SUPPLY: return new EquipmentSupplyDAOImpl();
            case MEMBER : return new MemberDAOImpl();
            case PAYMENT : return new PaymentDAOImpl();
            case PAYMENT_PLAN : return new PaymentPlanDAOImpl();
            case QUERY : return new QueryDAOImpl();
            case SESSION : return new SessionDAOImpl();
            case SUPPLEMENT : return new SupplementDAOImpl();
            case SUPPLEMENT_SUPPLY : return new SupplementSupplyDAOImpl();
            case SUPPLIER : return new SupplierDAOImpl();
            case WORK_OUT_EQUIP : return new WorkOutEquipDAOImpl();
            case WORK_OUT_PLAN : return new WorkOutPlanDAOImpl();
            default : return null;

        }

    }


}
