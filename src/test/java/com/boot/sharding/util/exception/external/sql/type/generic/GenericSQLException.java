/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.boot.sharding.util.exception.external.sql.type.generic;

import com.boot.sharding.util.exception.external.sql.ShardingSphereSQLException;
import com.boot.sharding.util.exception.external.sql.sqlstate.SQLState;

/**
 * Generic SQL exception.
 */
public abstract class GenericSQLException extends ShardingSphereSQLException {
    
    private static final long serialVersionUID = 1156879276497567865L;
    
    private static final int TYPE_OFFSET = 3;
    
    public GenericSQLException(final SQLState sqlState, final int errorCode, final String reason, final Object... messageArgs) {
        super(sqlState, TYPE_OFFSET, errorCode, reason, messageArgs);
    }
    
    public GenericSQLException(final String reason, final Exception cause, final SQLState sqlState, final int errorCode) {
        super(sqlState.getValue(), TYPE_OFFSET, errorCode, reason, cause);
    }
}
