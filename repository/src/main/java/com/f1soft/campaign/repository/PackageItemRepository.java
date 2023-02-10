package com.f1soft.campaign.repository;

import com.f1soft.campaign.entities.model.PackageItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Bikash Shah
 */
public interface PackageItemRepository extends BaseRepository<PackageItem>{
    @Query("select t from PackageItem t where t.offerPackage.id =: id")
    List<PackageItem> getPackageItemBypackageId(Long id);

}
