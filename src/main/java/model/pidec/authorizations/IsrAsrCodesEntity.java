package model.pidec.authorizations;

import javax.persistence.*;

@Entity
@Table(name = "isr_asr_codes", schema = "piedc", catalog = "piedc")
public class IsrAsrCodesEntity {
    private String asrCode;
    private String asrCodeDescription;

    @Id
    @Column(name = "asr_code", nullable = false, length = 40)
    public String getAsrCode() {
        return asrCode;
    }

    public void setAsrCode(String asrCode) {
        this.asrCode = asrCode;
    }

    @Basic
    @Column(name = "asr_code_description", nullable = true, length = 80)
    public String getAsrCodeDescription() {
        return asrCodeDescription;
    }

    public void setAsrCodeDescription(String asrCodeDescription) {
        this.asrCodeDescription = asrCodeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsrAsrCodesEntity that = (IsrAsrCodesEntity) o;

        if (asrCode != null ? !asrCode.equals(that.asrCode) : that.asrCode != null) return false;
        if (asrCodeDescription != null ? !asrCodeDescription.equals(that.asrCodeDescription) : that.asrCodeDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = asrCode != null ? asrCode.hashCode() : 0;
        result = 31 * result + (asrCodeDescription != null ? asrCodeDescription.hashCode() : 0);
        return result;
    }
}
