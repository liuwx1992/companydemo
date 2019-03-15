package com.example.springbootdemo.business.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: liuweixin
 * @Date: 2019/3/7
 * @Time: 13:52
 */
@RestController
@RequestMapping("/test")
public class TestBusinessController {

    @RequestMapping("/testMethod")
    public String getHello(){
        return "Hello World";
    }

    @RequestMapping("/testFastJson")
    public String testFastJson(){
        List<Object> list = new ArrayList<>();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONObject jsonObject5 = new JSONObject();

        jsonObject1.put("loa_time","2019");
        jsonObject1.put("loa_bank","中国银行");
        jsonObject1.put("loa_type","个人住房贷款");

        jsonObject2.put("loa_time","2019");
        jsonObject2.put("loa_bank","建设银行");
        jsonObject2.put("loa_type","个人商用房贷款");

        jsonObject3.put("loa_time","2018");
        jsonObject3.put("loa_bank","中国银行");
        jsonObject3.put("loa_type","个人住房公积金贷款");

        jsonObject4.put("loa_time","2019");
        jsonObject4.put("loa_bank","中国银行");
        jsonObject4.put("loa_type","个人住房贷款");

        jsonObject5.put("loa_time","2019");
        jsonObject5.put("loa_bank","建设银行");
        jsonObject5.put("loa_type","个人商用房贷款");

        list.add(jsonObject1);
        list.add(jsonObject2);
        list.add(jsonObject3);
        list.add(jsonObject3);
        list.add(jsonObject3);
        list.add(jsonObject4);
        list.add(jsonObject5);

        JSONArray loans = new JSONArray(list);

        loans = removeRepetitiveData(loans);

        int count = 0;
        for (int i = 0; i < loans.size(); i++) {
            JSONObject json = loans.getJSONObject(i);
            if (json.getString("loa_type").equals("个人住房贷款") || json.getString("loa_type").equals("个人商用房贷款") || json.getString("loa_type").equals("个人住房公积金贷款")) {
                count++;
            }
        }
        return ""+count;
    }

    /**
     *  去掉重复数据
     *     去重维度:时间(loa_time)、银行(loa_bank)
     *     同银行，相同时间发放，公积金贷款和个人住房贷款算一笔贷款，对于客户来说就是同一笔;
     * @param loans   个人贷款信息列表
     *
     * */
    private JSONArray removeRepetitiveData(JSONArray loans) {
        if(loans == null || loans.size() == 0){
            return loans;
        }
        List<Object> list = new ArrayList<>();
        List<Object> reduceList = new ArrayList<>();
        for(int i = 0;i < loans.size();i++){
            JSONObject loan = loans.getJSONObject(i);
            String loa_time = loan.getString("loa_time");
            String loa_bank = loan.getString("loa_bank");
            String removeStandard = loa_bank + loa_time;
            if(reduceList.contains(removeStandard)){
                continue;
            }
            reduceList.add(removeStandard);
            list.add(loan);
        }
        JSONArray ret = new JSONArray(list);
        return ret;
    }

    /**
     *  去掉重复数据----这个方法不存在问题，废弃了
     *     去重维度:时间(loa_time)、银行(loa_bank)
     *     同银行，相同时间发放，公积金贷款和个人住房贷款算一笔贷款，对于客户来说就是同一笔;
     * @param loans   个人贷款信息列表
     *
     * */
    private JSONArray removeRepetitiveData02(JSONArray loans) {
        if(loans == null || loans.size() == 0){
            return loans;
        }
        List<Object> list = new ArrayList<>();
        Map<String,String> reduceMap = new HashMap<>();
        for(int i = 0;i < loans.size();i++){
            JSONObject loan = loans.getJSONObject(i);
            String loa_time = loan.getString("loa_time");
            String loa_bank = loan.getString("loa_bank");
            if(reduceMap.get(loa_bank) != null && loa_time.equalsIgnoreCase(reduceMap.get(loa_bank))){
                //如果时间银行和时间一致就不存储
                continue;
            }
            list.add(loan);
            reduceMap.put(loa_bank,loa_time);
        }
        JSONArray ret = new JSONArray(list);
        return ret;
    }

}
