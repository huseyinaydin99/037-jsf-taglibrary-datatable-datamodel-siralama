/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siteninadi;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import javax.faces.model.DataModel;

/**
 *
 * @author husey
 */
public class DataModelSiralamasi<T> extends DataModel<T> {

    DataModel<T> dataModel;
    private Integer[] satirlar;

    public DataModelSiralamasi(DataModel<T> dataModel) {
        this.dataModel = dataModel;
        if (dataModel != null) {
            if (dataModel.getRowCount() > 0) {
                satirlariAl();
            } else {
                System.err.println("data model null");
            }
        }
        System.out.println("DATA MODEL HAZIRLANDI");
    }

    public void satirlariAl() {
        int rowCount = dataModel.getRowCount();
        if (rowCount != -1) {
            this.satirlar = new Integer[rowCount];
            for (int i = 0; i < rowCount; ++i) {
                this.satirlar[i] = i;
            }
        }
    }

    public void sirala(final Comparator<T> comparator) {
        System.out.println("sirala methodu çalıştı");
        Comparator<Integer> satirlariKarsilastir = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                T o1 = getData(i1);
                T o2 = getData(i2);
                return comparator.compare(o1, o2);
            }
        };
        Arrays.sort(satirlar, satirlariKarsilastir);
        System.out.println("arrays altı");

    }

    private T getData(int satir) {
        int orginalSatirIndexi = dataModel.getRowIndex();
        dataModel.setRowIndex(satir);

        T yeniSatirVerisi = dataModel.getRowData();
        dataModel.setRowIndex(orginalSatirIndexi);
        return yeniSatirVerisi;
    }

    @Override
    public void setRowIndex(int satirIndexi) {
        if (0 <= satirIndexi && satirIndexi < satirlar.length) {
            dataModel.setRowIndex(satirlar[satirIndexi]);
        } else {
            dataModel.setRowIndex(satirIndexi);
        }
    }

    @Override
    public boolean isRowAvailable() {
        return dataModel.isRowAvailable();
    }

    @Override
    public int getRowCount() {
        return dataModel.getRowCount();
    }

    @Override
    public T getRowData() {
        return dataModel.getRowData();
    }

    @Override
    public int getRowIndex() {
        return dataModel.getRowIndex();
    }

    @Override
    public Object getWrappedData() {
        return dataModel.getWrappedData();
    }

    @Override
    public void setWrappedData(Object o) {
        dataModel.setWrappedData(o);
        satirlariAl();
    }

    public static class Personel {

        private String numarasi, adiSoyadi;
        private BigDecimal maasi;
        private int yasi;
        private boolean duzenle;

        public Personel(String numarasi, String adiSoyadi, BigDecimal maasi, int yasi) {
            this.numarasi = numarasi;
            this.adiSoyadi = adiSoyadi;
            this.maasi = maasi;
            this.yasi = yasi;
        }

        public String getNumarasi() {
            return numarasi;
        }

        public void setNumarasi(String numarasi) {
            this.numarasi = numarasi;
        }

        public String getAdiSoyadi() {
            return adiSoyadi;
        }

        public void setAdiSoyadi(String adiSoyadi) {
            this.adiSoyadi = adiSoyadi;
        }

        public BigDecimal getMaasi() {
            return maasi;
        }

        public void setMaasi(BigDecimal maasi) {
            this.maasi = maasi;
        }

        public int getYasi() {
            return yasi;
        }

        public void setYasi(int yasi) {
            this.yasi = yasi;
        }

        public boolean isDuzenle() {
            return duzenle;
        }

        public void setDuzenle(boolean duzenle) {
            this.duzenle = duzenle;
        }

    }

    public DataModel<T> getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel<T> dataModel) {
        this.dataModel = dataModel;
    }

    public Integer[] getSatirlar() {
        return satirlar;
    }

    public void setSatirlar(Integer[] satirlar) {
        this.satirlar = satirlar;
    }

    
    
}
