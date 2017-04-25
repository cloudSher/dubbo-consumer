package com.sher.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by wei.zhao on 2017/4/25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String id;
    private int xps;
    private int clk;
    private int cc;
    private Date msgTm;
    private int xps_max;
    private int clk_max;
    private int cc_max;
    private long time_cycle;
    private double xps_factor;
    private double clk_factor;
    private double cc_factor;
    private double time_factor;
    private double xps_value;
    private double clk_value;
    private double cc_value;
    private double time_value;
    private double score;

    public Message(String id, int xps, int clk, int cc, Date msgTm, int xps_max, int clk_max, int cc_max,long time_cycle,
                   double xps_factor, double clk_factor, double cc_factor, double time_factor) {
        this.id = id;
        this.xps = xps;
        this.clk = clk;
        this.cc = cc;
        this.msgTm = msgTm;
        this.xps_max = xps_max;
        this.clk_max = clk_max;
        this.cc_max = cc_max;
        this.xps_factor = xps_factor;
        this.clk_factor = clk_factor;
        this.cc_factor = cc_factor;
        this.time_factor = time_factor;
    }
}
