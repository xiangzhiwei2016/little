package com.fengdu.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ImageTypeEnum {
	//类型（1.商品的，2.用户的头像3.案例4.详情的5.营销活动6.评价7.门店）

	PRODUCT ("1","商品"),
	CLIENT_IMAGE ("2","用户的头像"),
	CASE("3","案例"),
	DETAILS ("4","案例详情"),
	COMPAIGN ("5","营销活动"),
	EVALUATE("6","评价"),
	STORE("7","门店");

    private String    code;

    private String text;

    private ImageTypeEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static ImageTypeEnum getEnumByCode(int code) {
        for (ImageTypeEnum an : ImageTypeEnum.values()) {
            if (an.getCode().equals(code))
                return an;
        }
        return null;
    }

    public static ImageTypeEnum getEnumByText(String typeName) {
        for (ImageTypeEnum an : ImageTypeEnum.values()) {
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
        for (ImageTypeEnum an : ImageTypeEnum.values()) {
            dto = new HashMap<String, Object>();
            dto.put("text", an.getText());
            dto.put("value", an.getCode());
            list.add(dto);
        }
        return list;
    }


    
}
