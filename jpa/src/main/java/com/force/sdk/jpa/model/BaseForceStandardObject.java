/**
 * Copyright (c) 2011, salesforce.com, inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 *
 *    Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *    following disclaimer.
 *
 *    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *    the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 *    Neither the name of salesforce.com, inc. nor the names of its contributors may be used to endorse or
 *    promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.force.sdk.jpa.model;

import javax.persistence.*;

/**
 * Base Java model for a Force.com JPA standard object.
 * This contains all common fields for a Force.com
 * standard object.
 *
 * @author Tim Kral
 **/
@MappedSuperclass
public abstract class BaseForceStandardObject extends BaseForceObject {

    protected String name;
    protected ForceOwner forceOwner;
    protected java.util.Calendar createdDate;
    protected java.util.Calendar lastModifiedDate;
    protected java.util.Calendar systemModstamp;

    @Basic(optional = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @ManyToOne
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ownerId")
    public ForceOwner getForceOwner() {
        return this.forceOwner;
    }

    public void setForceOwner(ForceOwner forceOwner) {
        this.forceOwner = forceOwner;
    }
    
    public java.util.Calendar getCreatedDate() {
        return this.createdDate;
    }

    void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }
    
    @Version
    public java.util.Calendar getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    void setLastModifiedDate(java.util.Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public java.util.Calendar getSystemModstamp() {
        return this.systemModstamp;
    }

    void setSystemModstamp(java.util.Calendar systemModstamp) {
        this.systemModstamp = systemModstamp;
    }
}
