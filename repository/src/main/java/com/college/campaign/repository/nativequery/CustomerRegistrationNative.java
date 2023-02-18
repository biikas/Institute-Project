package com.college.campaign.repository.nativequery;

import com.college.campaign.dto.QueryCriteria;
import com.college.campaign.dto.RegistrationReportDetail;
import com.college.campaign.repository.Util.FieldQueryParameter;
import com.college.campaign.repository.Util.QueryFormatter;
import com.college.campaign.repository.Util.QueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author <krishna.pandey@college.com>
 */
@Slf4j
@Component
public class CustomerRegistrationNative {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private QueryHelper queryHelper;


    public List<RegistrationReportDetail> fetchRegistrationCampaignReport(List<FieldQueryParameter> search, PageRequest pageRequest) {

        QueryCriteria queryCriteria = buildQueryCriteria(search);

        String sqlQuery = "SELECT "
                + " C.PROMO_CODE as promoCode,"
                + " C.TITLE as campaignName,"
                + " RL.RECORDED_DATE as recordedDate,"
                + " RL.USERNAME as username,"
                + " RL.REGISTRATION_DATE as registrationDate,"
                + " OT.TRANSACTION_AMOUNT AS txnAmount,"
                + " OT.TXN_STATUS AS txnStatus,"
                + " OM.NAME AS offerType"
                + " FROM REGISTRATION_CAMPAIGN_USER RL"
                + " JOIN CAMPAIGN C ON (C.ID = RL.CAMPAIGN_ID) "
                + " LEFT JOIN OFFER_TRANSACTION OT ON (OT.CAMPAIGN_ID = RL.CAMPAIGN_ID AND RL.USERNAME = OT.MOBILE_NUMBER) "
                + " LEFT JOIN CAMPAIGN_OFFER CO ON (CO.ID = OT.CAMPAIGN_OFFER_ID) "
                + " LEFT JOIN OFFER_MODE OM ON (OM.ID = CO.OFFER_MODE_ID) "
                + " WHERE C.EVENT_TYPE = 'REGISTRATION' "
                + queryCriteria.getWhereClause();


        log.info("Sql query : {}", sqlQuery);

        Query query = em.createNativeQuery(sqlQuery);


        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(RegistrationReportDetail.class)).list();

        List<RegistrationReportDetail> object = query.getResultList();
        log.debug("Object query result: {}", object);
        return object;
    }

    private QueryCriteria buildQueryCriteria(List<FieldQueryParameter> search) {
        Map<String, Object> filterMap = queryHelper.convertToQueryMap(search);

        QueryCriteria queryCriteria = buildWhereClause(filterMap);

        return queryCriteria;
    }

    public QueryCriteria buildWhereClause(Map<String, Object> filterMap) {

        String whereClause = "";

        if (filterMap.containsKey("username") && ((String) filterMap.get("username")).length() > 0) {
            whereClause += " AND RL.USERNAME=" + "'" + QueryFormatter.escapeString((String) filterMap.get("username")) + "'";
        }

        if (filterMap.containsKey("promoCode") && ((String) filterMap.get("promoCode")).length() > 0) {
            whereClause += " AND C.PROMO_CODE=" + "'" + QueryFormatter.escapeString((String) filterMap.get("promoCode")) + "'";
        }

        if (filterMap.containsKey("fromDate") && filterMap.containsKey("toDate")) {
            whereClause += " AND RL.REGISTRATION_DATE BETWEEN " + "'" + ((String) filterMap.get("fromDate")) + "'";
            whereClause += " AND " + "'" + ((String) filterMap.get("toDate")) + "'";
        }

        QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setWhereClause(whereClause);

        return queryCriteria;
    }

    public long fetchRegistrationCampaignReportCount(List<FieldQueryParameter> search) {
        QueryCriteria queryCriteria = buildQueryCriteria(search);

        String sql = "SELECT COUNT(1) "
                + " FROM REGISTRATION_CAMPAIGN_USER RL"
                + " JOIN CAMPAIGN C ON (C.ID = RL.CAMPAIGN_ID) "
                + " LEFT JOIN OFFER_TRANSACTION OT ON (OT.CAMPAIGN_ID = RL.CAMPAIGN_ID AND RL.USERNAME = OT.MOBILE_NUMBER) "
                + " LEFT JOIN CAMPAIGN_OFFER CO ON (CO.ID = OT.CAMPAIGN_OFFER_ID) "
                + " LEFT JOIN OFFER_MODE OM ON (OM.ID = CO.OFFER_MODE_ID) "
                + " WHERE C.EVENT_TYPE = 'REGISTRATION' "
                + queryCriteria.getWhereClause();

        Query query = em.createNativeQuery(sql);
        log.info("Sql query : {}", sql);

        BigInteger count = (BigInteger) query.getSingleResult();
        return count.intValue();
    }
}
