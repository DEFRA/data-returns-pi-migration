package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_site", schema = "piedc", catalog = "piedc")
public class IsrSiteEntity {
    private int siteid;
    private String siteaddress;
    private String sitepostcode;
    private Integer easting;
    private Integer northing;
    private IsrCountyEntity isrCountyByCountyid;

    @Id
    @Column(name = "siteid", nullable = false, precision = 0)
    public int getSiteid() {
        return siteid;
    }

    public void setSiteid(int siteid) {
        this.siteid = siteid;
    }

    @Basic
    @Column(name = "siteaddress", nullable = true, length = -1)
    public String getSiteaddress() {
        return siteaddress;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }

    @Basic
    @Column(name = "sitepostcode", nullable = true, length = 20)
    public String getSitepostcode() {
        return sitepostcode;
    }

    public void setSitepostcode(String sitepostcode) {
        this.sitepostcode = sitepostcode;
    }

    @Basic
    @Column(name = "easting", nullable = true, precision = 0)
    public Integer getEasting() {
        return easting;
    }

    public void setEasting(Integer easting) {
        this.easting = easting;
    }

    @Basic
    @Column(name = "northing", nullable = true, precision = 0)
    public Integer getNorthing() {
        return northing;
    }

    public void setNorthing(Integer northing) {
        this.northing = northing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrSiteEntity that = (IsrSiteEntity) o;

        if (siteid != that.siteid) return false;
        if (siteaddress != null ? !siteaddress.equals(that.siteaddress) : that.siteaddress != null) return false;
        if (sitepostcode != null ? !sitepostcode.equals(that.sitepostcode) : that.sitepostcode != null) return false;
        if (easting != null ? !easting.equals(that.easting) : that.easting != null) return false;
        if (northing != null ? !northing.equals(that.northing) : that.northing != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteid;
        result = 31 * result + (siteaddress != null ? siteaddress.hashCode() : 0);
        result = 31 * result + (sitepostcode != null ? sitepostcode.hashCode() : 0);
        result = 31 * result + (easting != null ? easting.hashCode() : 0);
        result = 31 * result + (northing != null ? northing.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "countyid", referencedColumnName = "countyid")
    public IsrCountyEntity getIsrCountyByCountyid() {
        return isrCountyByCountyid;
    }

    public void setIsrCountyByCountyid(IsrCountyEntity isrCountyByCountyid) {
        this.isrCountyByCountyid = isrCountyByCountyid;
    }
}
