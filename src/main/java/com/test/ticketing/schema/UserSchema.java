package com.test.ticketing.schema;

import com.test.ticketing.model.Users;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSchema {
    private String name;
    private String identity_number;
    private String phone_number;

    public UserSchema() {
    }

    public UserSchema(Users users) {
        this.name = users.getName();
        this.identity_number = users.getIdentity_number();
        this.phone_number = users.getPhone_number();
    }

}
