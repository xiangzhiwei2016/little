package com.fengdu.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatusEnum {
	ORDER_CANCLE ("0","取消订单"),
	ORDER_HANDLESEL ("1","已交定金");

    private String    code;

    private String text;

    private OrderStatusEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static OrderStatusEnum getEnumByCode(int code) {
        for (OrderStatusEnum an : OrderStatusEnum.values()) {
            if (an.getCode().equals(code))
                return an;
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

    public String getCode() {
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
