package org.nsop.jusodemo.service;


import org.apache.ibatis.type.Alias;

@Alias("mergeJusoDataVO")
public class MergeJusoDataVO {

    private String seCd;
    private String gpCd;
    private String fcltyCd;
    private String fcltyNm;
    private String lnmadr;
    private String rdnmadr;
    private String la;
    private String lo;
    private String vwId;
    private String vwLnmadr;
    private String vwRdnmadr;
    private String vwLa;
    private String vwLo;
    private String vwFcltyNm;
    private String vwYn;

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

    public String getVwId() {
        return vwId;
    }

    public void setVwId(String vwId) {
        this.vwId = vwId;
    }

    public String getVwLnmadr() {
        return vwLnmadr;
    }

    public void setVwLnmadr(String vwLnmadr) {
        this.vwLnmadr = vwLnmadr;
    }

    public String getVwRdnmadr() {
        return vwRdnmadr;
    }

    public void setVwRdnmadr(String vwRdnmadr) {
        this.vwRdnmadr = vwRdnmadr;
    }

    public String getVwLa() {
        return vwLa;
    }

    public void setVwLa(String vwLa) {
        this.vwLa = vwLa;
    }

    public String getVwLo() {
        return vwLo;
    }

    public void setVwLo(String vwLo) {
        this.vwLo = vwLo;
    }

    public String getVwFcltyNm() {
        return vwFcltyNm;
    }

    public void setVwFcltyNm(String vwFcltyNm) {
        this.vwFcltyNm = vwFcltyNm;
    }

    public String getVwYn() {
        return vwYn;
    }

    public void setVwYn(String vwYn) {
        this.vwYn = vwYn;
    }
}
