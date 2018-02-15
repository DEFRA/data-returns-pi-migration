package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_site", schema = "piedc", catalog = "piedc")
public class IsrSiteEntity {
    private long siteid;
    private String siteaddress;
    private String sitepostcode;

    @Id
    @Column(name = "siteid", nullable = false, precision = 0)
    public long getSiteid() {
        return siteid;
    }

    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }

    @Basic
    @Column(name = "siteaddress", nullable = true, length = 2147483647)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrSiteEntity that = (IsrSiteEntity) o;

        return siteaddress != null ? siteaddress.equals(that.siteaddress) : that.siteaddress == null;
    }

    @Override
    public int hashCode() {
        return siteaddress != null ? siteaddress.hashCode() : 0;
    }
}
