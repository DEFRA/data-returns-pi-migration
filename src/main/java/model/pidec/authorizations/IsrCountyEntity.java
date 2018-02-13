package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_county", schema = "piedc", catalog = "piedc")
public class IsrCountyEntity {
    private int countyid;
    private String countyname;
    private IsrCountryEntity isrCountryByCountryid;

    @Id
    @Column(name = "countyid", nullable = false, precision = 0)
    public int getCountyid() {
        return countyid;
    }

    public void setCountyid(int countyid) {
        this.countyid = countyid;
    }

    @Basic
    @Column(name = "countyname", nullable = true, length = 100)
    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrCountyEntity that = (IsrCountyEntity) o;

        if (countyid != that.countyid) return false;
        if (countyname != null ? !countyname.equals(that.countyname) : that.countyname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countyid;
        result = 31 * result + (countyname != null ? countyname.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "countryid", referencedColumnName = "countryid")
    public IsrCountryEntity getIsrCountryByCountryid() {
        return isrCountryByCountryid;
    }

    public void setIsrCountryByCountryid(IsrCountryEntity isrCountryByCountryid) {
        this.isrCountryByCountryid = isrCountryByCountryid;
    }
}
