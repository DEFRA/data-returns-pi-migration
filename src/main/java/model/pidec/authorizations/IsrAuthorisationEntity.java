package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_authorisation", schema = "piedc", catalog = "piedc")
public class IsrAuthorisationEntity {
    private String authorisationid;
    private String supersededBy;
    private IsrAuthorisationTypeEntity isrAuthorisationtypeByAuthorisationtypeid;
    private IsrOperatorEntity isrOperatorByOperatorid;
    private IsrSiteEntity isrSiteBySiteid;
    private IsrAreaEntity isrAreaByAgencyareaid;
    private IsrAsrFullCodesEntity isrAsrFullCodesByAsrFullCode;

    @Id
    @Column(name = "authorisationid", nullable = false, length = 32)
    public String getAuthorisationid() {
        return authorisationid;
    }

    public void setAuthorisationid(String authorisationid) {
        this.authorisationid = authorisationid;
    }

    @Basic
    @Column(name = "superseded_by", nullable = true, length = 32)
    public String getSupersededBy() {
        return supersededBy;
    }

    public void setSupersededBy(String supersededBy) {
        this.supersededBy = supersededBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAuthorisationEntity that = (IsrAuthorisationEntity) o;

        if (authorisationid != null ? !authorisationid.equals(that.authorisationid) : that.authorisationid != null)
            return false;
        if (supersededBy != null ? !supersededBy.equals(that.supersededBy) : that.supersededBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorisationid != null ? authorisationid.hashCode() : 0;
        result = 31 * result + (supersededBy != null ? supersededBy.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "authorisationtypeid", referencedColumnName = "authorisationtypeid")
    public IsrAuthorisationTypeEntity getIsrAuthorisationtypeByAuthorisationtypeid() {
        return isrAuthorisationtypeByAuthorisationtypeid;
    }

    public void setIsrAuthorisationtypeByAuthorisationtypeid(IsrAuthorisationTypeEntity isrAuthorisationtypeByAuthorisationtypeid) {
        this.isrAuthorisationtypeByAuthorisationtypeid = isrAuthorisationtypeByAuthorisationtypeid;
    }

    @ManyToOne
    @JoinColumn(name = "operatorid", referencedColumnName = "operatorid")
    public IsrOperatorEntity getIsrOperatorByOperatorid() {
        return isrOperatorByOperatorid;
    }

    public void setIsrOperatorByOperatorid(IsrOperatorEntity isrOperatorByOperatorid) {
        this.isrOperatorByOperatorid = isrOperatorByOperatorid;
    }

    @ManyToOne
    @JoinColumn(name = "siteid", referencedColumnName = "siteid")
    public IsrSiteEntity getIsrSiteBySiteid() {
        return isrSiteBySiteid;
    }

    public void setIsrSiteBySiteid(IsrSiteEntity isrSiteBySiteid) {
        this.isrSiteBySiteid = isrSiteBySiteid;
    }

    @ManyToOne
    @JoinColumn(name = "agencyareaid", referencedColumnName = "areaid")
    public IsrAreaEntity getIsrAreaByAgencyareaid() {
        return isrAreaByAgencyareaid;
    }

    public void setIsrAreaByAgencyareaid(IsrAreaEntity isrAreaByAgencyareaid) {
        this.isrAreaByAgencyareaid = isrAreaByAgencyareaid;
    }

    @ManyToOne
    @JoinColumn(name = "asr_full_code", referencedColumnName = "asr_full_code")
    public IsrAsrFullCodesEntity getIsrAsrFullCodesByAsrFullCode() {
        return isrAsrFullCodesByAsrFullCode;
    }

    public void setIsrAsrFullCodesByAsrFullCode(IsrAsrFullCodesEntity isrAsrFullCodesByAsrFullCode) {
        this.isrAsrFullCodesByAsrFullCode = isrAsrFullCodesByAsrFullCode;
    }
}
