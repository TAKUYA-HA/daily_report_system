package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Favorite;

public class FavoriteConverter {

    public static Favorite toModel(FavoriteView fv) {
        return new Favorite(
                fv.getId(),
                ReportConverter.toModel(fv.getReport()),
                EmployeeConverter.toModel(fv.getEmployee()));
    }

    public static FavoriteView toView(Favorite f) {

        if (f == null) {
            return null;
        }

        return new FavoriteView(
                f.getId(),
                ReportConverter.toView(f.getReport()),
                EmployeeConverter.toView(f.getEmployee()));
    }

    public static List<FavoriteView> toViewList(List<Favorite> list) {
        List<FavoriteView> evs = new ArrayList<>();

        for (Favorite f : list) {
            evs.add(toView(f));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Favorite f, FavoriteView fv) {
        f.setId(fv.getId());
        f.setReport(ReportConverter.toModel(fv.getReport()));
        f.setEmployee(EmployeeConverter.toModel(fv.getEmployee()));
    }
}
