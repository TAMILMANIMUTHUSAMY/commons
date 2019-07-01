package com.agathium.common.configuration;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:21
 */
public class Field {

    private String name;
    private String type;
    private Boolean nullable;
    private Boolean readOnly;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }
}
