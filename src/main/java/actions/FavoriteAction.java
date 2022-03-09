package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.FavoriteService;

public class FavoriteAction extends ActionBase {

    private FavoriteService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new FavoriteService();
        invoke();
        service.close();
    }

    public void favorite()  throws ServletException, IOException {
        int repId = toNumber(getRequestParam(AttributeConst.EMP_ID));
        EmployeeView loginEmployee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        int empId = loginEmployee.getId();

        service.doFavorite(repId, empId);

        ForwardConst command;
        if (getRequestParam(
                AttributeConst.FAV_RD_CMD).equals(ForwardConst.CMD_INDEX.getValue())) {

            command = ForwardConst.CMD_INDEX;
        } else {
            command = ForwardConst.CMD_SHOW;
        }

        putSessionScope(AttributeConst.REP_ID, repId);

        redirect(ForwardConst.RD_REP, command);
    }
}
