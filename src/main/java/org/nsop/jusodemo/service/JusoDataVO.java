package org.nsop.jusodemo.service;


import org.apache.ibatis.type.Alias;

@Alias("jusoDataVO")
public class JusoDataVO {

    private String seCd;
    private String gpCd;
    private String fcltyCd;
    private String fcltyNm;
    private String lnmadr;
    private String rdnmadr;
    private String la;
    private String lo;

    public String getSeCd() {
        return seCd;
    }

    public void setSeCd(String seCd) {
        this.seCd = seCd;
    }

    public String getGpCd() {
        return gpCd;
    }

    public void setGpCd(String gpCd) {
        this.gpCd = gpCd;
    }

    public String getFcltyCd() {
        return fcltyCd;
    }

    public void setFcltyCd(String fcltyCd) {
        this.fcltyCd = fcltyCd;
    }

    public String getFcltyNm() {
        return fcltyNm;
    }

    public void setFcltyNm(String fcltyNm) {
        this.fcltyNm = fcltyNm;
    }

    public String getLnmadr() {
        return lnmadr;
    }

    public void setLnmadr(String lnmadr) {
        this.lnmadr = lnmadr;
    }

    public String getRdnmadr() {
        return rdnmadr;
    }

    public void setRdnmadr(String rdnmadr) {
        this.rdnmadr = rdnmadr;
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }
}
