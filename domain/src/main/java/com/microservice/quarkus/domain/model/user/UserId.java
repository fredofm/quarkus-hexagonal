package com.microservice.quarkus.domain.model.user;

import com.microservice.quarkus.domain.shared.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserId implements ValueObject<UserId> {
    private String id;

    @Override
    public boolean sameValueAs(UserId other) {
        return other != null && this.id.equals(other.id);
    }
}
