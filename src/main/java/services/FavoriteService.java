package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Employee;
import models.Favorite;
import models.Report;

public class FavoriteService extends ServiceBase {

    private FavoriteService service;
    @Override
    public Map<Integer, Long> count(List<ReportView> reports) {
        Map<Integer, Long> favCountsMap = new HashMap<>();
        for(ReportView rv : reports) {
            long cnt = countFavorite(ReportConverter.toModel(rv));
            favCountsMap.put(rv.getId(), cnt);
        }
        return favCountsMap;
    }

    public void doFavorite(int repId, int empId) {
        Report r = em.find(Report.class, repId);
        Employee e = em.find(Employee.class, empId);


        try {
            Favorite f = em.createNamedQuery(JpaConst.Q_FAV_GET_BY_REP_AND_EMP, Favorite.class)
                .setParameter(JpaConst.ENTITY_REP, r)
                .setParameter(JpaConst.ENTITY_EMP, e)
                .getSingleResult();

            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
        } catch (NoResultException nre) {
            em.getTransaction().begin();

            Favorite f = new Favorite();
            f.setEmployee(e);
            f.setReport(r);
            em.persist(f);

            em.getTransaction().commit();

        }
    }

}