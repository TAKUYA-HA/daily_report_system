package actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import constants.AttributeConst;
import constants.ForwardConst;
import services.FavoriteService;

public class FavoriteAction {

    public void process() throws ServletException, IOException {
        service = new FavoriteService();
        invoke();
        service.close();
    }

    public void favorite()  throws ServletException, IOException {
        int repId = toNumber(getRequestParam(AttributeConst.EMP_ID));
        int empId = getLoginEmployee().getId();

        service.doFavorite(repId, empId);

        Map<String, String> params = new HashMap<>();
        params.put(ForwardConst.CMD.getValue(), ForwardConst.CMD_INDEX.getValue());
        redirect(ForwardConst.RD_REP, params);
    }

}
