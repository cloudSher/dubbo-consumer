package com.sher.boot.handler;

import com.alibaba.fastjson.JSONArray;
import com.sher.boot.domain.FrSetting;
import com.sher.boot.domain.Message;
import com.sher.boot.util.DataUtil;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by wei.zhao on 2017/4/25.
 */
@Component
public class FrHandler {


    public String doHandle(FrSetting setting) {
        int num = setting.getNum();
        List<Message> msgs = new ArrayList<>(num);
        for(int i = 0; i < num; i++){
            msgs.add(new Message((i+1)+"", setting.getXps(),setting.getClk(), setting.getCc(),DataUtil.todayAndltCurr(System.currentTimeMillis()),
                    setting.getXps_max(),setting.getClk_max(),setting.getCc_max(),setting.getTime_cycle(),setting.getXps_factor()
            ,setting.getClk_factor(),setting.getCc_factor(),setting.getTime_factor()));
        }

        score(msgs);

        sort(msgs);

        JSONArray array = new JSONArray(msgs.size());
        array.addAll(msgs);
        return array.toJSONString();
    }


    public List<Message> score(List<Message> msgs){
        if(msgs!=null){
            for (Message msg: msgs){
                long now = System.currentTimeMillis();
                double xpsValue = featureDataDecay(msg.getXps(),msg.getXps_max());
                double clickValue = featureDataDecay(msg.getClk(),msg.getClk_max());
                double callValue = featureDataDecay(msg.getCc(),msg.getCc_max());
                double timeValue = timeLineDecay((Math.abs(now - msg.getMsgTm().getTime())),msg.getTime_cycle());
                double xpsDcValue = msg.getXps_factor() * xpsValue;
                double clkDcValue = msg.getClk_factor() * clickValue;
                double ccDcValue = msg.getCc_factor() * callValue;
                double timeDcValue = msg.getTime_factor() * timeValue;
                double score = xpsDcValue+clkDcValue+ccDcValue+timeDcValue;
                msg.setScore(score);
                msg.setXps_value(xpsDcValue);
                msg.setClk_value(clkDcValue);
                msg.setCc_value(ccDcValue);
                msg.setTime_value(timeDcValue);
                System.out.println("== score == " + score);
            }
        }
        return msgs;
    }


    /**
     * 特征数据衰减函数
     * @param feature  特征值
     * @param max  最大特征值
     * @return
     */
    public static double featureDataDecay(int feature,double max){
        if(feature == 0) return 0;
        if(feature <= max){
            return Math.log(feature);
        }else{
            return Math.log(max/Math.sqrt(feature - max + 1));
        }
    }

    /**
     *  时间衰减函数
     * @param time 反应时间，当前时间-创建时间
     * @param cycle 周期 单位毫秒
     * @return
     */
    public static double timeLineDecay(long time, double cycle){
        long t = time == 0 ? 1 : time;
        double tv =  Math.log(cycle / Math.sqrt(t));
        return tv;
    }

    public static void sort(List<Message> msgs){
        Collections.sort(msgs, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return Double.compare(o2.getScore(),o1.getScore());
            }
        });
    }
}
