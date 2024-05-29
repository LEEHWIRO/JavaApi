package org.nsop.jusodemo.service;


import org.apache.ibatis.type.Alias;

@Alias("vwJusoDataVO")
public class VwJusoDataVO {

    private String vwId;
    private String vwLnmadr;
    private String vwRdnmadr;
    private String vwLa;
    private String vwLo;
    private String vwFcltyNm;
    private String vwYn;

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
