package model.pidec.authorizations;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "isr_area", schema = "piedc", catalog = "piedc")
@ToString(includeFieldNames = true)
public class IsrAreaEntity {
    private int areaid;
    private String areaname;
    private String areashortname;
    private String areaaddress;
    private String areapostcode;
    private String areatelephone;
    private String areafax;

    @Id
    @Column(name = "areaid", nullable = false, precision = 0)
    public int getAreaid() {
        return areaid;
    }

    public void setAreaid(int areaid) {
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

    @Basic
    @Column(name = "areaaddress", nullable = true, length = -1)
    public String getAreaaddress() {
        return areaaddress;
    }

    public void setAreaaddress(String areaaddress) {
        this.areaaddress = areaaddress;
    }

    @Basic
    @Column(name = "areapostcode", nullable = true, length = 100)
    public String getAreapostcode() {
        return areapostcode;
    }

    public void setAreapostcode(String areapostcode) {
        this.areapostcode = areapostcode;
    }

    @Basic
    @Column(name = "areatelephone", nullable = true, length = 100)
    public String getAreatelephone() {
        return areatelephone;
    }

    public void setAreatelephone(String areatelephone) {
        this.areatelephone = areatelephone;
    }

    @Basic
    @Column(name = "areafax", nullable = true, length = 100)
    public String getAreafax() {
        return areafax;
    }

    public void setAreafax(String areafax) {
        this.areafax = areafax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAreaEntity that = (IsrAreaEntity) o;

        if (areaid != that.areaid) return false;
        if (areaname != null ? !areaname.equals(that.areaname) : that.areaname != null) return false;
        if (areashortname != null ? !areashortname.equals(that.areashortname) : that.areashortname != null)
            return false;
        if (areaaddress != null ? !areaaddress.equals(that.areaaddress) : that.areaaddress != null) return false;
        if (areapostcode != null ? !areapostcode.equals(that.areapostcode) : that.areapostcode != null) return false;
        if (areatelephone != null ? !areatelephone.equals(that.areatelephone) : that.areatelephone != null)
            return false;
        if (areafax != null ? !areafax.equals(that.areafax) : that.areafax != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaid;
        result = 31 * result + (areaname != null ? areaname.hashCode() : 0);
        result = 31 * result + (areashortname != null ? areashortname.hashCode() : 0);
        result = 31 * result + (areaaddress != null ? areaaddress.hashCode() : 0);
        result = 31 * result + (areapostcode != null ? areapostcode.hashCode() : 0);
        result = 31 * result + (areatelephone != null ? areatelephone.hashCode() : 0);
        result = 31 * result + (areafax != null ? areafax.hashCode() : 0);
        return result;
    }

}
