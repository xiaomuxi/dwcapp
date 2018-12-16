package com.weddingcar.driver.common.ui.datepicker.entities;

/**
 * 日历数据实体
 * 封装日历绘制时需要的数据
 * 
 * Entity of calendar
 *
 * @author AigeStudio 2015-03-26
 */
public class DPInfo {
    public String strG, strF;
    public boolean isHoliday;
    public boolean isToday, isWeekend;
    public boolean isSolarTerms, isFestival, isDeferred;
    public boolean isDecorBG;
    public boolean isDecorTL, isDecorT, isDecorTR, isDecorL, isDecorR;

    @Override
    public String toString() {
        return "DPInfo{" +
                "strG='" + strG + '\'' +
                ", strF='" + strF + '\'' +
                ", isHoliday=" + isHoliday +
                ", isToday=" + isToday +
                ", isWeekend=" + isWeekend +
                ", isSolarTerms=" + isSolarTerms +
                ", isFestival=" + isFestival +
                ", isDeferred=" + isDeferred +
                ", isDecorBG=" + isDecorBG +
                ", isDecorTL=" + isDecorTL +
                ", isDecorT=" + isDecorT +
                ", isDecorTR=" + isDecorTR +
                ", isDecorL=" + isDecorL +
                ", isDecorR=" + isDecorR +
                '}';
    }
}