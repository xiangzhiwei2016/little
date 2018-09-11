package com.fengdu.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 订单状态
 * @author xiangzhiwei
 *
 */
public enum OrderStatusEnum {
	ORDER_NOT_PAYED (0,"未付款"),
	ORDER_WATING (201,"等待发货"),
	ORDER_RECIVEDD (300,"待收货"),
	ORDER_FINISH (301,"已完成"),
	ORDER_PAYED (200,"已付款"),
	ORDER_CANCLE (101,"已取消"),
	ORDER_PAYED_CANCLE (401,"支付后取消"),
	ORDER_BACK (402,"已退货");


    private int    code;

    private String text;

    private OrderStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static OrderStatusEnum getEnumByCode(int code) {
        for (OrderStatusEnum an : OrderStatusEnum.values()) {
            if (an.getCode() == code)
                return an;
        }
        return null;
    }
    
    public static String getTextByCode(int code) {
        for (OrderStatusEnum an : OrderStatusEnum.values()) {
            if (an.getCode() == code)
                return an.getText();
        }
        return null;
    }

    public static OrderStatusEnum getEnumByText(String typeName) {
        for (OrderStatusEnum an : OrderStatusEnum.values()) {
            if (an.getText().equals(typeName)) {
                return an;
            }
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }

    public static List<Map<String, Object>> getListEnum() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> dto = null;
        for (OrderStatusEnum an : OrderStatusEnum.values()) {
            dto = new HashMap<String, Object>();
            dto.put("text", an.getText());
            dto.put("value", an.getCode());
            list.add(dto);
        }
        return list;
    }


    
}
