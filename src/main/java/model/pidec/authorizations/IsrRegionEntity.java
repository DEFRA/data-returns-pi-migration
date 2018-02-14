package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_region", schema = "piedc", catalog = "piedc")
public class IsrRegionEntity {
    private Integer regionid;
    private String regionname;
    private String regionshortname;

    @Id
    @Column(name = "regionid", nullable = false, precision = 0)
    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    @Basic
    @Column(name = "regionname", nullable = true, length = 100)
    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    @Basic
    @Column(name = "regionshortname", nullable = true, length = 4)
    public String getRegionshortname() {
        return regionshortname;
    }

    public void setRegionshortname(String regionshortname) {
        this.regionshortname = regionshortname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrRegionEntity that = (IsrRegionEntity) o;

        if (regionid != null ? !regionid.equals(that.regionid) : that.regionid != null) return false;
        if (regionname != null ? !regionname.equals(that.regionname) : that.regionname != null) return false;
        if (regionshortname != null ? !regionshortname.equals(that.regionshortname) : that.regionshortname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regionid != null ? regionid.hashCode() : 0;
        result = 31 * result + (regionname != null ? regionname.hashCode() : 0);
        result = 31 * result + (regionshortname != null ? regionshortname.hashCode() : 0);
        return result;
    }
}
