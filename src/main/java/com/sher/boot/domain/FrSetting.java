package com.sher.boot.domain;

import lombok.Data;

/**
 * Created by wei.zhao on 2017/4/25.
 */
@Data
public class FrSetting {

    private int num;
    private int xps;
    private int clk;
    private int cc;
    private int xps_max;
    private int clk_max;
    private int cc_max;
    private double xps_factor;
    private double clk_factor;
    private double cc_factor;
    private double time_factor;
    private long time_cycle;


}
