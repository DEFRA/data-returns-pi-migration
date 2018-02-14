package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_area", schema = "piedc", catalog = "piedc")
public class IsrAreaEntity {
    private Integer areaid;
    private String areaname;
    private String areashortname;
    private IsrRegionEntity isrRegionByRegionid;

    @Id
    @Column(name = "areaid", nullable = false, precision = 0)
    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    @Basic
    @Column(name = "areaname", nullable = true, length = 100)
    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    @Basic
    @Column(name = "areashortname", nullable = true, length = 8)
    public String getAreashortname() {
        return areashortname;
    }

    public void setAreashortname(String areashortname) {
        this.areashortname = areashortname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAreaEntity that = (IsrAreaEntity) o;

        if (areaid != null ? !areaid.equals(that.areaid) : that.areaid != null) return false;
        if (areaname != null ? !areaname.equals(that.areaname) : that.areaname != null) return false;
        if (areashortname != null ? !areashortname.equals(that.areashortname) : that.areashortname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaid != null ? areaid.hashCode() : 0;
        result = 31 * result + (areaname != null ? areaname.hashCode() : 0);
        result = 31 * result + (areashortname != null ? areashortname.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "regionid", referencedColumnName = "regionid")
    public IsrRegionEntity getIsrRegionByRegionid() {
        return isrRegionByRegionid;
    }

    public void setIsrRegionByRegionid(IsrRegionEntity isrRegionByRegionid) {
        this.isrRegionByRegionid = isrRegionByRegionid;
    }
}
