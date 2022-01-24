package com.codepulse.visitlogService.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    /**
     * 400 Bad Request
     */
    DUPLICATED_USER_ID(41001, "error.duplicated.user.id"),

    /**
     * 401 Unauthorized
     */
    UNAUTHORIZED_TOKEN(41101, "error.unauthorized.token"),

    /**
     * 404 Not found
     */

    /**
     * 408 Request timeout
     */

    /**
     * 409 Conflict
     */

    /**
     * 426 Upgrade required
     */

    /**
     * 503 Service unavailable
     */

    /**
     * 500 Internal server error
     */
    NOT_READY_SERVER(50010, "error.server.not.ready"),
    INTERNAL_SERVER_ERROR(50011, "internal.server.error"),

    /**
     * Exception
     */
    ;

    private int code;
    private String description;
}
