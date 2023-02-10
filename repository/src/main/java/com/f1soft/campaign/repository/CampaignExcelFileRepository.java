package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.CampaignExcelFiles;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Nitesh Poudel
 */
public interface CampaignExcelFileRepository extends BaseRepository<CampaignExcelFiles> {

    @Query("select t from CampaignExcelFiles t where t.processStatus=0")
    List<CampaignExcelFiles> findUnprocessedFile();

    @Query("select t from CampaignExcelFiles t where t.campaign.id =:id")
    CampaignExcelFiles getByCampaignId(Long id);
}
