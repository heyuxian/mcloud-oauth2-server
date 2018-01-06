package me.javaroad.mcloud.oauth.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author heyx
 */
@Getter
@Setter
@Embeddable
public class Company {

    // TODO Replace image fields as file ID when file storage is completed.
    @Size(max = 50)
    private String corpName;
    // 营业执照号
    @Size(max = 100)
    private String corpLicenseNumber;
    // 营业执照扫描件
    @Size(max = 255)
    private String corpLicenseImg;
    // 税务登记扫描件
    @Size(max = 255)
    private String taxImg;
    // 法人名称
    @Size(max = 20)
    private String legalPersonName;
    // 法人身份证扫描件
    @Size(max = 255)
    private String legalPersonIdCardImg;
}
