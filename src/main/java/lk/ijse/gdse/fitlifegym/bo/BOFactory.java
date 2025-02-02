package lk.ijse.gdse.fitlifegym.bo;

import lk.ijse.gdse.fitlifegym.bo.custom.impl.*;
import lk.ijse.gdse.fitlifegym.dao.DAOFactory;
import lk.ijse.gdse.fitlifegym.dao.SuperDAO;
import lk.ijse.gdse.fitlifegym.dao.custom.impl.*;

public class BOFactory{

    private static BOFactory boFactory;

    private BOFactory(){


    }

    public static BOFactory getInstance(){

        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;

    }

    public enum BOType{

        ADMIN,ATTENDANCE,BOOKING,CLASS,DASHBOARD,DIET_PLAN,EMPLOYEE,EQUIPMENT,EQUIPMENT_SUPPLY,MEMBER,PAYMENT,PAYMENT_PLAN,SESSION,SUPPLEMENT,SUPPLEMENT_SUPPLY,SUPPLIER,WORK_OUT_EQUIP,WORK_OUT_PLAN

    }

    public SuperBO getBO(BOType boType){

        switch (boType){
            case ADMIN : return new LoginBOImpl();
            case ATTENDANCE : return new AttendanceBOImpl();
            case BOOKING : return new BookingBOImpl();
            case CLASS : return new ClassBOImpl();
            case DASHBOARD : return new DashBoardBOImpl();
            case DIET_PLAN : return new DietPlanBOImpl();
            case EMPLOYEE : return new EmployeeB0Impl();
            case EQUIPMENT : return new EquipmentBOImpl();
            case EQUIPMENT_SUPPLY: return new EquipmentSupplyBOImpl();
            case MEMBER : return new MemberBOImpl();
            case PAYMENT : return new PaymentBOImpl();
            case PAYMENT_PLAN : return new PaymentPlanBOImpl();
            case SESSION : return new SessionBOImpl();
            case SUPPLEMENT : return new SupplementBOImpl();
            case SUPPLEMENT_SUPPLY : return new SupplimentSupplyBOImpl();
            case SUPPLIER : return new SupplierBOImpl();
            case WORK_OUT_EQUIP : return new WorkOutEquipBOImpl();
            case WORK_OUT_PLAN : return new WorkOutPlanBOImpl();
            default : return null;

        }

    }



}
